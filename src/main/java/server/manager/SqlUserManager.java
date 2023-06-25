package server.manager;


import common.Authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.logging.Logger;

public class SqlUserManager {
    private final Connection conn;
    private final Logger logger;

    public SqlUserManager(Connection connection, Logger logger) {
        this.conn = connection;
        this.logger = logger;
    }

    public void initUserTable() throws SQLException {
        try (Statement s = conn.createStatement()) {
            String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS users(" +
                    "id BIGSERIAL PRIMARY KEY," +
                    "user_name varchar(127) NOT NULL UNIQUE," +
                    "password varchar (127) NOT NULL," +
                    "salt varchar(127))";
            s.execute(CREATE_TABLE_USER);
        }
    }

    public Long registration(Authentication client) throws SQLException {
        logger.info("регистрация пользователя");
        Base64.Encoder encoder = Base64.getEncoder();
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[6];
        random.nextBytes(salt);
        String saltStr = encoder.encodeToString(salt);
        try (PreparedStatement s = conn.prepareStatement("INSERT INTO users (user_name, password, salt) VALUES (?, ?, ?) RETURNING id")) {
            s.setString(1, client.getUserName());
            s.setString(2, hashPassword(client.getPassword(), saltStr));
            s.setString(3, saltStr);
            ResultSet res = s.executeQuery();
            res.next();

            conn.commit();
            logger.info("пользователь успешно добавлен");
            return res.getLong("id");
        }
    }

    private String hashPassword(String password, String salt) {
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest((password + salt).getBytes(StandardCharsets.UTF_8));
            return encoder.encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Long login(Authentication client) throws SQLException, NullPointerException {
        try (PreparedStatement s = conn.prepareStatement("SELECT id, password, salt FROM users WHERE user_name = ? LIMIT 1")) {
            s.setString(1, client.getUserName());
            try (ResultSet res = s.executeQuery()) {
                if (res.next()) {
                    String realPasswordHashed = res.getString("password");
                    String passwordHashed = hashPassword(client.getPassword(), res.getString("salt"));
                    if (passwordHashed.equals(realPasswordHashed)) {
                        return res.getLong("id");
                    }
                }
            }
            return null;
        }
    }
}

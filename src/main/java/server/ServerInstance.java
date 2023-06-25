package server;


import server.manager.CollectionManager;
import server.manager.CommandManager;
import server.manager.SqlCollectionManager;
import server.manager.SqlUserManager;
import server.utility.LabAsk;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ServerInstance {
    private final Logger logger;
    private static final int TIMEOUTWRITE = 100;
    private static final int SOCKET_TIMEOUT = 10;
    private final int port;
    private CommandManager commandManager;
    private final Scanner scanner;
    private SqlCollectionManager sqlCollectionManager;
    private SqlUserManager sqlUserManager;
    private final ExecutorService selectorCommand = Executors.newCachedThreadPool();
    private final CollectionManager collectionManager;
    private SelectorResponse selectorResponse;


    public ServerInstance(int port, CollectionManager collectionManager, Scanner scanner, String dbUser, String dbPassword) {
        this.port = port;
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.logger = Logger.getLogger("log");
        File lf = new File("server.log");
        FileHandler fh;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", dbUser, dbPassword);
            this.sqlCollectionManager = new SqlCollectionManager(connection, logger);
            this.sqlUserManager = new SqlUserManager(connection, logger);
            fh = new FileHandler(lf.getAbsolutePath(), true);
            logger.addHandler(fh);
            this.commandManager = new CommandManager(collectionManager, new LabAsk(scanner), sqlCollectionManager);
            this.selectorResponse = new SelectorResponse(sqlUserManager, logger, commandManager);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "логер не записывает данные в файл");
        } catch (SQLException e) {
            System.out.println("ошибка подключения к базе данных");
            System.exit(1);
        }
    }

    private void start() {
        try {
            this.sqlUserManager.initUserTable();
            this.sqlCollectionManager.initTableOrExecuteLabWorks();
        } catch (SQLException e) {
            logger.severe("не удалось прочитать базу данных" + e);
        }
        collectionManager.initializeData(sqlCollectionManager.getCollection());
    }

    public void run() throws IOException {
        int check = 0;
        try (ServerSocket serv = new ServerSocket(port)) {
            serv.setSoTimeout(SOCKET_TIMEOUT);
            start();
            System.out.println("Сервер начал работать хост=" + InetAddress.getLocalHost() + " порт=" + port);
            while (true) {
                if (acceptConsoleInput()) {
                    return;
                }
                try {
                    Socket newClient = serv.accept();
                    newClient.setSoTimeout(SOCKET_TIMEOUT);
                    handleRequests(new MessageHandler(newClient));
                } catch (SocketTimeoutException e) {
                    if (check++ >= TIMEOUTWRITE) {
                        check = 0;
                    }
                }
            }
        } catch (BindException e) {
            System.out.println("Порт занят");
        }
    }

    public void handleRequests(MessageHandler client1) {
        new Thread(() -> {
            ClientInServer clientInServer = new ClientInServer(client1, logger, selectorResponse, selectorCommand);
            clientInServer.handleRequests();
        }).start();
    }

    private boolean acceptConsoleInput() throws IOException {
        if (System.in.available() > 0) {
            String command = scanner.nextLine().trim();
            if (command.equals("exit")) {
                logger.info("завершение работы");
                commandManager.exit();
            }
            System.out.println("Команда не распознана, сервер распознаёт только команду 'exit'");
        }
        return false;
    }
}
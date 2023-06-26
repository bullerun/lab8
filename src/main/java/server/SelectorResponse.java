package server;

import common.*;
import common.data.LabWork;
import server.manager.CommandManager;
import server.manager.SqlUserManager;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SelectorResponse {

    private final Logger logger;
    private final SqlUserManager sqlUserManager;
    private final CommandManager commandManager;

    public SelectorResponse(SqlUserManager sqlUserManager, Logger logger, CommandManager commandManager) {
        this.logger = logger;
        this.sqlUserManager = sqlUserManager;
        this.commandManager = commandManager;
    }

    public ResponseWithBooleanType selectAuthCommand(String command, Authentication client) {
        if (command.equals("reg")) {
            try {
                Long id = sqlUserManager.registration(client);
                client.setId(id);
                return new ResponseWithBooleanType(AuthorizationError.NAME_IS_TAKING, true, client);
            } catch (PSQLException e) {
                logger.info(client.getUserName() + e);
                return new ResponseWithBooleanType(AuthorizationError.NAME_IS_TAKING, false, client);
            } catch (SQLException e) {
                logger.info("sql недоступна " + e);
                return new ResponseWithBooleanType(AuthorizationError.ERROR, false, client);
            }
        }
        if (command.equals("login")) {
            try {
                Long id = sqlUserManager.login(client);
                if (id != null) {
                    client.setId(id);
                    return new ResponseWithBooleanType(AuthorizationError.INVALID_USERNAME_OR_PASSWORD, true, client);
                }
            } catch (PSQLException e) {
                logger.info(client.getUserName() + e);
                return new ResponseWithBooleanType(AuthorizationError.ERROR, false, client);
            } catch (SQLException e) {
                return new ResponseWithBooleanType(AuthorizationError.ERROR, false, client);
            }
        }
        return new ResponseWithBooleanType(AuthorizationError.INVALID_USERNAME_OR_PASSWORD, false, client);
    }

    public ResponseWithTreeSet selectCommand(String[] command, Authentication client) {
        try {
            return commandManager.commandSelection(command, sqlUserManager.login(client));
        } catch (SQLException e) {
            return new ResponseWithTreeSet("не удалось выполнить команду " + command[0] + " нехватка прав или лабораторная работа не обнаружена", null);
        }
    }

    public ResponseWithTreeSet selectCommand(String command, LabWork labWork, Authentication client) {
        try {
            return commandManager.commandSelection(command, labWork, sqlUserManager.login(client));
        } catch (SQLException e) {
            return new ResponseWithTreeSet("не удалось выполнить команду " + command + " нехватка прав или лабораторная работа не обнаружена", null);
        }
    }

    public Response selectWithCommands(String command, ArrayList<String> commands, Authentication client) {
        try {
            return commandManager.commandSelectionFromScript(command, commands, sqlUserManager.login(client));
        } catch (SQLException e) {
            return new Response("не удалось выполнить команду " + command + " нехватка прав или лабораторная работа не обнаружена");
        }
    }

    public ResponseWithLabWork update(String[] commands, Authentication client) {
        try {
            return commandManager.updateCommand(commands, sqlUserManager.login(client));
        } catch (SQLException e) {
            return new ResponseWithLabWork("не удалось выполнить команду update", null);
        }
    }
}

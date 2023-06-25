package client.backend;

import client.UI.controllers.MainFormController;
import client.backend.tableHandlers.TableViewHandler;
import common.*;
import javafx.geometry.Pos;
import common.data.LabWork;
import common.exception.MustBeNotEmptyException;
import common.exception.RangeException;
import org.controlsfx.control.Notifications;
//import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * console input processing class
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class Console {
    private static Sender sender;
    private static final int TIMEOUT = 10;
    private static final int TIMEOUTMS = 100;
    private static final int MILLIS_IN_SECONDS = 1000;

    public static Authentication client;
    private final Scanner scanner;
    private static InetSocketAddress address;
    private static ScriptReader scriptReader;

    public Console(Scanner scanner, LabAsk labAsk, ScriptReader scriptReader) {
        this.scanner = scanner;
        this.sender = new Sender();
        this.scriptReader = scriptReader;
    }

    public static void setAddress(InetSocketAddress address) {
        Console.address = address;
    }


    public static void add(LabWork labWork) {
        try {
            sender.sendMessageWithLabWork("add", labWork, client);
            ResponseWithTreeSet response = hookUpdateResponse();
            if (response != null && response.getLabWork() != null) {
                Notifications.create().position(Pos.TOP_CENTER).text(response.getResponse()).show();
                TableViewHandler tableViewHandler = MainFormController.getMainFormController().getTableViewHandler();
                tableViewHandler.initializeData(response.getLabWork());
                Notifications.create().position(Pos.TOP_CENTER).text("Добавление прошло успешно").show();
            }
            Notifications.create().position(Pos.TOP_CENTER).text("ошибка добавление").show();
        } catch (IOException ignored) {
            Notifications.create().position(Pos.TOP_CENTER).text("ошибка добавление").show();
        }
    }

    public static void update(LabWork labWork) {
        try {
            sender.sendMessageWithLabWork("update", labWork, client);

            ResponseWithTreeSet response = hookUpdateResponse();
            if (response != null && response.getLabWork() != null) {
                Notifications.create().position(Pos.TOP_CENTER).text(response.getResponse()).show();
                TableViewHandler tableViewHandler = MainFormController.getMainFormController().getTableViewHandler();
                tableViewHandler.initializeData(response.getLabWork());
            } else {
                Notifications.create().position(Pos.TOP_CENTER).text("ошибка обновления").show();
            }
        } catch (IOException e) {
            Notifications.create().position(Pos.TOP_CENTER).text("ошибка обновления").show();
        }
    }

    public static void execute_script(String absolutePath) {
        scriptReader.scriptReader(absolutePath);
        if (!scriptReader.getCommands().isEmpty()) {
            try {
                sender.sendMessageWithCommands(scriptReader.getCommands(), client);
                scriptReader.clearCommands();
                Notifications.create().position(Pos.TOP_CENTER).text(hookResponse().getResponse()).show();
            } catch (IOException e) {
                Notifications.create().position(Pos.TOP_CENTER).text("ошибка чтения").show();
            }
        }
    }

    private static Response hookResponse() throws IOException {
        Response response = (Response) waitForResponse();
        sender.clearInBuffer();
        return response;
    }


    public static void selectCommand(String[] command) {
        try {
            if (command[0].equals("exit")) {
                System.out.println("Завершение работы");
                System.exit(0);
            } else if (ValidationChecker.checkValidation(command)) {
                sender.sendMessage(command, client);
                ResponseWithTreeSet response = hookUpdateResponse();
                if (response != null && response.getLabWork() != null) {
                    Notifications.create().position(Pos.TOP_CENTER).text(response.getResponse()).show();
                    TableViewHandler tableViewHandler = MainFormController.getMainFormController().getTableViewHandler();
                    tableViewHandler.initializeData(response.getLabWork());
                }
            }
        } catch (IOException e) {
            System.out.println("Во время попытки отправить запрос произошла ошибка");
            waitForReconnection();
        }
    }

    private static ResponseWithTreeSet hookUpdateResponse() throws IOException {
        ResponseWithTreeSet response = (ResponseWithTreeSet) waitForResponse();
        if (response != null && response.getLabWork() != null) {
            sender.clearInBuffer();
            return response;
        }
        return null;
    }


    private static Object waitForResponse() throws IOException {
        int seconds = 0;
        long start = System.currentTimeMillis();
        while (seconds < TIMEOUT) {
            if (sender.checkForMessage()) {
                Object received = sender.getPayload();
                if (received instanceof ResponseWithTreeSet) {
                    return received;
                } else if (received instanceof ResponseWithBooleanType) {
                    return received;
                } else if (received instanceof ResponseWithLabWork) {
                    return received;
                } else if (received instanceof Response) {
                    return received;
                }
            }
            if (System.currentTimeMillis() >= start + (long) (seconds + 1) * MILLIS_IN_SECONDS) {
                seconds++;
            }
        }
        return null;
    }


    private static void waitForReconnection() {
        System.out.println("Попытка переподключения");
        start();
    }


    public static ResponseWithBooleanType authentication(String command, String userName, String password) {
        client = new Authentication();
        try {
            client.setUserName(userName);
        } catch (RangeException e) {
            return new ResponseWithBooleanType(AuthorizationError.NAME_LENGTH, false, client);

        } catch (MustBeNotEmptyException e) {
            return new ResponseWithBooleanType(AuthorizationError.EMPTY, false, client);

        }
        try {
            client.setPassword(password);
        } catch (RangeException e) {
            return new ResponseWithBooleanType(AuthorizationError.PASSWORD_LENGTH, false, client);

        } catch (MustBeNotEmptyException e) {
            return new ResponseWithBooleanType(AuthorizationError.EMPTY, false, client);
        }
        try {
            sender.sendAuth(command, client);
            return hookResponseWithBooleanType();
        } catch (IOException e) {
            return new ResponseWithBooleanType(AuthorizationError.ERROR, false, client);

        }
    }

    private static ResponseWithBooleanType hookResponseWithBooleanType() throws IOException {
        ResponseWithBooleanType responseWithBooleanType = (ResponseWithBooleanType) waitForResponse();
        if (responseWithBooleanType != null) {
            if (responseWithBooleanType.getClient().getId() != null) client = responseWithBooleanType.getClient();
            sender.clearInBuffer();
            return responseWithBooleanType;
        } else System.out.println("С сервера ничего не пришло");
        return null;
    }

    public static void start() {
        try {
            SocketChannel socket = SocketChannel.open();
            if (!connection(socket)) {
                System.out.println("сервер не отвечает");
                return;
            }
            socket.configureBlocking(false);
            sender.setSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static boolean connection(SocketChannel socket) {
        int second = 0;
        long start = System.currentTimeMillis();
        while (second < TIMEOUT) {
            try {
                socket.socket().connect(address, TIMEOUTMS);
                return true;
            } catch (IOException | IllegalBlockingModeException e) {
                if (System.currentTimeMillis() >= start + (long) (second + 1) * MILLIS_IN_SECONDS) {
                    System.out.print(".");
                    second++;
                }
            }
        }
        System.out.println("Время подключение вышло");
        return false;
    }
}
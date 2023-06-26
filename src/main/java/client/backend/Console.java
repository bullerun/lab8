package client.backend;

import client.UI.controllers.MainFormController;
import client.UI.controllers.VisualizerFormController;
import client.backend.tableHandlers.TableViewHandler;
import common.*;
import javafx.geometry.Pos;
import common.data.LabWork;
import common.exception.MustBeNotEmptyException;
import common.exception.RangeException;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
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
    public static Thread serverListener;
    private final Scanner scanner;
    private static InetSocketAddress address;
    private static ScriptReader scriptReader;

    public Console(Scanner scanner, ScriptReader scriptReader) {
        this.scanner = scanner;
        this.sender = new Sender();
        this.scriptReader = scriptReader;
    }

    public static void setAddress(InetSocketAddress address) {
        Console.address = address;
    }

    public static void show() {
        try {
            sender.sendMessage(new String[]{"show", ""}, client);
        } catch (IOException ignored) {
        }
    }

    public static void add(LabWork labWork) {
        try {
            sender.sendMessageWithLabWork("add", labWork, client);
            ResponseWithTreeSet response = hookUpdateResponse();
            if (response != null && response.getLabWork() != null) {
                TableViewHandler tableViewHandler = MainFormController.getMainFormController().getTableViewHandler();
                tableViewHandler.initializeData(response.getLabWork());
                Notifications.create().position(Pos.TOP_CENTER).text(response.getResponse().toString()).show();
            }
        } catch (IOException ignored) {
            Notifications.create().position(Pos.TOP_CENTER).text(ResponseStatusEnum.LAB_NOT_ADDED.toString()).show();
        }
    }

    public static void update(LabWork labWork) {
        try {
            sender.sendMessageWithLabWork("update", labWork, client);

            ResponseWithTreeSet response = hookUpdateResponse();
            if (response != null && response.getLabWork() != null) {
                Notifications.create().position(Pos.TOP_CENTER).text(response.getResponse().toString()).show();
                TableViewHandler tableViewHandler = MainFormController.getMainFormController().getTableViewHandler();
                tableViewHandler.initializeData(response.getLabWork());
                VisualizerFormController.step = 0;
            } else {
                Notifications.create().position(Pos.TOP_CENTER).text(ResponseStatusEnum.BAD_UPDATE.toString()).show();
            }
        } catch (IOException e) {
            Notifications.create().position(Pos.TOP_CENTER).text(ResponseStatusEnum.BAD_UPDATE.toString()).show();
        }
    }

    public static void execute_script(String absolutePath) {
        scriptReader.scriptReader(absolutePath);
        if (!scriptReader.getCommands().isEmpty()) {
            try {
                sender.sendMessageWithCommands(scriptReader.getCommands(), client);
                scriptReader.clearCommands();
                ResponseExecuteScript response = (ResponseExecuteScript) waitForResponse();
                assert response != null;
                for (ResponseStatusEnum i : response.getResponse()) {
                    Notifications.create().position(Pos.TOP_CENTER).text(i.toString()).show();
                }
            } catch (IOException e) {
                Notifications.create().position(Pos.TOP_CENTER).text(ResponseStatusEnum.READING_ERROR.toString()).show();
            }
        }
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
                    if (response.getArgs() != null) {
                        ArrayList<String> args = response.getArgs();
                        if (response.getArgs().size() == 3) {
                            Notifications.create().position(Pos.TOP_CENTER).text(String.format(response.getResponse().toString(), args.get(0), args.get(1), args.get(2))).show();
                        } else {
                            Notifications.create().position(Pos.TOP_CENTER).text(String.format(response.getResponse().toString(), args.get(0))).show();
                        }
                    } else {
                        Notifications.create().position(Pos.TOP_CENTER).text(response.getResponse().toString()).show();
                    }
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
                    sender.clearInBuffer();
                    return received;
                } else if (received instanceof ResponseWithBooleanType) {
                    sender.clearInBuffer();
                    return received;
                } else if (received instanceof ResponseWithLabWork) {
                    sender.clearInBuffer();
                    return received;
                } else if (received instanceof ResponseExecuteScript) {
                    sender.clearInBuffer();
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
            ResponseWithBooleanType response = hookResponseWithBooleanType();
            System.out.println(response.getClient().getId());
            return response;
        } catch (IOException e) {
            return new ResponseWithBooleanType(AuthorizationError.ERROR, false, client);

        }
    }

    public static void startServerListener() {
        serverListener = new Thread(() -> {
            ServerListener serverListener = new ServerListener(sender);
            serverListener.checkUpdating();
        });
        serverListener.start();
    }

    public static void shotDown() {
        try {
            serverListener.interrupt();

        } catch (Exception ignored) {
        }
    }

    private static ResponseWithBooleanType hookResponseWithBooleanType() throws IOException {
        ResponseWithBooleanType responseWithBooleanType = (ResponseWithBooleanType) waitForResponse();
        if (responseWithBooleanType != null) {
            if (responseWithBooleanType.getClient().getId() != null) client = responseWithBooleanType.getClient();
            return responseWithBooleanType;
        } else System.out.println("С сервера ничего не пришло");
        return null;
    }

    public static boolean start() {
        try {
            SocketChannel socket = SocketChannel.open();
            if (connection(socket)) {
                socket.configureBlocking(false);
                sender.setSocket(socket);
                return true;
            }
            return false;
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

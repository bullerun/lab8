package server;

import common.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

public class ClientInServer {
    private final MessageHandler client;
    private boolean running;
    private final Logger logger;
    private final SelectorResponse selectorResponse;

    private final ExecutorService selectorCommand;
    ClientInServer(MessageHandler socket, Logger logger, SelectorResponse selectorResponse, ExecutorService selectorCommand) {
        this.client = socket;
        this.logger = logger;
        running = true;
        this.selectorResponse = selectorResponse;
        this.selectorCommand = selectorCommand;
    }

    void stop() {
        running = false;
        logger.info("клиент  " + client.getSocket().getRemoteSocketAddress() + " был отключен");
        try {
            client.getSocket().close();
        } catch (IOException e) {
            logger.severe("не получилось разорвать соединение с клиентом " + client.getSocket().getRemoteSocketAddress() + e);
        }
    }

    public void handleRequests() {
        while (running) {
            try {
                if (client.checkForMessage()) {
                    Object message = client.getMessage();
                    if (message instanceof Request) {
                        logger.info("началась обработка команды");
                        selectorCommand.submit(() -> {
                            sendResponse(selectorResponse.selectCommand(((Request) message).getCommands(), ((Request) message).getClient()));
                        });
                        logger.info("закончилась обработка команды");

                    } else if (message instanceof RequestWithLabWork) {
                        logger.info("добавление лабораторной работы");
                        selectorCommand.submit(() -> {
                            sendResponse(selectorResponse.selectCommand(((RequestWithLabWork) message).getCommands(), ((RequestWithLabWork) message).getLabWork(), ((RequestWithLabWork) message).getClient()));
                        });
                    } else if (message instanceof RequestWithCommands) {
                        logger.info("началась обработка скрипта");
                        selectorCommand.submit(() -> {
                            sendResponse(selectorResponse.selectWithCommands(((RequestWithCommands) message).getName(), ((RequestWithCommands) message).getCommands(), ((RequestWithCommands) message).getClient()));
                        });
                        logger.info("закончилась обработка скрипта");
                    } else if (message instanceof RequestWithClient) {
                        logger.info("подключение нового клиента");
                        selectorCommand.submit(() -> {
                            sendResponse(selectorResponse.selectAuthCommand(((RequestWithClient) message).getCommand(), ((RequestWithClient) message).getClient()));
                        });
                    } else if (message instanceof RequestUpdate) {
                        logger.info("началась обработка команды");
                        selectorCommand.submit(() -> {
                            sendResponse(selectorResponse.update(((RequestUpdate) message).getCommands(), ((RequestUpdate) message).getClient()));
                        });
                        logger.info("закончилась обработка команды");
                    }
                    client.clearBuffer();
                }
            } catch (IOException | ClassNotFoundException e) {
                stop();
            }
        }

    }

    private void sendResponse(Object response) {
        new Thread(() -> {
            try {
                client.sendResponse(response);
                logger.info("ответ клиенту отправлен");
            } catch (IOException e) {
                logger.info("ответ клиенту не удалось отправить");
                stop();
            }
        }).start();
    }
}
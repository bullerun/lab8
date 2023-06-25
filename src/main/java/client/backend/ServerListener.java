package client.backend;

import client.UI.controllers.MainFormController;
import client.backend.tableHandlers.TableViewHandler;
import common.Response;
import common.ResponseWithBooleanType;
import common.ResponseWithLabWork;
import common.ResponseWithTreeSet;

import java.io.IOException;

public class ServerListener {
    private Sender sender;

    private static final int TIMEOUT = 3;
    private static final int TIMEOUTMS = 5000;
    private static final int MILLIS_IN_SECONDS = 1000;

    public ServerListener(Sender sender) {
        this.sender = sender;
    }

    public void checkUpdating() {
        System.out.println(2);

        while (true) {
            System.out.println(3);
            Console.show();
            waitResponse();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void waitResponse() {
        int seconds = 0;
        long start = System.currentTimeMillis();
        try {
            while (seconds < TIMEOUT) {
                if (sender.checkForMessage()) {
                    Object received = sender.getPayload();
                    if (received instanceof ResponseWithTreeSet) {
                        updatingTable((ResponseWithTreeSet) received);
                        sender.clearInBuffer();
                        return;
                    }
                }
                if (System.currentTimeMillis() >= start + (long) (seconds + 1) * MILLIS_IN_SECONDS) {
                    seconds++;
                }
            }
        } catch (IOException ignored) {
        }

    }


    private void updatingTable(ResponseWithTreeSet response) {
        System.out.println(5);
        TableViewHandler tableViewHandler = MainFormController.getMainFormController().getTableViewHandler();
        tableViewHandler.initializeData(response.getLabWork());
    }

}

package client.backend;

import client.UI.controllers.MainFormController;
import client.backend.tableHandlers.TableViewHandler;
import common.Response;
import common.ResponseWithBooleanType;
import common.ResponseWithLabWork;
import common.ResponseWithTreeSet;
import common.data.LabWork;

import java.io.IOException;
import java.util.Iterator;
import java.util.NavigableSet;

public class ServerListener {
    private Sender sender;

    private static final int TIMEOUT = 3;
    private static final int TIMEOUTMS = 5000;
    private static final int MILLIS_IN_SECONDS = 1000;
    private NavigableSet<LabWork> labs;

    public ServerListener(Sender sender) {
        this.sender = sender;
    }

    public void checkUpdating() {
        while (true) {
            Console.show();
            waitResponse();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
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
                        sender.clearInBuffer();
                        updatingTable((ResponseWithTreeSet) received);
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

        NavigableSet<LabWork> newLabs = response.getLabWork();
        try {
            if (newLabs.size() != labs.size()) {
                showNewData(newLabs);
            }

            if (newLabs != null) {
                Iterator<LabWork> i = newLabs.iterator();
                Iterator<LabWork> j = labs.iterator();
                for (int k = 0; k < newLabs.size(); k++) {
                    LabWork newLab = i.next();
                    LabWork oldLab = j.next();
                    if (!newLab.equals(oldLab)) {
                        showNewData(newLabs);
                        return;
                    }
                }
            }
        } catch (NullPointerException e) {
            showNewData(newLabs);
        }
    }

    private void showNewData(NavigableSet<LabWork> newLabs) {
        labs = newLabs;
        TableViewHandler tableViewHandler = MainFormController.getMainFormController().getTableViewHandler();
        tableViewHandler.initializeData(newLabs);
    }
}

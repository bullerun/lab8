package common;

import common.data.LabWork;

import java.io.Serializable;

public class RequestWithLabWork implements Serializable {
    private final String command;
    private final LabWork labWork;
    private final Authentication client;

    public RequestWithLabWork(String command, LabWork labWork, Authentication client) {
        this.command = command;
        this.labWork = labWork;
        this.client = client;
    }

    public String getCommands() {
        return command;
    }

    public LabWork getLabWork() {
        return labWork;
    }

    public Authentication getClient() {
        return client;
    }
}
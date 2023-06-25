package common;

import java.io.Serializable;

public class Request implements Serializable {
    private final String[] commands;
    private final Authentication client;

    public Request(String[] commands, Authentication client) {
        this.commands = commands;
        this.client = client;
    }

    public String[] getCommands() {
        return commands;
    }

    public Authentication getClient() {
        return client;
    }
}

package common;

import java.io.Serializable;

public class RequestUpdate implements Serializable {

    private final String[] commands;
    private final Authentication client;

    public RequestUpdate(String[] commands, Authentication client) {
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

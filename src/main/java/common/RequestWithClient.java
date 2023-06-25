package common;

import java.io.Serializable;

public class RequestWithClient implements Serializable {
    private final String command;
    private final Authentication client;

    public RequestWithClient(String command, Authentication client) {
        this.command = command;
        this.client = client;
    }

    public String getCommand() {
        return command;
    }

    public Authentication getClient() {
        return client;
    }
}

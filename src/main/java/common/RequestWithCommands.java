package common;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestWithCommands implements Serializable {
    private final ArrayList<String> commands;
    private final String name;
    private final Authentication client;
    public RequestWithCommands(ArrayList<String> commands, Authentication client){
        this.name = "execute_script";
        this.commands = commands;
        this.client = client;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }
    public Authentication getClient(){
        return client;
    }
}

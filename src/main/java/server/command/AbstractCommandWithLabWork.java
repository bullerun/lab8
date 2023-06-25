package server.command;

public abstract class AbstractCommandWithLabWork implements CommandWithLabWork{
    private final String name;

    public AbstractCommandWithLabWork(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }
}

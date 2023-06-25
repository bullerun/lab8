package server.command;

/**
 * abstract class that is the parent of all commands
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public abstract class AbstractCommand implements Command{
    private final String name;
    private final String description;
    public AbstractCommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}

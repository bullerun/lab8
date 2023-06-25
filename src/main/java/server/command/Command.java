package server.command;

import common.ResponseWithTreeSet;

/**
 * interface from which all commands are implemented
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public interface Command {
    String getDescription();
    String getName();
    ResponseWithTreeSet execute(String argument, Long id);
}

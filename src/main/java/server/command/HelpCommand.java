package server.command;

import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.exception.MustBeEmptyException;

import java.util.ArrayList;

/**
 * command outputs help on
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class HelpCommand extends AbstractCommand {
    private final ArrayList<Command> commandsForHelpCommand;

    public HelpCommand(ArrayList<Command> commandsForHelpCommand) {
        super("help", "выводит справку по доступным командам");
        this.commandsForHelpCommand = commandsForHelpCommand;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            StringBuilder s = new StringBuilder();
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            for (Command i : commandsForHelpCommand) {
                s.append("Команда ").append(i.getName()).append(" ").append(i.getDescription()).append("\n");
            }
//            исправить
            return new ResponseWithTreeSet(ResponseStatusEnum.ERROR, null);
        } catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.MUST_BE_WITHOUT_AN_ARGUMENT, null);
        }
    }
}

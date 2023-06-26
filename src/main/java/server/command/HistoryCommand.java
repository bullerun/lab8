package server.command;

import common.ResponseStatusEnum;
import common.ResponseWithTreeSet;
import common.exception.MustBeEmptyException;

import java.util.ArrayList;
/**
 * command outputs the last 9 entered commands
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class HistoryCommand extends AbstractCommand {
    private final ArrayList<String> lastCommands;
    public HistoryCommand(ArrayList<String> lastCommands) {
        super("history", "выводит последние 9 команд (без их аргументов)");
        this.lastCommands = lastCommands;
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        try {
            StringBuilder s = new StringBuilder();
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            for (int i = lastCommands.size() -1; i >= 0; i--) {
                s.append(lastCommands.get(i)).append("\n");
            }
            //исправить
            return new ResponseWithTreeSet(ResponseStatusEnum.ERROR, null, null);
        }catch (MustBeEmptyException e) {
            return new ResponseWithTreeSet(ResponseStatusEnum.MUST_BE_WITHOUT_AN_ARGUMENT, null, null);

        }
    }
}

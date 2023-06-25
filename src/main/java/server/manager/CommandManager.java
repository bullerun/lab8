package server.manager;


import common.Response;
import common.ResponseWithLabWork;
import common.ResponseWithTreeSet;
import common.data.LabWork;
import server.command.*;
import server.utility.LabAsk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * class for interacting with commands
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class CommandManager {
    private Scanner localScanner;
    ArrayList<Command> commandsForHelpCommand = new ArrayList<>();
    ArrayList<String> lastCommands = new ArrayList<>();
    Map<String, Command> commands = new HashMap<>();
    Map<String, CommandWithLabWork> commandsWithLabWork = new HashMap<>();
    private final ExitCommand exitCommand;
    private final UpdateByIdCommand update;
    private final LabAsk labAsk;

    public CommandManager(CollectionManager collectionManager, LabAsk labAsk, SqlCollectionManager sqlCollectionManager) {
        this.labAsk = labAsk;
        localScanner = new Scanner("");
        commandsForHelpCommand.add(new InfoCommand(collectionManager));
//        commandsForHelpCommand.add(new HistoryCommand(lastCommands));
        commandsForHelpCommand.add(new ShowCommand(collectionManager));
        commandsForHelpCommand.add(new AddCommand(collectionManager, labAsk, sqlCollectionManager));
        commandsForHelpCommand.add(new RemoveByIdCommand(collectionManager, sqlCollectionManager));
        commandsForHelpCommand.add(new SumOfMinimalPointCommand(collectionManager));
        commandsForHelpCommand.add(new AverageOfMinimalPointCommand(collectionManager));
        commandsForHelpCommand.add(new ClearCommand(collectionManager, sqlCollectionManager));
        commandsForHelpCommand.add(new ExecuteScriptCommand());
        commandsForHelpCommand.add(new PrintFieldDescendingDisciplineCommand(collectionManager));
        commandsForHelpCommand.add(new RemoveGreaterCommand(collectionManager, sqlCollectionManager));
        commandsForHelpCommand.add(new RemoveLowerCommand(collectionManager, sqlCollectionManager));

//        commandsForHelpCommand.add(new HelpCommand(commandsForHelpCommand));
        commandsWithLabWork.put("add", new AddCommandWithLabWork(collectionManager, sqlCollectionManager));
        commandsWithLabWork.put("update", new UpdateByIdCommandWithLabWork(collectionManager, sqlCollectionManager));
//        commands.put("help", new HelpCommand(commandsForHelpCommand));
        commands.put("info", new InfoCommand(collectionManager));
//        commands.put("history", new HistoryCommand(lastCommands));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, labAsk, sqlCollectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, sqlCollectionManager));
        commands.put("sum_of_minimal_point", new SumOfMinimalPointCommand(collectionManager));
        commands.put("average_of_minimal_point", new AverageOfMinimalPointCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager, sqlCollectionManager));
        commands.put("print_field_descending_discipline", new PrintFieldDescendingDisciplineCommand(collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager, sqlCollectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, sqlCollectionManager));
        update = new UpdateByIdCommand(collectionManager);
        exitCommand = new ExitCommand();
    }


    public ResponseWithTreeSet commandSelection(String[] command, Long client) {

        return commands.get(command[0]).execute(command[1], client);
    }
    public ResponseWithLabWork updateCommand(String[] command, Long client) {

        return update.execute(command[1], client);
    }
    public ResponseWithTreeSet commandSelection(String command, LabWork labWork, Long client) {

        return commandsWithLabWork.get(command).execute(labWork, client);
    }

    public Response commandSelectionFromScript(String command, ArrayList<String> commands, Long client) {
        addCommandHistory(command);
        String stringWithCommands = String.join("\n", commands);
        setScanner(new Scanner(stringWithCommands));
        return new Response(String.join("\n", localConsole(client)) + "\nКоманда execute_script выполнена");
    }
    public ArrayList<String> localConsole(Long client) {
        ArrayList<String> response = new ArrayList<>();
        String[] command;
        while (localScanner.hasNext()) {
            command = (localScanner.nextLine().trim() + " ").split(" ", 2);
            if (!command[0].equals("")) {
                if (command[0].equals("add")) {
                    labAsk.setScanner(getScanner());
                }
                Command commandSelect = commands.get(command[0]);
                if (commandSelect != null) {
                    response.add(commandSelect.execute(command[1], client).getResponse());
                    addCommandHistory(command[0]);
                }
            }
        }
        return response;
    }

    public Scanner getScanner() {
        return localScanner;
    }

    public void setScanner(Scanner scanner) {
        localScanner = scanner;
    }

    public void addCommandHistory(String command) {
        lastCommands.add(command);
        if (lastCommands.size() > 9) {
            lastCommands.remove(0);
        }
    }


    public void exit() {
        addCommandHistory("exit");
        exitCommand.execute("", 0L);
    }
}

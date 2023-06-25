package server.command;


import common.ResponseWithTreeSet;

/**
 * command reading and executing the script
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class ExecuteScriptCommand extends AbstractCommand {


    public ExecuteScriptCommand() {
        super("execute_script file_name", "считывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    @Override
    public ResponseWithTreeSet execute(String argument, Long client) {
        return null;
    }
}
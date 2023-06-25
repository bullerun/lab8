package server.command;


import common.ResponseWithLabWork;
import server.manager.CollectionManager;


public class UpdateByIdCommand {
    CollectionManager collectionManager;

    public UpdateByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;

    }

    public ResponseWithLabWork execute(String argument, Long client) {
        return new ResponseWithLabWork("Выберете что вы хотите изменить", collectionManager.getElementById(Long.parseLong(argument), client));
    }
}
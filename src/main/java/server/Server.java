package server;

import server.manager.CollectionManager;

import java.io.IOException;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Нужно ввести название таблицы и пароль");
        }
        Scanner scanner = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager();
        ServerInstance serverInstance = new ServerInstance(10644, collectionManager, scanner, args[0], args[1]);
        serverInstance.run();
    }
}

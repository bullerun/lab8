package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.backend.Console;
import client.backend.ScriptReader;
import client.backend.ValidationChecker;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AuthorizationForm.fxml"));
        primaryStage = stage;
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Lab8");
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setOpacity(1);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов, введите хост и порт");
            return;
        }
        try {
            int port = Integer.parseInt(args[1]);
            String host;
            if (args[0].contains("/")) host = args[0].split("/")[1];
            else host = args[0];
            InetSocketAddress addr = new InetSocketAddress(InetAddress.getLocalHost(), port);
            Scanner scanner = new Scanner(System.in);
            ScriptReader scriptReader = new ScriptReader();
            Console console = new Console(scanner, scriptReader);
            Console.setAddress(addr);
            ValidationChecker validationChecker = new ValidationChecker();
            if (Console.start()) {
                launch();
                Console.shotDown();
            } else {
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный порт");
        } catch (IOException e) {
            System.out.println("Не удалось запустить приложение");
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
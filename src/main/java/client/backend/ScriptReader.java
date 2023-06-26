package client.backend;

import client.UI.resourcebundles.enums.RuntimeOutputs;
import common.exception.ScriptRecursionException;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScriptReader {


    private final List<String> nameScripts = new ArrayList<>();
    private final List<Scanner> nameScanners = new ArrayList<>();
    private final ArrayList<String> commands = new ArrayList<>();

    private boolean recursion=false;



    public void scriptReader(String scriptPath) {
        try {
            if (nameScripts.contains(scriptPath)) throw new ScriptRecursionException();
            nameScripts.add(scriptPath);
        } catch (ScriptRecursionException e) {
            System.out.println("Повторный вызов скрипта " + scriptPath);
            recursion=true;
            return;
        }
        String[] command;
        try (Scanner scriptScanner = new Scanner(new InputStreamReader(new FileInputStream(scriptPath), StandardCharsets.UTF_8))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            do {
                command = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                command[1] = command[1].trim();
                if (command[0].equals("execute_script")) {
                    scriptReader(command[1].trim());
                    if (recursion){
                        return;
                    }
                } else {
                    commands.add(command[0] +" "+ command[1]);
                }
            } while (scriptScanner.hasNextLine());
        } catch (NoSuchElementException e) {
            Notifications.create().position(Pos.TOP_CENTER).text(RuntimeOutputs.FILE_LISTENER_CAN_NOT_READ_FILE.toString()).show();

        } catch (IOException e) {
            Notifications.create().position(Pos.TOP_CENTER).text(RuntimeOutputs.FILE_LISTENER_PROBLEM_WITH_SCRIPT_FILE.toString()).show();
        } catch (IllegalArgumentException e) {
            Notifications.create().position(Pos.TOP_CENTER).text(RuntimeOutputs.FIELDS_DOES_NOT_VALID.toString()).show();
        } finally {

            if (nameScripts.size() > 0) {
                nameScripts.remove(nameScripts.size() - 1);
            }
        }
    }
    public ArrayList<String> getCommands() {
        return commands;
    }

    public void clearCommands() {
        nameScripts.clear();
        commands.clear();
    }
}

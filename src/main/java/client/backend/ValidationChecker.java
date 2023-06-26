package client.backend;


import common.Response;
import common.ResponseStatusEnum;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

import java.util.HashSet;

public class ValidationChecker {
    private static final HashSet<String> commandWithoutArg = new HashSet<>();
    private static final HashSet<String> commandWithArg = new HashSet<>();
    public ValidationChecker() {
        commandWithoutArg.add("help");
        commandWithoutArg.add("info");
        commandWithoutArg.add("history");
        commandWithoutArg.add("show");
        commandWithoutArg.add("clear");
        commandWithoutArg.add("sum_of_minimal_point");
        commandWithoutArg.add("average_of_minimal_point");
        commandWithoutArg.add("print_field_descending_discipline");
        commandWithArg.add("remove_by_id");
        commandWithArg.add("update");
        commandWithArg.add("remove_greater");
        commandWithArg.add("remove_lower");

    }

    public static boolean checkValidation(String[] command) {
        try {
            if (commandWithoutArg.contains(command[0])) {
                if (!command[1].isEmpty()) {
                    Notifications.create().position(Pos.TOP_CENTER).text(ResponseStatusEnum.MUST_BE_WITHOUT_AN_ARGUMENT.toString()).show();
                    return false;
                }
                return true;
            } else if (commandWithArg.contains(command[0])) {
                if (command[1].isEmpty()) {
                    Notifications.create().position(Pos.TOP_CENTER).text(ResponseStatusEnum.LACK_OF_ARGUMENT.toString()).show();
                    return false;
                }
                Long i = Long.parseLong(command[1]);
                return true;
            }
        } catch (NumberFormatException e) {
            Notifications.create().position(Pos.TOP_CENTER).text(ResponseStatusEnum.INVALID_ARGUMENT.toString()).show();
            return false;
        }
        return false;
    }


}

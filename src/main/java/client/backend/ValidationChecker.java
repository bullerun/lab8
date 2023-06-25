package client.backend;


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
                    System.out.println("У команды " + command[0] + " не должно быть аргумента");
                    return false;
                }
                return true;
            } else if (commandWithArg.contains(command[0])) {
                if (command[1].isEmpty()) {
                    System.out.println("У команды " + command[0] + " должен быть аргумента");
                    return false;
                }
                Long i = Long.parseLong(command[1]);
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("некорректно введено число, число должно содержать только цифры и должно быть меньше или равно " + Long.MAX_VALUE);
            return false;
        }
        System.out.println("команда не найдена");
        return false;
    }


}

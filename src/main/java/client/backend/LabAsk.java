package client.backend;

import common.data.*;
import common.exception.*;

import java.util.Scanner;

/**
 * auxiliary class for working with an instance of the LabWork class
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class LabAsk {
    private final Long MINIMAL_POINT = 0L;
    private final float MINIMAL_X_COORDINATES = -18;
    private Scanner scanner;

    private LabWork labWork;


    public LabAsk(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }


    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }

    public LabWork addLabWork() {
        this.labWork = new LabWork();
        nameAsk();
        coordinatesAsk();
        minimalPointAsk();
        difficultyAsk();
        disciplineAsk();
        return labWork;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void nameAsk() {
        while (true) {
            try {
                System.out.println("Введите название лабораторной");
                labWork.setName(scanner.nextLine().trim());
                break;
            } catch (MustBeNotEmptyException e) {
                System.out.println("Название не должно быть пустым");
            }
        }
    }

    public void coordinatesAsk() {
        XAsk(labWork.getCoordinates());
        YAsk(labWork.getCoordinates());
    }

    public void XAsk(Coordinates coordinates) {
        while (true) {
            try {
                System.out.println("Введите X координату она должна быть больше " + MINIMAL_X_COORDINATES);
                coordinates.setX(Float.parseFloat(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("при вводе не должно быть ничего кроме цифр и '.'");

            } catch (RangeException e) {
                System.out.println("X должен быть больше -18");

            } catch (IllegalArgumentException e) {
                System.out.println("Слишком большое число");

            }
        }
    }

    public void YAsk(Coordinates coordinates) {
        while (true) {
            try {
                System.out.println("Введите Y координату");
                coordinates.setY(Long.parseLong(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("некорректно введено число, число должно содержать только цифры и должно быть меньше или равно " + Long.MAX_VALUE);

            }
        }
    }

    public void minimalPointAsk() {
        while (true) {
            try {
                System.out.println("Введите минимальный балл он должен быть больше " + MINIMAL_POINT);
                labWork.setMinimalPoint(Long.parseLong(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("некорректно введено число, число должно содержать только цифры и должно быть меньше или равно " + Long.MAX_VALUE);

            } catch (RangeException e) {
                System.out.println("минимальный балл должен быть больше 0");

            }
        }
    }

    public void difficultyAsk() {
        while (true) {
            try {
                System.out.println("Выберите сложность - " + Difficulty.allDifficulty());
                labWork.setDifficulty(scanner.nextLine().trim().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("нет такой сложности, повторите ввод");
            }
        }
    }

    public void disciplineAsk() {
        while (true) {
            try {
                System.out.println("Вы хотите добавить дисциплину, введите 'yes', если да, no' или 'enter', если нет?");
                String line = scanner.nextLine().trim();
                if (line.equals("") | line.equals("no")) {
                    labWork.setDiscipline(null);
                    break;
                }
                if (line.equals("yes")) {
                    if (labWork.getDiscipline() == null) {
                        labWork.setDiscipline(new Discipline());
                    }
                    nameDisciplineAsk(labWork.getDiscipline());
                    practiceHoursDisciplineAsk(labWork.getDiscipline());
                    break;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод");

            }
        }

    }

    public void nameDisciplineAsk(Discipline discipline) {
        while (true) {
            try {
                System.out.println("Введите название дисциплины");
                discipline.setName(scanner.nextLine().trim());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод");

            }
        }
    }

    public void practiceHoursDisciplineAsk(Discipline discipline) {
        while (true) {
            try {
                System.out.println("Введите количество часов");
                discipline.setPracticeHours(Integer.parseInt(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("некорректно введено число, число должно содержать только цифры и должно быть меньше или равно " + Integer.MAX_VALUE);

            }
        }
    }

    public boolean updateById(String ask) {
        while (true) {
            try {
                String answer;
                System.out.println(ask);
                System.out.println("Если да, введите 'yes', если нет, то введите 'no' или нажмите 'enter'");
                answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("yes")) return true;
                if (answer.equals("") | answer.equals("no")) return false;
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод, введите только 'yes', если хотите сделать изменения, или 'no', если не хотите");
            }
        }
    }

    public LabWork getLabWork() {
        return labWork;
    }
}
package server.utility;

import common.data.Coordinates;
import common.data.Discipline;
import common.data.LabWork;
import common.exception.MustBeNotEmptyException;
import common.exception.RangeException;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * auxiliary class for working with an instance of the LabWork class
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class LabAsk {
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

    public LabWork addLabWork() throws NoSuchElementException {
        this.labWork = new LabWork();
        nameAsk();
        coordinatesAsk();
        minimalPointAsk();
        difficultyAsk();
        disciplineAsk();
        return labWork;
    }

    public void nameAsk() throws NoSuchElementException {
        while (true) {
            try {
                labWork.setName(scanner.nextLine().trim());
                break;
            } catch (MustBeNotEmptyException ignored) {
            }
        }
    }

    public void coordinatesAsk() throws NoSuchElementException {
        XAsk(labWork.getCoordinates());
        YAsk(labWork.getCoordinates());
    }

    public void XAsk(Coordinates coordinates) throws NoSuchElementException {
        while (true) {
            try {
                coordinates.setX(Float.parseFloat(scanner.nextLine().trim()));
                break;
            } catch (RangeException | IllegalArgumentException ignored) {
            }
        }
    }

    public void YAsk(Coordinates coordinates) throws NoSuchElementException {
        while (true) {
            try {
                coordinates.setY(Long.parseLong(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public void minimalPointAsk() throws NoSuchElementException {
        while (true) {
            try {
                labWork.setMinimalPoint(Long.parseLong(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException | RangeException ignored) {
            }
        }
    }

    public void difficultyAsk() throws NoSuchElementException {
        while (true) {
            try {

                labWork.setDifficulty(scanner.nextLine().trim().toUpperCase());
                break;
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    public void disciplineAsk() throws NoSuchElementException {
        nameDisciplineAsk(labWork.getDiscipline());
        practiceHoursDisciplineAsk(labWork.getDiscipline());
    }

    public void nameDisciplineAsk(Discipline discipline) throws NoSuchElementException {
        while (true) {
            try {
                discipline.setName(scanner.nextLine().trim());
                break;
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    public void practiceHoursDisciplineAsk(Discipline discipline) throws NoSuchElementException {
        while (true) {
            try {
                discipline.setPracticeHours(Integer.parseInt(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException ignored) {
            }
        }
    }
}

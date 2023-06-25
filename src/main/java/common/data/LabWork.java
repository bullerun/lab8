package common.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import common.exception.MustBeNotEmptyException;
import common.exception.RangeException;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * class of creating a laboratory work
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class LabWork implements Comparable<LabWork>, Serializable {
    private final Long MINIMAL_POINT = 0L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long minimalPoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Discipline discipline; //Поле может быть null
    private Long ownerID;

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public LabWork(Long id) {
        this.id = id;
        this.creationDate = LocalDate.now();
        this.coordinates = new Coordinates();
        this.discipline = new Discipline();
    }
    public LabWork() {
        this.creationDate = LocalDate.now();
        this.coordinates = new Coordinates();
        this.discipline = new Discipline();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long getMinimalPoint() {
        return minimalPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }
    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name) throws MustBeNotEmptyException {
        if (name.equals("")) throw new MustBeNotEmptyException();
        this.name = name;
    }
    public void setCoordinates(Coordinates coordinates){this.coordinates = coordinates;}
    public void setMinimalPoint(long minimalPoint) throws RangeException {
        if (minimalPoint <= MINIMAL_POINT) throw new RangeException();
        this.minimalPoint = minimalPoint;
    }

    public void setDifficulty(String difficulty) throws IllegalArgumentException{
        if (difficulty.equals("")) this.difficulty = null;
        else {
            this.difficulty = Difficulty.valueOf(difficulty);
        }
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setOwnerID(Long ownerID){
        this.ownerID = ownerID;
    }
    public Long getOwnerID(){
        return ownerID;
    }

    @Override
    public String toString() {
        return "id=" + id +
                " имя=" + name +
                " координаты=" + coordinates +
                " дата создания=" + creationDate +
                " минимальный балл=" + minimalPoint +
                " трудность=" + difficulty +
                " дисциплина=" + discipline +
                " owner_ID=" +ownerID;
    }

    @Override
    public int compareTo(LabWork other) {
        return id.compareTo(other.getId());
    }
}

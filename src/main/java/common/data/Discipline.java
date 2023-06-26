package common.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * an auxiliary class for working with LabWork discipline
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class Discipline implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int practiceHours;
    public Discipline(String name, int practiceHours){
        this.name = name;
        this.practiceHours = practiceHours;
    }
    public Discipline(){

    }

    public String getName() {
        return name;
    }

    public int getPracticeHours() {
        return practiceHours;
    }

    public void setName(String name){
        if (name.equals("")) throw new IllegalArgumentException();
        this.name = name;
    }
    public void setPracticeHours(int practiceHours){
        this.practiceHours = practiceHours;
    }

    @Override
    public String toString() {
        return name + ", " + practiceHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return practiceHours == that.practiceHours && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, practiceHours);
    }
}

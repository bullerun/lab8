package common.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import common.exception.RangeException;

import java.io.Serializable;
import java.util.Objects;

/**
 * an auxiliary class for working with LabWork coordinates
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class Coordinates implements Serializable {
    private final float MINIMAL_X_COORDINATES = -18f;
    @JsonProperty("X_coordinates")
    private float x; //Значение поля должно быть больше -18
    @JsonProperty("Y_coordinates")
    private Long y; //Поле не может быть null

    public Coordinates() {
    }

    public void setX(float x) throws RangeException, IllegalArgumentException {
        if (x <= MINIMAL_X_COORDINATES) throw new RangeException();
        if (x == Float.POSITIVE_INFINITY) throw new IllegalArgumentException();
        this.x = x;
    }

    public void setY(Long y) throws IllegalArgumentException {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.x, x) == 0 && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

package client.backend.visualizationlogic.entities;

public class StraightLineEquation {
    private final double A;
    private final double B;
    private final double C;

    public StraightLineEquation(double a, double b, double c){
        this.A = a;
        this.B = b;
        this.C = c;
    }

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }
}

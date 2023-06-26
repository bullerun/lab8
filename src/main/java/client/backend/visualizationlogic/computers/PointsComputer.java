package client.backend.visualizationlogic.computers;


import client.backend.visualizationlogic.entities.StraightLineEquation;
import javafx.geometry.Point2D;

public class PointsComputer {
    public Point2D projectPoint(Point2D coordinates, StraightLineEquation straightLineEquation) {
        double a = straightLineEquation.getA();
        double b = straightLineEquation.getB();
        double c1 = straightLineEquation.getC();
        double c2 = coordinates.getY() * straightLineEquation.getA() - coordinates.getX() * straightLineEquation.getB();

        double x = (-straightLineEquation.getA() * c1 - c2 * b) / (a * a + b * b);
        double y = (-c1 - a * x) / b;

        return new Point2D(x, y);
    }

    public boolean isPointAndCenterTogether(Point2D center, Point2D point, StraightLineEquation controlLine){
        double centerPos = controlLine.getA() * center.getX() + controlLine.getB() * center.getY() + controlLine.getC();
        double pointPos = controlLine.getA() * point.getX() + controlLine.getB() * point.getY() + controlLine.getC();
        return (centerPos > 0 && pointPos >= 0) || (centerPos < 0 && pointPos <= 0);
    }
}

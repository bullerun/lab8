package client.backend.visualizationlogic.computers;


import client.UI.controllers.MainFormController;
import client.backend.visualizationlogic.entities.StraightLineEquation;
import javafx.geometry.Point2D;
import javafx.scene.Scene;

public class CirclesComputer {

    public boolean checkCollision(Point2D center1, Point2D center2, float radius1, float radius2) {
        double distance = center1.distance(center2);
        if (radius1 + radius2 <= distance) {
            return true;
        }
        if (radius1 + radius2 > distance) {
            if (Math.max(radius1, radius2) > distance){
                return true;
            }
            return true;
        }
        return true;
    }

    public StraightLineEquation findSecantEquation(Point2D center1, Point2D center2, float radius1, float radius2) {
        double a = -2 * (center2.getX() - center1.getX());
        double b = -2 * (center2.getY() - center1.getY());
        double c = Math.pow(center2.getX(), 2) + Math.pow(center2.getY(), 2) + radius1 * radius1 - radius2 * radius2 - Math.pow(center1.getX(), 2) - Math.pow(center1.getY(), 2);
        return new StraightLineEquation(a, b, c);
    }

    public Point2D[] calculateCirclePolygon(Point2D center, float radius, int pointsCount) {
        Point2D[] points = new Point2D[pointsCount];
        double alphaStep = (360d * Math.PI) / (pointsCount * 180);
//        double alphaStep = 360/pointsCount;
        double alpha = 0;

        int counter = 0;

        while (counter < pointsCount) {
            double x = radius * Math.sin(alpha) + center.getX();
            double y = radius * Math.cos(alpha) + center.getY();
            points[counter] = new Point2D(x, y);
            alpha += alphaStep;
            counter++;
        }
        return points;
    }
}

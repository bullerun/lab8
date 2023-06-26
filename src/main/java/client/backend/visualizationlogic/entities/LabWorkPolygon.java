package client.backend.visualizationlogic.entities;

import common.data.LabWork;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;


import java.util.List;

public class LabWorkPolygon extends Polygon {
    private final LabWork labWork;
    private final Point2D center;
    private final float RADIUS;

    public LabWorkPolygon(LabWork labWork, Point2D center, float radius) {
        this.labWork = labWork;
        this.center = center;
        this.RADIUS = radius;
    }

    public void updatePoints(Point2D[] points) {
        List<Double> pointsCollection = getPoints();
        pointsCollection.clear();
        for (int i = 0; i < points.length; i++) {
            pointsCollection.add(points[i].getX());
            pointsCollection.add(points[i].getY());
        }
    }

    public Point2D[] getPointsFromPolygon() {
        Point2D[] output = new Point2D[getPoints().size() / 2];
        for (int i = 0; i < getPoints().size(); i += 2) {
            output[i / 2] = new Point2D(getPoints().get(i), getPoints().get(i + 1));
        }
        return output;
    }

    public Point2D getCenter() {
        return center;
    }

    public LabWork getlabWork() {
        return labWork;
    }


    public float getRadius() {
        return RADIUS;
    }
}

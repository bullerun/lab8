package client.backend.visualizationlogic.computers;


import client.backend.visualizationlogic.entities.LabWorkPolygon;
import client.backend.visualizationlogic.entities.StraightLineEquation;
import javafx.geometry.Point2D;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class PolygonsFacade {
    private ExecutorService cachedThreadPool;

    private static PolygonsFacade polygonsFacade;

    private PolygonsFacade(){
        cachedThreadPool = Executors.newCachedThreadPool(runnable-> {
            final Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });
    }

    public static PolygonsFacade getInstance(){
        if (polygonsFacade == null){
            polygonsFacade = new PolygonsFacade();
        }
        return polygonsFacade;
    }

    public LabWorkPolygon[] checkPolygons(LabWorkPolygon polygon1, LabWorkPolygon polygon2)  {
        Point2D[] points1 = polygon1.getPointsFromPolygon();
        Point2D[] points2 = polygon2.getPointsFromPolygon();

        CirclesComputer circlesComputer = new CirclesComputer();
    
            StraightLineEquation straightLineEquation = circlesComputer.findSecantEquation(polygon1.getCenter(), polygon2.getCenter(), polygon1.getRadius(), polygon2.getRadius());
            Future<?> future1 = cachedThreadPool.submit(()->checkPoints(points1, polygon1.getCenter(), straightLineEquation));
            Future<?> future2 = cachedThreadPool.submit(()->checkPoints(points2, polygon2.getCenter(), straightLineEquation));
            try {
                future1.get();
                future2.get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Points calculating thread was interrupted!");
            }

        polygon1.updatePoints(points1);
        polygon2.updatePoints(points2);
        return new LabWorkPolygon[]{polygon1, polygon2};
    }

    public LabWorkPolygon checkBorder(LabWorkPolygon polygon1, StraightLineEquation straightLineEquation)  {
        Point2D[] points1 = polygon1.getPointsFromPolygon();

        checkPoints(points1, polygon1.getCenter(), straightLineEquation);
        polygon1.updatePoints(points1);
        return polygon1;
    }

    private void checkPoints(Point2D[] points, Point2D center, StraightLineEquation straightLineEquation){
        PointsComputer pointsComputer = new PointsComputer();
        for(int i = 0; i < points.length; i++){
            if (!pointsComputer.isPointAndCenterTogether(center, points[i], straightLineEquation)){
                points[i] = pointsComputer.projectPoint(points[i], straightLineEquation);
            }
        }
    }

}

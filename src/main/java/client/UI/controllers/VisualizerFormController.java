package client.UI.controllers;

import client.Main;
import client.UI.resourcebundles.enums.VisualisationFirmElements;
import client.backend.Console;
import client.backend.LabAskStatic;
import client.backend.visualizationlogic.computers.CirclesComputer;
import client.backend.visualizationlogic.computers.PolygonsFacade;
import client.backend.visualizationlogic.entities.LabWorkPolygon;
import client.backend.visualizationlogic.entities.StraightLineEquation;
import common.data.Coordinates;
import common.data.LabWork;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class VisualizerFormController {
    private static Timeline frameTimer;
    private static Canvas canvas = new Canvas();
    private ArrayList<LabWork> collection;
    private final int CIRCLE_POLYGON_POINTS_COUNT = 100;
    private ArrayList<LabWorkPolygon> labWorkPolygons;


    private static final int CANVAS_DEFAULT_WIDTH = 580;

    private static final int CANVAS_DEFAULT_HEIGHT = 580;

    private static final int DEFAULT_STROKE_WIDTH = 10;

    private final float DEFAULT_RADIUS_VALUE = 50;
    private final static double SCALE_COMPUTING_RADIUS_MULTIPLIER = 20;
    private final double FRAMES_PER_SECOND = 60;

    private final int SECOND = 1000;
    private LabWork selectedLabwork;
    private static final Affine defaultTransform = canvas.getGraphicsContext2D().getTransform();

    @FXML
    private Button backToTableButton;

    @FXML
    private Pane canvasPane;

    @FXML
    private Label canvasXLabel;

    @FXML
    private Label canvasYLabel;

    @FXML
    private Label coordinateXLabel;

    @FXML
    private TextField coordinateXTextField;

    @FXML
    private Label coordinateYLabel;

    @FXML
    private TextField coordinateYTextField;
    public static double step;

    static {
        configureCanvas();
    }

    private static void configureCanvas() {
        canvas.maxHeight(Double.POSITIVE_INFINITY);
        canvas.maxWidth(Double.POSITIVE_INFINITY);
        canvas.setHeight(CANVAS_DEFAULT_HEIGHT);
        canvas.setWidth(CANVAS_DEFAULT_WIDTH);
        canvas.setScaleY(1);
        canvas.setScaleX(1);
        canvas.getGraphicsContext2D().setLineWidth(DEFAULT_STROKE_WIDTH);
    }

    @FXML
    public void initialize() {
        labWorkPolygons = new ArrayList<>();
        MainFormController.getCurrentLocale().addListener(change -> updateLocale());
        updateLocale();
        canvas.setOnMouseClicked(this::onCanvasMouseClicked);
        canvas.setOnMouseMoved(this::onCanvasMouseMoved);
        canvasPane.getChildren().add(canvas);
        frameTimer = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {
            collection = new ArrayList<>(MainFormController.getMainFormController().getModelsCollection());
            this.drawModels();
        })

        );
        step = 0;

        frameTimer.playFromStart();
    }

    @FXML
    protected void onCanvasMouseClicked(MouseEvent event) {
        Affine affine = canvas.getGraphicsContext2D().getTransform();
        double newX = event.getX() / affine.getMxx();
        double newY = event.getY() / affine.getMyy();
        LabWorkPolygon labWorkPolygon = null;
        for (LabWorkPolygon polygon : labWorkPolygons) {
            if (polygon.contains(newX, newY)) {
                labWorkPolygon = polygon;
            }
        }
        if (labWorkPolygon == null) {
            resetSelection();
            return;
        }
        selectPolygon(labWorkPolygon);
    }

    private void selectPolygon(LabWorkPolygon labWorkPolygon) {
        coordinateXTextField.setText(String.valueOf(labWorkPolygon.getlabWork().getCoordinates().getX()));
        coordinateYTextField.setText(String.valueOf(labWorkPolygon.getlabWork().getCoordinates().getY()));
        selectedLabwork = labWorkPolygon.getlabWork();
    }

    private void resetSelection() {
        coordinateXTextField.setText("");
        coordinateYTextField.setText("");
        selectedLabwork = null;
    }

    private void updateLocale() {
        coordinateXLabel.setText(VisualisationFirmElements.COORDINATE_X_LABEL.toString());
        coordinateYLabel.setText(VisualisationFirmElements.COORDINATE_Y_LABEL.toString());
        backToTableButton.setText(VisualisationFirmElements.BACK_TO_THE_TABLE_BUTTON.toString());
    }


    @FXML
    void onBackToTableButtonPressed(ActionEvent event) {
        Main.getPrimaryStage().setResizable(true);
        Main.getPrimaryStage().setScene(MainFormController.getMainFormController().getPrimaryScene());
        clearResources();
    }

    @FXML
    void onOkButtonPressed(ActionEvent event) {
        try {
            if (LabAskStatic.checkX(coordinateXTextField)
                    & LabAskStatic.checkY(coordinateYTextField)
                    && selectedLabwork != null && selectedLabwork.getOwnerID().equals(Console.client.getId())) {
                LabWork labWork = selectedLabwork;
                Coordinates coordinates = new Coordinates();
                coordinates.setX(Float.parseFloat(coordinateXTextField.getText()));
                coordinates.setY(Long.parseLong(coordinateYTextField.getText()));
                labWork.setCoordinates(coordinates);
                Console.update(labWork);
            }
            //добавить сообщение об ошибке
        } catch (Exception ignored) {
            //добавить сообщение об ошибке
        }
    }

    private void clearResources() {
        canvasPane.getChildren().clear();
        frameTimer.stop();
    }

    private void onCanvasMouseMoved(MouseEvent mouseEvent) {
        Affine affine = canvas.getGraphicsContext2D().getTransform();
        Integer newX = (int) Math.round(mouseEvent.getX() / affine.getMxx());
        Integer newY = (int) Math.round(mouseEvent.getY() / affine.getMyy());
        canvasXLabel.setText(newX.toString());
        canvasYLabel.setText(newY.toString());
    }

    private void drawModels() {
        canvas.getGraphicsContext2D().setTransform(defaultTransform);
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (collection.isEmpty()) return;
        computeCanvasScale();
        if (step < 1) step += 0.01;
        initPolygons(step);
        labWorkPolygons.sort((o1, o2) -> Float.compare(o1.getRadius(), o2.getRadius()));
        for (LabWorkPolygon polygon : labWorkPolygons) {
            drawModel(polygon);

        }

        frameTimer.playFromStart();
    }

    private void computeCanvasScale() {
        LabWork[] modelsArray = collection.toArray(LabWork[]::new);

        double canvasComputedWidth = Arrays.stream(modelsArray).mapToDouble(x ->
                x.getCoordinates().getX() +
                        DEFAULT_RADIUS_VALUE * SCALE_COMPUTING_RADIUS_MULTIPLIER
        ).max().getAsDouble();

        double canvasComputedHeight = Arrays.stream(modelsArray).mapToDouble(x ->
                x.getCoordinates().getY() +
                        DEFAULT_RADIUS_VALUE * SCALE_COMPUTING_RADIUS_MULTIPLIER).max().getAsDouble();

        if (Math.max(canvasComputedHeight, canvasComputedWidth) != 0) {
            canvas.getGraphicsContext2D().scale(canvas.getWidth() / Math.max(canvasComputedHeight, canvasComputedWidth), canvas.getWidth() / Math.max(canvasComputedHeight, canvasComputedWidth));
        }
    }

    private void drawModel(LabWorkPolygon polygon) {
        PolygonsFacade polygonsFacade = PolygonsFacade.getInstance();
        Affine affine = canvas.getGraphicsContext2D().getTransform();
        for (LabWorkPolygon polygonToCompare : labWorkPolygons) {
            if (polygonToCompare == polygon) continue;
            polygonsFacade.checkPolygons(polygon, polygonToCompare);
            polygonsFacade.checkBorder(polygon, new StraightLineEquation(0, 1, 0));
            polygonsFacade.checkBorder(polygon, new StraightLineEquation(0, 1, -canvas.getHeight() / affine.getMyy()));
            polygonsFacade.checkBorder(polygon, new StraightLineEquation(1, 0, 0));
            polygonsFacade.checkBorder(polygon, new StraightLineEquation(1, 0, -canvas.getWidth() / affine.getMxx()));

        }
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        configureGC(polygon.getlabWork().getOwnerID());
        Point2D[] points = polygon.getPointsFromPolygon();
        graphicsContext.fillPolygon(Arrays.stream(points).mapToDouble(Point2D::getX).toArray(), Arrays.stream(points).mapToDouble(Point2D::getY).toArray(), points.length);
        graphicsContext.strokePolygon(Arrays.stream(points).mapToDouble(Point2D::getX).toArray(), Arrays.stream(points).mapToDouble(Point2D::getY).toArray(), points.length);
    }

    private void configureGC(Long ownerId) {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        if (ownerId.equals(Console.client.getId())) {
            graphicsContext.setStroke(Color.GREEN);
            graphicsContext.setFill(Color.LIGHTGREEN);
        } else {
            graphicsContext.setStroke(Color.DARKGREY);
            graphicsContext.setFill(Color.LIGHTGREY);
        }
    }

    private void initPolygons(double delta) {
        CirclesComputer circlesComputer = new CirclesComputer();
        labWorkPolygons.clear();
        for (LabWork labwork : collection) {
            float radius = DEFAULT_RADIUS_VALUE;
            Point2D center = new Point2D(labwork.getCoordinates().getX() * delta, labwork.getCoordinates().getY() * delta);
            Point2D[] points = circlesComputer.calculateCirclePolygon(center, radius, CIRCLE_POLYGON_POINTS_COUNT);
            LabWorkPolygon labWorkPolygon = new LabWorkPolygon(labwork, center, radius);
            labWorkPolygon.updatePoints(points);
            labWorkPolygons.add(labWorkPolygon);
        }
    }
}

package com.danielstone.shapes;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * {@link Main} is the underlying class that displays
 * the program. It extends JavaFX {@link Application}
 * and so uses JavaFX classes throughout.
 * @author Daniel Stone
 * @version 1.0
 * @since 1.0
 */
public class Main extends Application{

    //Stage variable. Use this to control the stage.
    Stage window;
    // Graphics context, used to draw on
    GraphicsContext gc;

    /*
    Preferences
     */
    // Grid size
    int gridSize = 30;

    // Grid color & lineWidth
    Color gridColor = Color.LIGHTGRAY;
    double gridLineWidth = 0.5f;

    // Canvas size
    static final double canvasX = 510;
    static final double canvasY = 510;

    // Circle point size
    static final int circleWidth = 10;
    static final int circleHeight = 10;
    // Circle Color
    static final Color circleColor = Color.PALEVIOLETRED;

    /*
    End of Preferences
     */

    //int definitions (not as resource intensive as Enums)
    static final int FILL = 1;
    static final int FILL_WITH_STROKE = 2;
    static final int STROKE = 3;

    /*
    Strings and layout values
     */
    private final String windowTitle = "Shape Grid";
    private final String titleLabelString = "Draw Shapes!";
    private final int optionsGridPanePadding = 20;
    private final String preferredFont = "Segoe UI";
    private final int gridLabelFontSize = 10;
    private final int smallLabelFontSize = 15;
    private final int titleLabelFontSize = 30;
    /*
    End of strings and layout values
     */

    /*
    JavaFX elements
     */
    Label titleLabel, numberOfPointsLabel;
    Button resetButton;
    /*
    End of JavaFX elements
     */

    /*
    Event Handlers
     */
    EventHandler<MouseEvent> canvasOnMouseClickedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            canvasClick(x, y);
        }
    };
    /*
    End of Event Handlers
     */

    /*
    Arrays
     */
    ArrayList<Circle> pointsArray;
    /*
    End of Arrays
     */


    /**
     * Start of program. This method is run
     * as them program is run.
     * @param args optional arguments.
     */
    public static void main(String[] args) {
	launch(args);
    }

    /**
     * Invoked in {@link Main#main(String[])}.
     * Initializes the program. The root layout (GridPane)
     * {@link javafx.scene.layout.GridPane} is used.
     * @param primaryStage Stage.
     * @throws Exception May throw exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //set global variable window
        window = primaryStage;
        //set window title.
        window.setTitle(windowTitle);

        //create root GridPane.
        GridPane root = new GridPane();

        //initialize Canvas
        Canvas canvas = new Canvas(canvasX, canvasY);
        //set Canvas onMouseClickedHandler
        canvas.setOnMouseClicked(canvasOnMouseClickedHandler);
        //get graphics context from canvas
        gc = canvas.getGraphicsContext2D();
        //set graphics context font
        gc.setFont(new Font(preferredFont, gridLabelFontSize));

        //initialize options (bottom) GridPane
        GridPane options = new GridPane();
        //set padding
        options.setPadding(new Insets(optionsGridPanePadding));

        //initialize the titleLabel with the text titleLabelString
        titleLabel = new Label(titleLabelString);
        titleLabel.setFont(new Font(preferredFont, titleLabelFontSize));
        options.add(titleLabel, 0, 0, 2, 1);

        numberOfPointsLabel = new Label("0 points");
        numberOfPointsLabel.setFont(new Font(preferredFont, smallLabelFontSize));
        options.add(numberOfPointsLabel, 0, 1, 1, 1);

        resetButton = new Button("Reset");
        resetButton.setFont(new Font(preferredFont, smallLabelFontSize));
        resetButton.setOnAction(event -> resetButtonClicked(event));
        options.setMargin(resetButton, new Insets(0, 0, 0, 20));
        options.add(resetButton, 1, 1, 1, 1);

        root.add(canvas, 0, 0);
        root.add(options, 0, 1);

        window.setScene(new Scene(root, canvasX, 700));
        window.show();

        Utility.drawGrid(canvasX, canvasY, gridSize, gridColor, gridLineWidth, gc);
        pointsArray = new ArrayList<>();
    }

    private void canvasClick(int x, int y) {
        //Snap click point to grid
        x = Utility.snapToGridPixels(gridSize, x);
        y = Utility.snapToGridPixels(gridSize, y);
        //Use utility checkIfCircleExists helper method to get index if it does exist.
        int check = Utility.checkIfCircleExists(pointsArray, x, y);
        if (check == -1) {
            //circle doesn't exist. Add to arrayList
            pointsArray.add(new Circle(x, y, circleHeight, circleWidth, Color.ALICEBLUE, FILL));
        } else {
            //circle exists. Remove from arrayList
            pointsArray.remove(check);
        }
        reRender();
    }

    private void reRender() {
        gc.clearRect(0, 0, canvasX, canvasY);
        Utility.drawGrid(canvasX, canvasY, gridSize, gridColor, gridLineWidth, gc);

        gc.setFill(Color.AQUA);

        if (pointsArray.size() >= 3) {
            double[] xPoints = new double[pointsArray.size()];
            double[] yPoints = new double[pointsArray.size()];
            for (int i = 0; i < pointsArray.size(); i++) {
                xPoints[i] = (double) pointsArray.get(i).centerX;
                yPoints[i] = (double) pointsArray.get(i).centerY;
            }
            Utility.drawPolygonWithCoordPointsArray(gc, xPoints, yPoints, FILL, pointsArray.size(), null);
        }

        for (int i = 0; i < pointsArray.size(); i++) {
            gc.setFill(circleColor);
            Circle currentCircle = pointsArray.get(i);
            gc.fillOval(currentCircle.centerX - currentCircle.width /2,
                    currentCircle.centerY - currentCircle.height / 2,
                    currentCircle.width,
                    currentCircle.height);

            gc.setFill(Color.BLACK);
            gc.fillText(""+(i + 1), currentCircle.centerX + currentCircle.width / 2 + 4, currentCircle.centerY + currentCircle.height / 2 - 1);
        }

        numberOfPointsLabel.setText(pointsArray.size() + " points");
    }

    private void resetButtonClicked(ActionEvent event) {
        pointsArray.clear();
        reRender();
    }
}

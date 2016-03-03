package com.danielstone.shapes;

import com.sun.istack.internal.Nullable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Utility class with methods to help.
 * @author Daniel Stone
 */
public class Utility {

    /**
     * Helper method to draw grid onto canvas. Uses variables from
     * the {@link Main} class such as gridColor and gridLineWidth.
     * @param canvasX size of canvas x (double)
     * @param canvasY size of canvas y (double)
     * @param gridColor {@link Color} to draw lines as
     * @param gridLineWidth width of grid lines (double)
     * @param gc {@link GraphicsContext} to draw onto
     */
    static void drawGrid(double canvasX,
                         double canvasY,
                         int gridSize,
                         Color gridColor,
                         double gridLineWidth,
                         GraphicsContext gc) {
        // Set line color and line width
        gc.setStroke(gridColor);
        gc.setLineWidth(gridLineWidth);

        // get canvas size from Main class
        double height = canvasX;
        double width = canvasY;

        // For loops: for every interval of gridSize (taken from current instance)
        // draw a line at the current x or y value
        for (int i = 0; i < height; i = i + gridSize) {
            gc.strokeLine(i, 0, i, width);
        }
        for (int i = 0; i < width; i = i + gridSize) {
            gc.strokeLine(0, i, height, i);
        }
    }

    /**
     * Takes a pixel point and rounds it to the nearest factor of
     * grid size. Essentially snapping it to grid.
     * @param gridSize int grid size (interval)
     * @param position int Pixel point.
     * @return int snapped to grid.
     */
    static int snapToGridPixels(int gridSize, int position) {
        return round(position, gridSize);
    }

    /**
     * Rounds a number to the factor specified
     * @param number double to round
     * @param factor int factor
     * @return int rounded value
     */
    static int round(double number, int factor){
        return (int) Math.round(number/factor) * factor;
    }

    /**
     * Helper method to check if the circle with
     * center coordinates centerX and centerY have
     * exists in the supplied {@link ArrayList}
     * @param circleArrayList ArrayList to check
     * @param centerX int center x coordinate of circle
     * @param centerY int center y coordinate of circle
     * @return if exists: int index of circle in array list. Else: return -1
     */
    static int checkIfCircleExists(ArrayList<Circle> circleArrayList, int centerX, int centerY) {
        for (int i = 0; i < circleArrayList.size(); i++) {
            if (circleArrayList.get(i).centerX == centerX &&
                    circleArrayList.get(i).centerY == centerY) {
                return i;
            }
        }
        return -1;
    }

    static void drawPolygonWithCoordPointsArray(GraphicsContext gc,
                                                double[] xCoordPoints,
                                                double[] yCoordPoints,
                                                int type, int numberOfPoints,
                                                @Nullable Integer strokeSize) {
        if (strokeSize != null) {
            gc.setLineWidth(strokeSize);
        }
        if (type == Main.FILL || type == Main.FILL_WITH_STROKE)
            gc.fillPolygon(xCoordPoints, yCoordPoints, numberOfPoints);
        if (type == Main.STROKE || type == Main.FILL_WITH_STROKE) {
            gc.strokePolygon(xCoordPoints, yCoordPoints, numberOfPoints);
        }
    }


}

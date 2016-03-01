package com.danielstone.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Utility class with methods to help.
 * @author Daniel Stone
 */
public class Utility {

    /**
     * Helper method to draw grid onto canvas. Uses variables from
     * the {@link Main} class such as gridColor and gridLineWidth.
     * @param context Current instance of {@link Main}
     * @param gc {@link GraphicsContext} to draw onto
     */
    static void drawGrid(Main context, GraphicsContext gc) {
        // Set line color and line width
        gc.setStroke(context.gridColor);
        gc.setLineWidth(context.gridLineWidth);

        // get canvas size from Main class
        double height = Main.canvasX;
        double width = Main.canvasY;

        // For loops: for every interval of gridSize (taken from current instance)
        // draw a line at the current x or y value
        for (int i = 0; i < height; i = i + context.gridSize) {
            gc.strokeLine(i, 0, i, width);
        }
        for (int i = 0; i < width; i = i + context.gridSize) {
            gc.strokeLine(0, i, height, i);
        }
    }

    /**
     * Takes a pixel point and rounds it to the nearest factor of
     * grid size. Essentially snapping it to grid.
     * @param context Instance of {@link Main} used to get gridSize
     * @param position int Pixel point.
     * @return int snapped to grid.
     */
    static int snapToGridPixels(Main context, int position) {
        return round(position, context.gridSize);
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

    static int checkIfCircleExists(Main context, int centerX, int centerY) {
        for (int i = 0; i < context.pointsArray.size(); i++) {
            if (context.pointsArray.get(i).centerX == centerX &&
                    context.pointsArray.get(i).centerY == centerY) {
                return i;
            }
        }
        return -1;
    }



}

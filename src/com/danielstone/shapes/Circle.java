package com.danielstone.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * {@link Circle}Circle object consisting of relevant
 * data needed to draw a circle.
 * @author Daniel Stone
 */
public class Circle {

    int centerX;
    int centerY;

    int height;
    int width;

    Color color;
    int fillStrokeType;
    double strokeSize;


    public Circle(int centerX, int centerY,
                  int height, int width,
                  Color color,
                  int fillStrokeType) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.height = height;
        this.width = width;
        this.color = color;
        this.fillStrokeType = fillStrokeType;
        this.strokeSize = 0;
    }

    public Circle(int centerX, int centerY,
                  int height, int width,
                  Color color,
                  int fillStrokeType, double strokeSize) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.height = height;
        this.width = width;
        this.color = color;
        this.fillStrokeType = fillStrokeType;
        this.strokeSize = strokeSize;
    }
}

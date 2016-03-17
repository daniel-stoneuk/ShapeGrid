package com.danielstone.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import java.io.*;

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

    public Circle(String jsonInfo) throws FileNotFoundException {
        JsonReader reader = Json.createReader(new FileReader("jsondata.txt"));
        JsonStructure jsonst = reader.read();

    }


    /**
     * Constructor for circle without stroke.
     * @param centerX
     * @param centerY
     * @param height
     * @param width
     * @param color
     * @param fillStrokeType
     */
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

    /**
     * Constructor for circle with stroke
     * @param centerX
     * @param centerY
     * @param height
     * @param width
     * @param color
     * @param fillStrokeType
     * @param strokeSize
     */
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

    public String generateSaveLine(int order) throws IOException{


        ByteArrayOutputStream writer = new ByteArrayOutputStream();

        JsonGenerator generator = Json.createGenerator(writer);

        generator.writeStartObject()
                .write("order", order)
                .writeStartObject("position")
                    .write("centerX", centerX)
                    .write("centerY", centerY)
                .writeEnd()
                .writeStartObject("size")
                    .write("height", height)
                    .write("width", width)
                .writeEnd()
                .write("color", color.toString())
                .write("fillStrokeType", fillStrokeType)
                .write("strokeSize", strokeSize)
                .writeEnd();
        generator.close();

        writer.close();

        String retString = writer.toString();

        System.out.print(retString);
        return retString;
    }
}

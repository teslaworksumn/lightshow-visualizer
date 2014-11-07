package net.teslaworks.visualizer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import org.dom4j.Element;

public abstract class Shape {

    // DMX
    public final String name;
    public final int channel;

    // Position
    public final int x;
    public final int y;
    public final int xOffset;
    public final int yOffset;

    // Color
    public final int red;
    public final int blue;
    public final int green;

    // Make a new shape from a <shape> tag in configuration
    public static Shape makeShape(Element e, int xOffset, int yOffset) {
        // Switch based on "type" attribute in tag.
        String type = e.attributeValue("type");
        switch (type) {
        case "rect":
            return new Rectangle(e, xOffset, yOffset);
        case "oval":
            return new Oval(e, xOffset, yOffset);
        case "line":
            return new Line(e, xOffset, yOffset);
        case "arc":
            return new Arc(e, xOffset, yOffset);
        case "fan":
            return new Fan(e, xOffset, yOffset);
        case "megatree":
            return new Megatree(e, xOffset, yOffset);
        }
        throw new IllegalArgumentException("Unknown shape type: " + e.asXML());
    }

    // Set values common to all shapes
    protected Shape(Element e, int xOffset, int yOffset) {
        name = e.attributeValue("name");
        channel = Integer.parseInt(e.attributeValue("channel"));

        x = Integer.parseInt(e.attributeValue("x"));
        y = Integer.parseInt(e.attributeValue("y"));
        this.xOffset = xOffset;
        this.yOffset = yOffset;

        red = Integer.parseInt(e.attributeValue("red", "255"));
        blue = Integer.parseInt(e.attributeValue("blue", "255"));
        green = Integer.parseInt(e.attributeValue("green", "255"));
    }

    public String toString() {
        return name + " @channel " + channel;
    }

    // Draw this shape to the graphics2d instance
    public void paint(Graphics2D g2d, int[] channelValues) {
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(new Color(red, green, blue, channelValues[channel]));
    }
}

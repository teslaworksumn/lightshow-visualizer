package net.teslaworks.visualizer.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import org.dom4j.Element;

public abstract class Shape {

    public static Color TRANSPARENT = new Color(0, 0, 0, 0);

    // DMX
    public final String name;
    public final int channel;

    // Position
    public final int x;
    public final int y;
    public final double rotation;

    // Color
    public final int red;
    public final int blue;
    public final int green;

    // Make a new shape from a <shape> tag in configuration
    public static Shape makeShape(Element e) {
        // Switch based on "type" attribute in tag.
        String type = e.attributeValue("type");
        switch (type) {
        case "text":
            return new Text(e);
        case "rect":
            return new Rectangle(e);
        case "oval":
            return new Oval(e);
        case "line":
            return new Line(e);
        case "arc":
            return new Arc(e);
        case "fan": // Old name for Sunburst, here just in case.
        case "sunburst":
            return new Sunburst(e);
        case "megatree":
            return new Megatree(e);
        case "roundmegatree":
            return new RoundMegatree(e);
        case "tree":
            return new Tree(e);
        case "bush":
            return new Bush(e);
        case "quadbush":
            return new QuadBush(e);
        case "arcbush":
            return new ArcBush(e);
        case "snowflake":
            return new Snowflake(e);
        case "netlight":
            return new NetLight(e);
        case "snowguitarist":
            return new Snowguitarist(e);
        case "snowdrummer":
            return new Snowdrummer(e);
        case "sun":
            return new Sun(e);
        }
        throw new IllegalArgumentException("Unknown shape type: " + e.asXML());
    }

    // Set values common to all shapes
    protected Shape(Element e) {
        name = e.attributeValue("name", "unnamed");
        channel = Integer.parseInt(e.attributeValue("channel"));

        x = Integer.parseInt(e.attributeValue("x", "0"));
        y = Integer.parseInt(e.attributeValue("y", "0"));
        rotation = Integer.parseInt(e.attributeValue("rotation", "0"));

        red = Integer.parseInt(e.attributeValue("red", "255"));
        blue = Integer.parseInt(e.attributeValue("blue", "255"));
        green = Integer.parseInt(e.attributeValue("green", "255"));
    }

    public String toString() {
        return name + " @channel " + channel;
    }

    // Draw this shape to the graphics2d instance
    public void paint(Graphics2D g2d, int[] channelValues) {
        g2d.rotate(Math.toRadians(rotation), x, y);
        g2d.setStroke(new BasicStroke(2));
        g2d.setPaint(new Color(red, green, blue, channelValues[channel]));

        paintWork(g2d, channelValues);

        g2d.rotate(0 - Math.toRadians(rotation), x, y);
    }

    abstract protected void paintWork(Graphics2D g2d, int[] channelValues);
}

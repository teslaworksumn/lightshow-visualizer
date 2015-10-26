package net.teslaworks.visualizer.shapes;

import java.awt.Graphics2D;

import org.dom4j.Element;

public class Rectangle extends Shape {

    // Shape size
    public final int width;
    public final int height;
    public final boolean fill;

    // Set values unique to rectangles
    protected Rectangle(Element e, int xOffset, int yOffset) {
        super(e, xOffset, yOffset);
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        fill = Boolean.parseBoolean(e.attributeValue("fill"));
    }

    // Draw this rectangle
    public void paint(Graphics2D g2d, int[] channelValues) {
        super.paint(g2d, channelValues);
        if (fill) {
            g2d.fill(new java.awt.Rectangle(x + xOffset, y + yOffset, width, height));
        }
        else {
            g2d.draw(new java.awt.Rectangle(x + xOffset, y + yOffset, width, height));
        }
    }
}

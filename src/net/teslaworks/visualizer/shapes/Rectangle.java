package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import net.teslaworks.visualizer.LayoutXML;

import org.dom4j.Element;

public class Rectangle extends Shape {

    // Shape size
    public final int width;
    public final int height;
    public final boolean fill;

    // Set values unique to rectangles
    protected Rectangle(Element e) {
        super(e);
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        fill = Boolean.parseBoolean(e.attributeValue("fill"));
    }

    // Draw this rectangle
    public void paint(Graphics2D g2d, int channelValue) {
        super.paint(g2d, channelValue);
        if (fill) {
            g2d.fillRect(x, y, width, height);
        }
        else {
            g2d.drawRect(x, y, width, height);
        }
    }
}

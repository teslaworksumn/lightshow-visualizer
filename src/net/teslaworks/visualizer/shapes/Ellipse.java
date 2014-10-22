package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import net.teslaworks.visualizer.LayoutXML;

import org.dom4j.Element;

public class Ellipse extends Shape {

    // Shape size
    public final int width;
    public final int height;

    // Set values unique to rectangles
    protected Ellipse(Element e) {
        super(e);
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
    }

    // Draw this rectangle
    public void paint(Graphics2D g2d, int channelValue) {
        super.paint(g2d, channelValue);
        if (fill) {
            g2d.fillOval(x, y, width, height);
        }
        else {
            g2d.drawOval(x, y, width, height);
        }
    }
}

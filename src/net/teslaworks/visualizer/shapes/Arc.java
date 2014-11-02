package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import net.teslaworks.visualizer.LayoutXML;

import org.dom4j.Element;

public class Arc extends Shape {

    // Shape size
    public final int width;
    public final int height;
    public final int start;
    public final int size;
    public final boolean fill;

    // Set values unique to rectangles
    protected Arc(Element e, int xOffset, int yOffset) {
        super(e, xOffset, yOffset);
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        start = Integer.parseInt(e.attributeValue("start"));
        size = Integer.parseInt(e.attributeValue("size"));
        fill = Boolean.parseBoolean(e.attributeValue("fill"));
    }

    // Draw this arc
    public void paint(Graphics2D g2d, int[] channelValues) {
        super.paint(g2d, channelValues);
        if (fill) {
            g2d.fillArc(x + xOffset, y + yOffset, width, height, start, size);
        }
        else {
            g2d.drawArc(x + xOffset, y + yOffset, width, height, start, size);
        }
    }
}

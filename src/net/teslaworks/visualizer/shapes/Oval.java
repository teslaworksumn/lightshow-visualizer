package net.teslaworks.visualizer.shapes;

import java.awt.Graphics2D;

import org.dom4j.Element;

public class Oval extends Shape {

    // Shape size
    public final int width;
    public final int height;
    public final boolean fill;

    // Set values unique to rectangles
    protected Oval(Element e, int xOffset, int yOffset) {
        super(e, xOffset, yOffset);
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        fill = Boolean.parseBoolean(e.attributeValue("fill"));
    }

    // Draw this oval
    public void paint(Graphics2D g2d, int[] channelValues) {
        super.paint(g2d, channelValues);
        if (fill) {
            g2d.fillOval(x + xOffset, y + yOffset, width, height);
        }
        else {
            g2d.drawOval(x + xOffset, y + yOffset, width, height);
        }
    }
}

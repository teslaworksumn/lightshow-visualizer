package net.teslaworks.visualizer.shapes;

import java.awt.Graphics2D;

import org.dom4j.Element;

public class Line extends Shape {

    // Shape size
    public final int x2;
    public final int y2;
    public final boolean relative;

    // Set values unique to rectangles
    protected Line(Element e, int xOffset, int yOffset) {
        super(e, xOffset, yOffset);
        x2 = Integer.parseInt(e.attributeValue("x2"));
        y2 = Integer.parseInt(e.attributeValue("y2"));
        relative = Boolean.parseBoolean(e.attributeValue("relative"));
    }

    // Draw this line
    public void paint(Graphics2D g2d, int[] channelValues) {
        super.paint(g2d, channelValues);
        if (relative) {
            g2d.drawLine(x + xOffset, y + yOffset, x + x2 + xOffset, y + y2 + yOffset);
        }
        else {
            g2d.drawLine(x + xOffset, y + yOffset, x2 + xOffset, y2 + yOffset);
        }
    }
}

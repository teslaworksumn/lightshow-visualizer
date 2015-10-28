package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;

public class Snowdrummer extends Shape {

    // Shape size
    public final int height;

    // Set values unique to rectangles
    protected Snowdrummer(Element e) {
        super(e);
        height = Integer.parseInt(e.attributeValue("height"));
    }

    // Draw this oval
    public void paintWork(Graphics2D g2d, int[] channelValues) {
        // head, torso, legs
        g2d.drawOval(x + ((3 * height) / 20), y, height / 5, height / 5);
        g2d.drawOval(x + (height / 10), y + (height / 5), (3 * height) / 10, (3 * height / 10));
        g2d.drawOval(x, y + (height / 2), height / 2, height / 2);
    }
}

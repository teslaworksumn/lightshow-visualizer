package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;
import java.awt.Rectangle;

public class Snowguitarist extends Shape {

    // Shape size
    public final int BASE_HEIGHT = 80;
    private final int height;

    // Set values unique to rectangles
    protected Snowguitarist(Element e) {
        super(e);
        height = Integer.parseInt(e.attributeValue("height"));
    }

    // Draw this oval
    public void paintWork(Graphics2D g2d, int[] channelValues) {
        g2d.translate(x, y);
        g2d.scale(1, (1.0 * height) / BASE_HEIGHT);

        paintBodyWork(g2d);
        paintGuitarWork(g2d, channelValues);
        paintArmsWork(g2d, channelValues);

        g2d.scale(1, (1.0 * BASE_HEIGHT) / height);
        g2d.translate(0 - x, 0 - y);
    }

    private void paintBodyWork(Graphics2D g2d) {
        // Needs to be called first, so paint is already set.
        // head, torso, legs
        g2d.drawOval((3 * BASE_HEIGHT) / 20, 0, BASE_HEIGHT / 5, BASE_HEIGHT / 5);
        g2d.drawOval(BASE_HEIGHT / 10, BASE_HEIGHT / 5, (3 * BASE_HEIGHT) / 10, (3 * BASE_HEIGHT / 10));
        g2d.drawOval(0, BASE_HEIGHT / 2, BASE_HEIGHT / 2, BASE_HEIGHT / 2);
    }

    private void paintArmsWork(Graphics2D g2d, int[] channelValues) {
        // right drumming arm
        g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 0]));

        g2d.drawLine(13, 25, 3, 38);
        g2d.drawLine(3, 38, 20, 35);
        g2d.drawLine(3, 38, 20, 55);
    }

    private void paintGuitarWork(Graphics2D g2d, int[] channelValues) {
        g2d.setPaint(new Color(255, 0, 0, channelValues[channel]));
        g2d.rotate(Math.toRadians(-20));

        g2d.draw(new Rectangle(-10, 43, 30, 18));
        g2d.drawLine(-5, 48, 55, 48);
        g2d.drawLine(-5, 52, 55, 52);
        g2d.drawLine(-5, 56, 55, 56);

        g2d.rotate(Math.toRadians(20));
    }
}

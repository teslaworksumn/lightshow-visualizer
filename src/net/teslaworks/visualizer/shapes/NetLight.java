package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;

public class NetLight extends Shape {

    // Shape size
    private final int width, height;
    private final int xCount, yCount, gap;
    private final boolean reverseCheckered;

    // Set values unique to rectangles
    protected NetLight(Element e) {
        super(e);
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        xCount = Integer.parseInt(e.attributeValue("xCount"));
        yCount = Integer.parseInt(e.attributeValue("yCount"));
        gap = Integer.parseInt(e.attributeValue("gap"));
        reverseCheckered = Boolean.parseBoolean(e.attributeValue("reverse"));
    }

    // Draw this rectangle
    public void paintWork(Graphics2D g2d, int[] channelValues) {
        int c = channel;
        for (int i = 0; i < xCount; i++) {
            for (int j = yCount - 1; j >= 0; j--) {
                g2d.setPaint(new Color(red, green, blue, channelValues[c]));
                g2d.fill(new java.awt.Rectangle(
                        x + i * (width + gap), y + j * (height + gap), width, height));
                c++;
            }
        }
    }
}

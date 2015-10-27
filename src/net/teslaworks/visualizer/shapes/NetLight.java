package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;

public class NetLight extends Shape {

    // Shape size
    private final int width, height;
    private final int xCount, yCount, gap;

    // Set values unique to rectangles
    protected NetLight(Element e) {
        super(e);
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        xCount = Integer.parseInt(e.attributeValue("xCount"));
        yCount = Integer.parseInt(e.attributeValue("yCount"));
        gap = Integer.parseInt(e.attributeValue("gap"));
    }

    // Draw this rectangle
    public void paintWork(Graphics2D g2d, int[] channelValues) {
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                g2d.fill(new java.awt.Rectangle(
                        x + i * (width + gap), y + j * (height + gap), width, height));
            }
        }
    }
}

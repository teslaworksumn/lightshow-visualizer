package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tree extends Shape {

    // Shape size
    public final int width;
    public final int height;

    // Secondary colors
    public final int red2, green2, blue2;
    public final boolean dualColor;
    private final int dualOffset;

    // Set values unique to rectangles
    protected Tree(Element e) {
        super(e);
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        dualColor = Boolean.parseBoolean(e.attributeValue("dual"));
        dualOffset = Integer.parseInt(e.attributeValue("offset", "1"));

        red2 = Integer.parseInt(e.attributeValue("red2", "0"));
        green2 = Integer.parseInt(e.attributeValue("green2", "0"));
        blue2 = Integer.parseInt(e.attributeValue("blue2", "0"));
    }

    private TexturePaint makeTexturePaint(int[] channelValues) {
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setPaint(new Color(red, green, blue, channelValues[channel]));
        g2d.fill(new Rectangle(0, 0, 16, 4));

        g2d.setPaint(dualColor
                ? new Color(red2, green2, blue2, channelValues[channel + dualOffset])
                : Shape.TRANSPARENT);
        g2d.fill(new Rectangle(0, 8, 16, 4));

        g2d.setPaint(Shape.TRANSPARENT);
        g2d.fill(new Rectangle(0, 4, 16, 4));
        g2d.fill(new Rectangle(0, 12, 16, 4));

        TexturePaint paint = new TexturePaint(image, new Rectangle(0, 0, 16, 16));
        g2d.dispose();

        return paint;
    }

    // Draw this rectangle
    public void paintWork(Graphics2D g2d, int[] channelValues) {
        g2d.setPaint(makeTexturePaint(channelValues));
        g2d.translate(x, y);

        // (x, y + h) (x + (w / 2), y) (x + w, y + h)
        int[] xPoints = { 0, width / 2, width };
        int[] yPoints = { (height * 7) / 8, 0, (height * 7) / 8 };
        g2d.fillPolygon(xPoints, yPoints, 3);

        g2d.fillRect(width / 3, (height * 7) / 8, width / 3, height /  8);

        g2d.translate(0 - x, 0 - y);
    }
}

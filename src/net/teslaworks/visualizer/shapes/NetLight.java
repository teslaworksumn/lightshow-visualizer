package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class NetLight extends Shape {

    // Shape size
    private final int width, height, gap;
    private final int xCount, yCount, count;
    private final boolean reverseCheckered;

    // Set values unique to rectangles
    protected NetLight(Element e) {
        super(e);

        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        gap = Integer.parseInt(e.attributeValue("gap"));

        xCount = Integer.parseInt(e.attributeValue("xCount"));
        yCount = Integer.parseInt(e.attributeValue("yCount"));
        count = xCount * yCount;
        reverseCheckered = Boolean.parseBoolean(e.attributeValue("reverse"));
    }

    private TexturePaint[] makeTexturePaints(int[] channelValues) {
        TexturePaint[] paints = new TexturePaint[count];

        for (int i = 0; i < count; i++) {
            BufferedImage image = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            if (reverseCheckered) {
                g2d.setPaint(new Color(red, green, blue, channelValues[channel + i]));
                g2d.fill(new Rectangle(0, 4, 4, 4));
                g2d.fill(new Rectangle(4, 0, 4, 4));

                g2d.setPaint(Shape.TRANSPARENT);
                g2d.fill(new Rectangle(0, 0, 4, 4));
                g2d.fill(new Rectangle(4, 4, 4, 4));
            }
            else {
                g2d.setPaint(new Color(red, green, blue, channelValues[channel + i]));
                g2d.fill(new Rectangle(0, 0, 4, 4));
                g2d.fill(new Rectangle(4, 4, 4, 4));

                g2d.setPaint(Shape.TRANSPARENT);
                g2d.fill(new Rectangle(0, 4, 4, 4));
                g2d.fill(new Rectangle(4, 0, 4, 4));
            }

            paints[i] = new TexturePaint(image, new Rectangle(0, 0, 8, 8));
            g2d.dispose();
        }

        return paints;
    }

    // Draw this rectangle
    public void paintWork(Graphics2D g2d, int[] channelValues) {
        TexturePaint[] paints = makeTexturePaints(channelValues);
        int c = 0;

        for (int i = 0; i < xCount; i++) {
            for (int j = yCount - 1; j >= 0; j--) {
                g2d.setPaint(paints[c]);
                g2d.fill(new java.awt.Rectangle(
                        x + i * (width + gap), y + j * (height + gap), width, height));
                c++;
            }
        }
    }
}

package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoundMegatree extends Shape {

    private static class Spoke {
        final int channel;
        final int red, green, blue;
        final int endX, endY;

        Spoke(int channel, int red, int green, int blue, int endX, int endY) {
            this.channel = channel;

            this.red = red;
            this.green = green;
            this.blue = blue;

            this.endX = endX;
            this.endY = endY;
        }
    }

    public final int width;
    public final int originX;
    public final int originY;
    public final double startAngle;
    public final List<Spoke> spokes;

    @SuppressWarnings("unchecked")
    protected RoundMegatree(Element e) {
        super(e);

        width = Integer.parseInt(e.attributeValue("width"));
        startAngle = Math.toRadians(Integer.parseInt(e.attributeValue("start")));
        int spokeCount = Integer.parseInt(e.attributeValue("spokes"));

        int radius = width / 2;
        originX = x + radius;
        originY = y + radius;

        spokes = new ArrayList<>();

        List<Element> spokeElements = (List<Element>) e.elements("spoke");
        double angleSize = (2 * Math.PI) / spokeCount;

        for (int i = 0; i < spokeCount; i++) {
            double angle = startAngle + (angleSize * i);

            Element spoke = spokeElements.get(i % spokeElements.size());
            int r = Integer.parseInt(spoke.attributeValue("red"));
            int g = Integer.parseInt(spoke.attributeValue("green"));
            int b = Integer.parseInt(spoke.attributeValue("blue"));

            int endX = (int) (originX + (Math.cos(angle) * radius));
            int endY = (int) (originY - (Math.sin(angle) * radius));
            
            spokes.add(new Spoke(channel + i, r, g, b, endX, endY));
        }
    }

    // Draw this fan
    public void paintWork(Graphics2D g2d, int[] channelValues) {
        for (Spoke s : spokes) {
            g2d.setColor(new Color(s.red, s.green, s.blue, channelValues[s.channel]));
            g2d.drawLine(originX, originY, s.endX, s.endY);
        }
    }

}

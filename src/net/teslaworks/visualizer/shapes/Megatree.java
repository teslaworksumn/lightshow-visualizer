package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class Megatree extends Shape {
    
    private static class Spoke {
        final int channel;
        final int red, green, blue;
        final int endX;
        
        Spoke(int channel, int red, int green, int blue, int endX) {
            this.channel = channel;
            
            this.red = red;
            this.green = green;
            this.blue = blue;
            
            this.endX = endX;
        }
    }

    public final int width;
    public final int height;
    public final int originX;
    public final List<Spoke> spokes;

    protected Megatree(Element e) {
        super(e);
        
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        
        originX = x + (width/2);
        
        spokes = new ArrayList<>();
        
        List<Element> spokeElements = (List<Element>) e.elements("spoke");
        int offset = width / (spokeElements.size() - 1);
        
        for (int i = 0; i < spokeElements.size(); i++) {
            Element spokeElement = spokeElements.get(i);
            
            int r = Integer.parseInt(spokeElement.attributeValue("red"));
            int g = Integer.parseInt(spokeElement.attributeValue("green"));
            int b = Integer.parseInt(spokeElement.attributeValue("blue"));

            int endX = x + (i * offset);
            spokes.add(new Spoke(channel + i, r, g, b, endX));
        }
    }

    // Draw this fan
    public void paint(Graphics2D g2d, int[] channelValues) {
        super.paint(g2d, channelValues);
        for (Spoke s : spokes) {
            g2d.setColor(new Color(s.red, s.green, s.blue, channelValues[s.channel]));
            g2d.drawLine(originX, y, s.endX, y + height);
        }
    }

}

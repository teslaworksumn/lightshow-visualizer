package net.teslaworks.visualizer.shapes;

import java.awt.Font;
import java.awt.Graphics2D;

import org.dom4j.Element;

public class Text extends Shape {
    
    public final String value;
    public final Font font;

    // Set values unique to rectangles
    protected Text(Element e) {
        super(e);
        value = e.getTextTrim();
        String font = e.attributeValue("font", "Purisa");
        int fontSize = Integer.parseInt(e.attributeValue("fontSize", "12"));
        this.font = new Font(font, Font.PLAIN, fontSize);
    }

    // Draw this rectangle
    public void paintWork(Graphics2D g2d, int[] channelValues) {
        g2d.setFont(font);
        g2d.drawString(value, x, y);
    }
}

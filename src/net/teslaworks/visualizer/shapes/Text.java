package net.teslaworks.visualizer.shapes;

import java.awt.Font;
import java.awt.Graphics2D;

import org.dom4j.Element;

public class Text extends Shape {
    
    public final String value;
    public final String font;
    public final int fontSize;

    // Set values unique to rectangles
    protected Text(Element e, int xOffset, int yOffset) {
        super(e, xOffset, yOffset);
        value = e.getTextTrim();
        font = e.attributeValue("font", "Purisa");
        fontSize = Integer.parseInt(e.attributeValue("fontSize", "12"));
    }

    // Draw this rectangle
    public void paint(Graphics2D g2d, int[] channelValues) {
        super.paint(g2d, channelValues);
        g2d.setFont(new Font(font, Font.PLAIN, fontSize));
        g2d.drawString(value, x + xOffset, y + yOffset);
    }
}

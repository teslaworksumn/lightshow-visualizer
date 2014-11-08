package net.teslaworks.visualizer;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import net.teslaworks.visualizer.shapes.Shape;

import org.dom4j.Element;

public class Group {
    
    private final int xOffset, yOffset;
    private final List<Shape> shapes;
    private final List<Group> subgroups;
    private final int x, y;

    @SuppressWarnings("unchecked")
    public Group(Element e, int _xOffset, int _yOffset) {
        this.xOffset = _xOffset;
        this.yOffset = _yOffset;
        this.shapes = new ArrayList<>();
        this.subgroups = new ArrayList<>();

        x = Integer.parseInt(e.attributeValue("x"));
        y = Integer.parseInt(e.attributeValue("y"));

        for (Element shape : (List<Element>) e.elements("shape")) {
            shapes.add(Shape.makeShape(shape, x + xOffset, y + yOffset));
        }
        
        for (Element subgroup : (List<Element>) e.elements("group")) {
            subgroups.add(new Group(subgroup, x + xOffset, y + yOffset));
        }
    }

    public void paint(Graphics2D g2d, int[] channelValues) {
        for (Shape s : shapes) {
            s.paint(g2d, channelValues);
        }
        for (Group g : subgroups) {
            g.paint(g2d, channelValues);
        }
    }
}

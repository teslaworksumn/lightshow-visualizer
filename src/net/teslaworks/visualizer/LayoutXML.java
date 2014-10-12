package net.teslaworks.visualizer;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import net.teslaworks.visualizer.shapes.Rectangle;
import net.teslaworks.visualizer.shapes.Shape;

public class LayoutXML {

    // Constants representing data from layout file
    final public int[] channelValues;
    final public int width, height;
    final public Color background;
    final public List<Shape> shapes;

    // Given layout filename, parses all relevant data and stores it
    public LayoutXML(String layoutFilename)
            throws DocumentException, IOException {
        URL layoutURL = getClass().getResource(layoutFilename);
        Document layout = new SAXReader().read(layoutURL);;

        // Channel count
        Element channels =
                (Element) layout.selectSingleNode("/layout/channels");
        channelValues =
                new int[Integer.parseInt(channels.attributeValue("count"))];

        // Window size and color
        Element sizeElement = (Element) layout.selectSingleNode("/layout/size");
        width = Integer.parseInt(sizeElement.attributeValue("width"));
        height = Integer.parseInt(sizeElement.attributeValue("height"));
        Element bgElement =
                (Element) layout.selectSingleNode("/layout/background");
        int bgRed = Integer.parseInt(bgElement.attributeValue("red"));
        int bgGreen = Integer.parseInt(bgElement.attributeValue("green"));
        int bgBlue = Integer.parseInt(bgElement.attributeValue("blue"));
        background = new Color(bgRed, bgGreen, bgBlue);

        // All the shapes to render
        List<Node> shapeNodes = layout.selectNodes("/layout/elements/shape");
        shapes = new ArrayList<>();
        for (Node n : shapeNodes) {
            shapes.add(Shape.makeShape((Element) n));
        }
    }
}

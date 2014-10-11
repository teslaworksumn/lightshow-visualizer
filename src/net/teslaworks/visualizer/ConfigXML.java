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

public class ConfigXML {

    // Constants representing data from config file
    final public String targetFilename;
    final public int channelCount;
    final public int width, height;
    final public Color background;
    final public List<Shape> shapes;

    // Given config filename, parses all relevant data and stores it
    public ConfigXML(String configFilename)
            throws DocumentException, IOException {
        URL configURL = getClass().getResource(configFilename);
        Document config = new SAXReader().read(configURL);;

        // Target filename to read from
        targetFilename = config
                .selectSingleNode("/vixenlights_viz_cfg/vltarget").getText();

        // Channel count
        Element channels = (Element) config
                .selectSingleNode("/vixenlights_viz_cfg/channels");
        channelCount = Integer.parseInt(channels.attributeValue("count"));

        // Window size and color
        Element sizeElement = (Element) config
                .selectSingleNode("/vixenlights_viz_cfg/size");
        width = Integer.parseInt(sizeElement.attributeValue("width"));
        height = Integer.parseInt(sizeElement.attributeValue("height"));
        Element bgElement = (Element) config
        		.selectSingleNode("/vixenlights_viz_cfg/background");
        int bgRed = Integer.parseInt(bgElement.attributeValue("red"));
        int bgGreen = Integer.parseInt(bgElement.attributeValue("green"));
        int bgBlue = Integer.parseInt(bgElement.attributeValue("blue"));
        background = new Color(bgRed, bgGreen, bgBlue);

        // All the shapes to render
        List<Node> shapeNodes = config
                .selectNodes("/vixenlights_viz_cfg/elements/shape");
        shapes = new ArrayList<>();
        for (Node n : shapeNodes) {
            shapes.add(Shape.makeShape((Element) n));
        }
    }
}

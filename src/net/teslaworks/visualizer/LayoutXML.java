package net.teslaworks.visualizer;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

public class LayoutXML {

    // Constants representing data from layout file
    final public int[] channelValues;
    final public int width, height;
    final public Color background;
    final public Group topGroup;

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

        // Change top level 'elements' to a group node.
        Element elements = (Element) layout.selectSingleNode("/layout/elements");
        elements.setQName(new QName("group"));
        elements.addAttribute("x", "0");
        elements.addAttribute("y", "0");
        
        // The top level group containing all the shapes and subgroups
        topGroup = new Group(elements, 0, 0);
    }
}

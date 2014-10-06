package net.teslaworks.visualizer;

import java.io.File;
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
	final public List<Shape> shapes;

	// Given config filename, parses all relevant data and stores it
	public ConfigXML(String configFilename) throws DocumentException {
		Document config = new SAXReader().read(new File(configFilename));

		// Target filename to read from
		targetFilename = config
				.selectSingleNode("/vixenlights_viz_cfg/vltarget").getText();

		// Channel count
		Element channels = (Element) config
				.selectSingleNode("/vixenlights_viz_cfg/channels");
		channelCount = Integer.parseInt(channels.attributeValue("count"));

		// Window size
		Element sizeElement = (Element) config
				.selectSingleNode("/vixenlights_viz_cfg/size");
		width = Integer.parseInt(sizeElement.attributeValue("width"));
		height = Integer.parseInt(sizeElement.attributeValue("height"));

		// All the shapes to render
		List<Node> shapeNodes = config
				.selectNodes("/vixenlights_viz_cfg/elements/shape");
		shapes = new ArrayList<>();
		for (Node n : shapeNodes) {
			shapes.add(Shape.makeShape((Element) n));
		}
	}
}

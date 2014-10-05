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

public class ConfigXMLParser {

	// Given config filename, returns list of shapes
	public Document parseConfig(String configFilename) throws DocumentException {
		return parse(new File(configFilename));
	}
	
	public String parseTargetFilename(Document config) {
		return config.selectSingleNode("/vixenlights_viz_cfg/vltarget").getText();
	}
	
	public List<Rectangle> parseRectangles(Document config) {
		List<Node> rects = config.selectNodes("/vixenlights_viz_cfg/elements/rect");
		List<Rectangle> result = new ArrayList<>();
		for (Node rect : rects) {
	        Element e = (Element) rect;
	        result.add(new Rectangle(e));
		}
		return result;
	}
	
    public static int[] parseColor(String colorString) {
    	String[] splitted = colorString.split(",");
    	int[] colors = {0, 0, 0};
    	
    	colors[0] = Integer.parseInt(splitted[0]);
    	colors[1] = Integer.parseInt(splitted[1]);
    	colors[2] = Integer.parseInt(splitted[2]);
		return colors;
	}

	public Document parse(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        return document;
    }
}

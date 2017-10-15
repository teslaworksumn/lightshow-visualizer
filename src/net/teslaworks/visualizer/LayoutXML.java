package net.teslaworks.visualizer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;

import javax.imageio.ImageIO;

public class LayoutXML {

	// Constants representing data from layout file
	public final int[] channelValues;
	public final int width, height;
	public final Color backgroundColor;
	public final BufferedImage backgroundImage;
	public final Group topGroup;

	// Given layout filename, parses all relevant data and stores it
	public LayoutXML(File layoutFile) throws DocumentException, IOException {
		Document layout = new SAXReader().read(layoutFile);
		;

		// Channel count
		Element channels = (Element) layout.selectSingleNode("/layout/channels");
		channelValues = new int[Integer.parseInt(channels.attributeValue("count"))];

		// Window size and color
		Element sizeElement = (Element) layout.selectSingleNode("/layout/size");
		width = Integer.parseInt(sizeElement.attributeValue("width"));
		height = Integer.parseInt(sizeElement.attributeValue("height"));

		// Background color
		Element bgElement = (Element) layout.selectSingleNode("/layout/background");
		int bgRed = Integer.parseInt(bgElement.attributeValue("red"));
		int bgGreen = Integer.parseInt(bgElement.attributeValue("green"));
		int bgBlue = Integer.parseInt(bgElement.attributeValue("blue"));
		backgroundColor = new Color(bgRed, bgGreen, bgBlue);

		backgroundImage = (null == bgElement.attributeValue("image")) ? null
				: ImageIO.read(new File(bgElement.attributeValue("image")));

		// Change top level 'elements' to a group node.
		Element elements = (Element) layout.selectSingleNode("/layout/elements");
		elements.setQName(new QName("group"));

		// The top level group containing all the shapes and subgroups
		topGroup = new Group(elements);
	}
}

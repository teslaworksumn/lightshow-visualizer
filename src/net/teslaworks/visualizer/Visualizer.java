package net.teslaworks.visualizer;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.SwingUtilities;

import net.teslaworks.visualizer.shapes.Rectangle;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.dom4j.Document;

public class Visualizer {

    public static void main(String[] args) throws Exception {
    	
    	ConfigXMLParser parser = new ConfigXMLParser();
    	Document config = parser.parseConfig("config/samplecfg.xml");
    	final String targetFilename = parser.parseTargetFilename(config);
    	// TODO Read size from config document.
    	
    	final List<Rectangle> rects = parser.parseRectangles(config);
    	
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VizFrame frame = new VizFrame(targetFilename, rects);
                frame.setVisible(true);
            }
        });
        
        
    }
}
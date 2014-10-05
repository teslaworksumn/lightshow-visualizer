package net.teslaworks.visualizer;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.SwingUtilities;

import net.teslaworks.visualizer.shapes.Rectangle;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.dom4j.Document;

public class Visualizer {

	public static String targetFilename;

    public static void main(String[] args) throws Exception {
    	
    	ConfigXMLParser parser = new ConfigXMLParser();
    	Document config = parser.parseConfig("config/samplecfg.xml");
    	targetFilename = parser.parseTargetFilename(config);
    	
    	List<Rectangle> rects = parser.parseRectangles(config);
    	for (Rectangle rect : rects) {
    		System.out.println(rect);
    	}
    	
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VizFrame frame = new VizFrame();
                frame.setVisible(true);
                
                ListenerThread lt = new ListenerThread("/Users/rjayatilleka/k.txt", frame);
                lt.start();
            }
        });
        
        
    }
}
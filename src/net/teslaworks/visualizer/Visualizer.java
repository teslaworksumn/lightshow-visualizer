package net.teslaworks.visualizer;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.SwingUtilities;

import net.teslaworks.visualizer.shapes.Rectangle;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.dom4j.Document;

public class Visualizer {
	
	// Filename to read configuration data from.
	private static final String CONFIG_FILENAME = "config/samplecfg.xml";

    public static void main(String[] args) throws Exception {
    	// ConfigXML has constants with all config data after parse
    	final ConfigXML config = new ConfigXML(CONFIG_FILENAME);
    	
    	// Launch the display, passing the config data
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VizFrame frame = new VizFrame(config);
                frame.setVisible(true);
            }
        });
    }
}
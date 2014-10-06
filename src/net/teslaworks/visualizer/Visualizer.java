package net.teslaworks.visualizer;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.SwingUtilities;

import net.teslaworks.visualizer.shapes.Rectangle;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.dom4j.Document;

public class Visualizer {

    public static void main(String[] args) throws Exception {
    	final ConfigXML config = new ConfigXML("config/samplecfg.xml");
    	
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VizFrame frame = new VizFrame(config);
                frame.setVisible(true);
            }
        });
    }
}
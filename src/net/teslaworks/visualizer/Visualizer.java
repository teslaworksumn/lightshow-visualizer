package net.teslaworks.visualizer;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.SwingUtilities;

import net.teslaworks.visualizer.shapes.Rectangle;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.dom4j.Document;

public class Visualizer {

    // File to read layout data from.
	private FileChooser filechooser = new FileChooser();
	private static String LAYOUT_FILENAME = "/layout1.xml";

    public static void main(String[] args) throws Exception {
        // Get vixen log file from command line
        String vltarget="";
        for (int i=0; i<args.length; i++) {
        	if (args[i] == "-tf") {
        		i++;
        		LAYOUT_FILENAME = args[i];
        	}
        	if (args[i] == "-vt") {
        		i++;
        		vltarget = args[i];
        	}
        }
        if (vltarget == "") {
        	System.err.println("ERROR: No Vixen target file specified. Exiting");
        	System.exit(1);
        }
        if (LAYOUT_FILENAME == "") {
        	
        }
        // LayoutXML has constants with all layout data after parse
        final LayoutXML layout = new LayoutXML(LAYOUT_FILENAME);

        // Launch the display, passing the layout data
        final String targetFilename = vltarget;
        SwingUtilities.invokeLater(new Runnable() {
        	
            @Override
            public void run() {
                VizFrame frame = new VizFrame(layout, targetFilename);
                frame.setVisible(true);
            }
        });
    }
}
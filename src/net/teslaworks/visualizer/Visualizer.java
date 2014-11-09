package net.teslaworks.visualizer;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;
import javax.swing.SwingUtilities;

public class Visualizer {

    // File to read layout data from.
	private static FileChooser filechooser = new FileChooser();
	private static String LAYOUT_FILENAME = "";
	private static File LAYOUT_FILE = null;

    public static void main(String[] args) throws Exception {
        // Get vixen log file from command line
        String vltarget="";
        for (int i=0; i<args.length; i++) {
        	if (args[i].equals("-tf")) {
        		i++;
        		LAYOUT_FILENAME = args[i];
        	}
        	if (args[i].equals("-vt")) {
        		i++;
        		vltarget = args[i];
        	}
        }
        if (vltarget == "") {
        	System.err.println("ERROR: No Vixen target file specified. Exiting");
        	System.exit(1);
        }
        if (LAYOUT_FILENAME == "") {
        	filechooser.choose();
        	LAYOUT_FILE = filechooser.getFile();
        } else {
        	LAYOUT_FILE = new File(LAYOUT_FILENAME);
        }
        // LayoutXML has constants with all layout data after parse
        final LayoutXML layout = new LayoutXML(LAYOUT_FILE);

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
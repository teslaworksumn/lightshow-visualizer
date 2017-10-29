package net.teslaworks.visualizer;

import java.io.File;
import java.util.Arrays;
import javax.swing.SwingUtilities;

public class Visualizer {

	// File to read layout data from.
	private static FileChooser filechooser = new FileChooser();

	public static void main(String[] args) throws Exception {
		// Get vixen log file from command line
		String vltarget = "";
		String LAYOUT_FILENAME = "";
		boolean noInput = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-tf")) {
				i++;
				LAYOUT_FILENAME = args[i];
			} else if (args[i].equals("-vt")) {
				i++;
				vltarget = args[i];
			} else if (args[i].equals("--noInput")) {
				noInput = true;
			}
		}
		
		final boolean NO_INPUT = noInput;
		
		if (vltarget.equals("")) {
			System.err.println("ERROR: No Vixen target file specified. Exiting");
			System.exit(1);
		}

		File LAYOUT_FILE;
		if (LAYOUT_FILENAME.equals("")) {
			filechooser.choose();
			LAYOUT_FILE = filechooser.getFile();
		} else {
			LAYOUT_FILE = new File(LAYOUT_FILENAME);
		}

		// LayoutXML has constants with all layout data after parse
		final LayoutXML layout = new LayoutXML(LAYOUT_FILE);

		// Launch the display, passing the layout data
		final String targetFilename = vltarget;
		SwingUtilities.invokeLater(() -> {
			VizFrame frame = new VizFrame(layout, targetFilename, NO_INPUT);
			frame.setVisible(true);

			if (NO_INPUT) {
				Arrays.fill(layout.channelValues, 255);
				frame.repaint();
			}
		});
	}
}
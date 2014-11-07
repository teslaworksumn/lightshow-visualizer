package net.teslaworks.visualizer;

import javax.swing.SwingUtilities;

public class Visualizer {

    // Filename to read layout data from.
    private static final String LAYOUT_FILENAME = "/layout7.xml";

    public static void main(String[] args) throws Exception {
        // Get vixen log file from command line
        final String targetFilename = args[0];
        
        // LayoutXML has constants with all layout data after parse
        final LayoutXML layout = new LayoutXML(LAYOUT_FILENAME);

        // Launch the display, passing the layout data
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VizFrame frame = new VizFrame(layout, targetFilename);
                frame.setVisible(true);
            }
        });
    }
}
package net.teslaworks.visualizer;

import org.apache.commons.io.input.Tailer;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.Executors;

@SuppressWarnings("serial")
public class VizFrame extends JFrame {

	private final int DELAY = 25;

	// Make the frame with specified configuration
	public VizFrame(LayoutXML layout, String targetFilename, boolean noInput) {
		init(layout.width, layout.height);

		// Make the panel we draw to.
		add(new DisplayPanel(layout));

		if (noInput)
			return;

		// Thread to read from file and repaint frame.
		TailListener listener = new TailListener(layout.channelValues, this);
		Tailer.create(new File(targetFilename), listener, DELAY, true);
	}

	// Makes the window.
	private void init(int width, int height) {
		setTitle("Tesla Works DMX Visualizer");
		setSize(width, height);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}

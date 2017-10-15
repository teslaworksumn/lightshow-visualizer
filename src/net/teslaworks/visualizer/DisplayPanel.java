package net.teslaworks.visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class DisplayPanel extends JPanel {

	public final int[] channelValues; // Reference to data buffers
	public final Group topGroup; // Group to render
	public final Color bgColor;
	public final BufferedImage bgImage;
	public final AffineTransform bgXform;

	public DisplayPanel(LayoutXML layout) {
		this.channelValues = layout.channelValues;
		this.topGroup = layout.topGroup;
		this.bgColor = layout.backgroundColor;
		this.bgImage = layout.backgroundImage;

		if (null != bgImage) {
			bgXform = new AffineTransform();
			bgXform.scale((1.0 / bgImage.getWidth()) * layout.width, (1.0 / bgImage.getHeight()) * layout.height);
		} else {
			bgXform = null;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g; // Cast for access to Graphics2D API's

		setBackground(bgColor);
		g2d.drawImage(bgImage, bgXform, null);
		topGroup.paint(g2d, channelValues);
	}
}
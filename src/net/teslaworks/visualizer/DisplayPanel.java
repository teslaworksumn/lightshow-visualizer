package net.teslaworks.visualizer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import net.teslaworks.visualizer.shapes.Shape;

class DisplayPanel extends JPanel {

	int[] channelValues; // Reference to data buffers
	List<Shape> shapes; // Shapes to render each frame
	
	public DisplayPanel(int[] channelValues, List<Shape> shapes) {
		this.channelValues = channelValues;
		this.shapes = shapes;
	}
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // Cast to get access to Graphics2D API's
        
        // Paint each shape, with alpha from the desired channel
        for (Shape shape : shapes) {
        	shape.paint(g2d, channelValues[shape.channel]);
        }
    }
}
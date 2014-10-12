package net.teslaworks.visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import net.teslaworks.visualizer.shapes.Shape;

class DisplayPanel extends JPanel {

    int[] channelValues; // Reference to data buffers
    List<Shape> shapes; // Shapes to render each frame
    Color bgColor;

    public DisplayPanel(LayoutXML layout) {
        this.channelValues = layout.channelValues;
        this.shapes = layout.shapes;
        this.bgColor = layout.background;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // Cast for access to Graphics2D API's
        
        // Set the background color to the value from the layout file
        setBackground(bgColor);

        // Paint each shape, with alpha from the desired channel
        for (Shape shape : shapes) {
            shape.paint(g2d, channelValues[shape.channel]);
        }
    }
}
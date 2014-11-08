package net.teslaworks.visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

class DisplayPanel extends JPanel {

    int[] channelValues; // Reference to data buffers
    Group topGroup; // Group to render
    Color bgColor;

    public DisplayPanel(LayoutXML layout) {
        this.channelValues = layout.channelValues;
        this.topGroup = layout.topGroup;
        this.bgColor = layout.background;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // Cast for access to Graphics2D API's
        
        // Set the background color to the value from the layout file
        setBackground(bgColor);
        
        // Paint the group
        topGroup.paint(g2d, channelValues);

//        Paint each shape, with alpha from the desired channel
//        for (Shape shape : shapes) {
//            shape.paint(g2d, channelValues[shape.channel]);
//        }
    }
}
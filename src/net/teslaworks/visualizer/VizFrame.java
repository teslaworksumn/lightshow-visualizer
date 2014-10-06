package net.teslaworks.visualizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import net.teslaworks.visualizer.shapes.Rectangle;
import net.teslaworks.visualizer.shapes.Shape;

class Surface extends JPanel {
	
	List<Shape> shapes;
	int[] channelValues;
	
	public Surface(int[] channelValues, List<Shape> shapes) {
		this.channelValues = channelValues;
		this.shapes = shapes;
	}

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);
        
        for (Shape shape : shapes) {
        	shape.paint(g2d, channelValues[shape.channel]);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class VizFrame extends JFrame {

    public VizFrame(ConfigXML config) {
    	init(config.width, config.height);
    	
    	int[] channelValues = new int[config.channelCount];
        add(new Surface(channelValues, config.shapes));

        ListenerThread lt = new ListenerThread(config.targetFilename, channelValues, this);
        lt.start();
    }
    
    private void init(int width, int height) {
        setTitle("Tesla Works DMX Visualizer");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

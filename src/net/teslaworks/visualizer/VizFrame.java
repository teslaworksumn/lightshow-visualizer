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
	
	public Surface(int[] channelValues, List<Rectangle> rects) {
		this.channelValues = channelValues;
		shapes = new ArrayList<>();
		for (Rectangle rect : rects) {
			shapes.add((Shape) rect);
		}
	}

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);
        
        for (int i = 0; i < channelValues.length; i++) {
        	shapes.get(i).paint(g2d, channelValues[i]);
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class VizFrame extends JFrame {

    public VizFrame(String target, List<Rectangle> rects) {
    	init();

    	int channelCount = rects.size();
    	int[] channelValues = new int[channelCount];
        add(new Surface(channelValues, rects));
    	
        ListenerThread lt = new ListenerThread(target, channelValues, this);
        lt.start();
    }
    
    private void init() {
        setTitle("Simple Java 2D example");
        setSize(800, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

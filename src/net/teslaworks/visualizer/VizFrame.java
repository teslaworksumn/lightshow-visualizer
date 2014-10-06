package net.teslaworks.visualizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import net.teslaworks.visualizer.shapes.Rectangle;

public class VizFrame extends JFrame {

    public VizFrame(ConfigXML config) {
    	init(config.width, config.height);
    	
    	int[] channelValues = new int[config.channelCount];
        add(new DisplayPanel(channelValues, config.shapes));

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

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

    // Make the frame with specified configuration
    public VizFrame(ConfigXML config) {
        init(config.width, config.height);

        // This is where data will be read to from the file
        int[] channelValues = new int[config.channelCount];

        // Make the panel we draw to.
        add(new DisplayPanel(channelValues, config.shapes, config.background));

        // Thread to read from file and repaint frame.
        TailListenerThread lt = new TailListenerThread(
                config.targetFilename, channelValues, this);
        lt.start();
    }

    // Makes the window.
    private void init(int width, int height) {
        setTitle("Tesla Works DMX Visualizer");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

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
    public VizFrame(LayoutXML layout, String targetFilename) {
        init(layout.width, layout.height);

        // Make the panel we draw to.
        add(new DisplayPanel(layout));

        // Thread to read from file and repaint frame.
        TailListenerThread lt = new TailListenerThread(
                targetFilename, layout.channelValues, this);
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

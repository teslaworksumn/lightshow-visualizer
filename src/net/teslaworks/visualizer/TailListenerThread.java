package net.teslaworks.visualizer;

import java.io.File;

import org.apache.commons.io.input.Tailer;

class TailListenerThread extends Thread {

    TailListener listener;
    String targetFilename;

    final int DELAY = 25;

    public TailListenerThread(
            String targetFilename, int[] channelValues, VizFrame frame) {
        this.targetFilename = targetFilename;
        listener = new TailListener(channelValues, frame); // Handles line reads
    }

    public void run() {
        // Launch the tailer with reading starting from end of file. Then sleep
        Tailer.create(new File(targetFilename), listener, DELAY, true);
        while (true) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
            }
        }
    }
}
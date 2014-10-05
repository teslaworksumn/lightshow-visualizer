package net.teslaworks.visualizer;

import java.io.File;

import org.apache.commons.io.input.Tailer;

class ListenerThread extends Thread {
	VizTailListener listener;
	String filename;
	
	final int DELAY = 25;
	
	public ListenerThread(String filename, int[] channelValues, VizFrame frame) {
        this.listener = new VizTailListener(channelValues, frame);
        this.filename = filename;
	}
	
	@Override
	public void run() {
		Tailer tailer = Tailer.create(new File(filename), listener, DELAY, true);
        while (true) {
            try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
			}
        }
	}
}
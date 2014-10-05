package net.teslaworks.visualizer;

import java.io.File;

import org.apache.commons.io.input.Tailer;

class ListenerThread extends Thread {
	VizTailListener listener;
	String filename;
	
	public ListenerThread(String filename, VizFrame frame) {
        listener = new VizTailListener(frame);
        this.filename = filename;
	}
	
	@Override
	public void run() {
		Tailer tailer = Tailer.create(
        		new File(filename),
        		listener, 0, true);
        while (true) {
            try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
			}
        }
	}
}
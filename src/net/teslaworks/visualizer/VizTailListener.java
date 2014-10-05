package net.teslaworks.visualizer;

import org.apache.commons.io.input.TailerListenerAdapter;

public class VizTailListener extends TailerListenerAdapter{
	
	VizFrame frame;
	
	public VizTailListener(VizFrame frame) {
		this.frame = frame;
	}
	
    @Override
    public void handle(String line) {
        System.out.println(line);
        frame.repaint();
        if (line.equals("exit")) {
        	System.exit(0);
        }
    }

}

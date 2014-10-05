package net.teslaworks.visualizer;

import org.apache.commons.io.input.TailerListenerAdapter;

public class VizTailListener extends TailerListenerAdapter{
	
	private int[] channelValues;
	private VizFrame frame;
	
	public VizTailListener(int[] channelValues, VizFrame frame) {
		this.channelValues = channelValues;
		this.frame = frame;
	}
	
    @Override
    public void handle(String line) {
        String[] splits = line.substring(30).split(" ");
        for (int i = 0; i < channelValues.length; i++) {
        	channelValues[i] = Integer.parseInt(splits[i], 16);
        }
        frame.repaint();
    }

}

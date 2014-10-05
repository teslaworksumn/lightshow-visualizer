package net.teslaworks.visualizer;

import org.apache.commons.io.input.TailerListenerAdapter;

public class VizTailListener extends TailerListenerAdapter{
	
    @Override
    public void handle(String line) {
        System.out.println(line);
    }

}

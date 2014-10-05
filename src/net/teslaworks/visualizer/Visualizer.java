package net.teslaworks.visualizer;

import java.io.File;
import java.util.List;

import net.teslaworks.visualizer.shapes.Rectangle;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.dom4j.Document;

public class Visualizer {

	// Delay between callbacks.
    private static final int SLEEP = 25;

    public static void main(String[] args) throws Exception {
    	Visualizer app = new Visualizer();
//    	if(args.length == 1) {
//    		app.run(args[0]);
//    	}
//    	else {
//    		app.run("/Users/rjayatilleka/k.txt");
//    	}
    	
    	ConfigXMLParser parser = new ConfigXMLParser();
    	Document config = parser.parseConfig("config/samplecfg.xml");
    	String targetFilename = parser.parseTargetFilename(config);
    	List<Rectangle> rects = parser.parseRectangles(config);
    	for (Rectangle rect : rects) {
    		System.out.println(rect);
    	}
    }

    private void run(String filename) throws InterruptedException {
        VizTailListener listener = new VizTailListener();
        @SuppressWarnings("unused")
		Tailer tailer = Tailer.create(
        		new File(filename),
        		listener, SLEEP, true);
        while (true) {
            Thread.sleep(SLEEP);
        }
    }
}
package net.teslaworks.visualizer;

import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;

public class Visualizer {

    private static final int SLEEP = 25;

    public static void main(String[] args) throws Exception {
    	Visualizer app = new Visualizer();
    	if(args.length == 1) {
    		app.run(args[0]);
    	}
    	else {
    		app.run("/Users/rjayatilleka/k.txt");
    	}
    }

    private void run(String filename) throws InterruptedException {
        MyListener listener = new MyListener();
        @SuppressWarnings("unused")
		Tailer tailer = Tailer.create(
        		new File(filename),
        		listener, SLEEP, true);
        while (true) {
            Thread.sleep(SLEEP);
        }
    }

    public class MyListener extends TailerListenerAdapter {

        @Override
        public void handle(String line) {
            System.out.println(line);
        }

    }
}
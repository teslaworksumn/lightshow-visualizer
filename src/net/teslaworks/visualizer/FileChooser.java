package net.teslaworks.visualizer;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class FileChooser {
	private File file;
	public void choose() {
        JFileChooser fc = new JFileChooser() {				
        	private static final long serialVersionUID = 1170852534375941586L;
            protected JDialog createDialog(Component parent)
                    throws HeadlessException {
                JDialog dialog = super.createDialog(parent);
                // config here as needed - just to see a difference
                dialog.setLocationByPlatform(true);
                // might help - can't know because I can't reproduce the problem
                dialog.setAlwaysOnTop(true);
                return dialog;
            }

        };
        int retValue = fc.showOpenDialog(null);
        if (retValue == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
        } else {
            file = null;
        }
    }
	public File getFile() {
		return file;
	}
	public String getFilename() throws IOException {
		return file.getCanonicalPath();
	}
}

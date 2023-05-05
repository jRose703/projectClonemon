package Frames.basicFrames;

import java.awt.Dimension;

import javax.swing.JPanel;

public class BasicPanel extends JPanel{

	public static final int SCREENWIDTH = 600;
	public static final int SCREENHEIGHT = SCREENWIDTH;
	
	private final Dimension SCREENSIZE = new Dimension (SCREENWIDTH, SCREENHEIGHT);
	
	public BasicPanel() {
		
		this.setPreferredSize(SCREENSIZE);
		this.setVisible(true);
		this.setLayout(null);
	}
	
}

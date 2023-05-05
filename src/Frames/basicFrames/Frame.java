package Frames.basicFrames;

import javax.swing.JFrame;

public class Frame extends JFrame{

	private BasicPanel mainPanel;
	
	public Frame() {
		mainPanel = new BasicPanel();
		this.add(mainPanel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Clonemon");
		this.pack();
	}
}
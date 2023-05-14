package Frames;

import Worlds.World;

import javax.swing.JFrame;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class Frame extends JFrame{

	public static final String TITLE = "Clonémon";
	private BasicPanel mainPanel;

	public Frame() {
		mainPanel = new BasicPanel();
		this.add(mainPanel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(TITLE);
		this.pack();
	}

	public void tickWorldPanel(World world){
		this.mainPanel.updateWorldScene(world);
	}
}

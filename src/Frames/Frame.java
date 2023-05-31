package Frames;

import Observer.Observer;
import Worlds.World;

import javax.swing.JFrame;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class Frame extends JFrame{

	public static final String TITLE = "Clonémon";
	private BasicPanel mainPanel;

	public Frame(World world, Observer stateMachineObserver) {
		mainPanel = new BasicPanel(world, stateMachineObserver);
		this.add(mainPanel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(TITLE);
		this.pack();
	}

	public void reloadWorld(){
		mainPanel.reloadWorld();
	}
	public void reloadEntities(){
		mainPanel.reloadEntities();
	}
	public void changeToBattleScene(){
		mainPanel.changeToBattleScene();
	}
	public void changeToWorldScene() {
		mainPanel.changeToWorldScene();
	}

	public void startDialogue(String text) {
		mainPanel.startDialogue(text);
	}

}

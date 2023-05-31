package Frames;

import Observer.Observer;
import Worlds.World;

import javax.swing.*;

public class Frame extends JFrame{

	public static final String TITLE = "Clonémon";
	private final BasicPanel mainPanel;

	/**
	 * Frame setup
	 */
	public Frame(World world, Observer stateMachineObserver) {
		mainPanel = new BasicPanel(world, stateMachineObserver);
		this.add(mainPanel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(TITLE);
		this.pack();
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

	// Werden diese zwei Methoden überhaupt benötigt
	public void reloadWorld() {
		mainPanel.reloadWorld();
	}

	public void reloadEntities() {
		mainPanel.reloadEntities();
	}

}

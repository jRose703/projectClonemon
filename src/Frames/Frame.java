package Frames;

import BattleSystem.Fighter;
import Entity.Entities.OpponentEntity;
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

	public void changeToBattleScene(OpponentEntity opponent, Fighter wildFighter, boolean isTrainerBattle) {
		mainPanel.changeToBattleScene(opponent, wildFighter, isTrainerBattle);
	}

	public void changeToWorldScene() {
		mainPanel.changeToWorldScene();
	}

	public void setOpponentDefeated() {
		mainPanel.setOpponentDefeated();
	}
}

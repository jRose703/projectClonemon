package Frames.BattleUI;

import BattleSystem.BattleSystem;
import Frames.BasicPanel;
import Frames.TextBox.BattleMenuBox;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class BattlePane extends JLayeredPane implements KeyListener, BattleObserver {

	private BattleMenuBox battleBox;
	private final int FIGHTING_PLACE_HEIGHT;
	private FighterUI playerUI;
	private FighterUI opponentUI;

	/**
	 * Starts the graphical battle.
	 */
	public BattlePane() {
		this.battleBox = new BattleMenuBox();
		this.add(battleBox, Integer.valueOf(1));

		FIGHTING_PLACE_HEIGHT = BasicPanel.SCREENHEIGHT - battleBox.getHeight();

		playerUI = new FighterUI();
		playerUI.setBounds(0, FIGHTING_PLACE_HEIGHT / 2, BasicPanel.SCREENWIDTH, FIGHTING_PLACE_HEIGHT / 2);
		this.add(playerUI, Integer.valueOf(0));

		opponentUI = new FighterUI();
		opponentUI.setBounds(0, 0, BasicPanel.SCREENWIDTH, FIGHTING_PLACE_HEIGHT / 2);
		this.add(opponentUI, Integer.valueOf(0));

		this.setVisible(false);
		this.setLayout(null);
	}

	public void setBattle(BattleSystem battle) {
		this.battleBox.setBattle(battle);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		battleBox.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void update(BattleParticipant defender, int newHitpoints) {
		switch (defender) {
			case PLAYER -> playerUI.updateHitpointbar(newHitpoints);
			case OPPONENT -> opponentUI.updateHitpointbar(newHitpoints);
		}
	}
}

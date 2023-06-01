package Frames.BattleUI;

import BattleSystem.BattleSystem;
import Frames.TextBox.BattleMenuBox;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class BattlePane extends JLayeredPane implements KeyListener {

	private BattleMenuBox battleBox;

	/**
	 * Starts the graphical battle.
	 */
	public BattlePane() {
		this.battleBox = new BattleMenuBox();
		this.add(battleBox, Integer.valueOf(2));

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
	public void keyPressed(KeyEvent e) {}
}

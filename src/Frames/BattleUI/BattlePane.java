package Frames.BattleUI;

import BattleSystem.BattleSystem;
import Frames.TextBox.BattleMenuBox;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class BattlePane extends JLayeredPane implements KeyListener {

	private TestPanel testOne;
	private TestPanelTwo testTwo;
	private BattleMenuBox battleBox;

	public BattlePane() {
		this.testOne = new TestPanel();
		this.testOne.setBounds(0, 0, 300, 300);
		this.add(testOne, Integer.valueOf(0));

		this.testTwo = new TestPanelTwo();
		this.testTwo.setBounds(100, 100, 400, 400);
		this.add(testTwo, Integer.valueOf(1));

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
	public void keyPressed(KeyEvent e) {
	}
}

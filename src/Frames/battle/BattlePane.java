package Frames.battle;

import Frames.textBox.*;

import javax.swing.*;

public class BattlePane extends JLayeredPane{

	private TestPanel testOne;
	private TestPanelTwo testTwo;
	private TextBox textBox;

	public BattlePane() {
		this.testOne = new TestPanel();
		this.testOne.setBounds(0, 0, 300, 300);
		this.add(testOne, Integer.valueOf(0));

		this.testTwo = new TestPanelTwo();
		this.testTwo.setBounds(100, 100, 400, 400);
		this.add(testTwo, Integer.valueOf(1));

		this.textBox = new TextBox();
		this.textBox.setMessage("Sein Panzer ist mit einer explosiven Schicht überzogen. Gegnerische Angriffe quittiert es gewaltig.");
		this.add(textBox, Integer.valueOf(2));

		this.setVisible(false);
		this.setLayout(null);
	}
	
}

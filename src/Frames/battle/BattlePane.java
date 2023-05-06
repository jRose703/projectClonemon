package Frames.battle;

import javax.swing.*;

public class BattlePane extends JLayeredPane{

	private TestPanel testOne;
	private TestPanelTwo testTwo;

	public BattlePane() {
		this.testOne = new TestPanel();
		this.testOne.setBounds(0, 0, 300, 300);
		this.add(testOne, Integer.valueOf(0));

		this.testTwo = new TestPanelTwo();
		this.testTwo.setBounds(100, 100, 400, 400);
		this.add(testTwo, Integer.valueOf(1));

		this.setVisible(false);
		this.setLayout(null);
	}
	
}

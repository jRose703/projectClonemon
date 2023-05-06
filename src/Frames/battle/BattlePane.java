package Frames.battle;

import Frames.textBox.*;

import javax.swing.*;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
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
		this.textBox.setMessage("In computer programming, an anonymous function (function literal, lambda abstraction, lambda function, lambda expression or block) is a function definition that is not bound to an identifier. Anonymous functions are often arguments being passed to higher-order functions or used for constructing the result of a higher-order function that needs to return a function. If the function is only used once, or a limited number of times, an anonymous function may be syntactically lighter than using a named function. Anonymous functions are ubiquitous in functional programming languages and other languages with first-class functions, where they fulfil the same role for the function type as literals do for other data types.");
		this.add(textBox, Integer.valueOf(2));

		this.setVisible(false);
		this.setLayout(null);
	}
	
}

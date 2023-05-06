package Frames.textBox;

import Frames.BasicPanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public abstract class AbstractTextBox extends JPanel{

	public static final int TEXT_BOX_HEIGHT = BasicPanel.SCREENHEIGHT / 4;
	public static final int TEXT_BOX_CENTER_X = BasicPanel.SCREENWIDTH / 2;
	public static final int TEXT_BOX_CENTER_Y = TEXT_BOX_HEIGHT / 2;
	
	private Border blackline = BorderFactory.createLineBorder(Color.black);
	public static Border compound = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
	
	public AbstractTextBox(){
		compound = BorderFactory.createCompoundBorder(compound, blackline);
		compound = BorderFactory.createCompoundBorder(blackline, compound);

		this.setBorder(compound);
		this.setBounds(0, BasicPanel.SCREENHEIGHT * 3 / 4, BasicPanel.SCREENWIDTH, TEXT_BOX_HEIGHT);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLayout(null);
	}
}
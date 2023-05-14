package Frames.textBox;

import Frames.BasicPanel;

import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public abstract class AbstractTextBox extends JPanel implements KeyListener {

	protected final int TEXT_BOX_HEIGHT = BasicPanel.SCREENHEIGHT / 4;
	protected final int TEXT_BOX_CENTER_X = BasicPanel.SCREENWIDTH / 2;
	protected final int TEXT_BOX_CENTER_Y = TEXT_BOX_HEIGHT / 2;

	private Border blackline = BorderFactory.createLineBorder(Color.black);
	private Border compound = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
	
	public AbstractTextBox(){
		compound = BorderFactory.createCompoundBorder(compound, blackline);
		compound = BorderFactory.createCompoundBorder(blackline, compound);

		this.setBorder(compound);
		this.setBounds(0, BasicPanel.SCREENHEIGHT * 3 / 4, BasicPanel.SCREENWIDTH, TEXT_BOX_HEIGHT);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.setVisible(true);
		this.setLayout(null);
	}
}
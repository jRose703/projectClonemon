package Frames;

import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class BasicPanel extends JPanel{

	public static final int SCREENWIDTH = 600;
	public static final int SCREENHEIGHT = SCREENWIDTH;

	private final Dimension SCREENSIZE = new Dimension (SCREENWIDTH, SCREENHEIGHT);

	private WorldPane worldPane;
	private BattlePane battlePane;

	public BasicPanel() {
		this.worldPane = new WorldPane();
		this.worldPane.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
		this.add(worldPane);

		this.battlePane = new BattlePane();
		this.battlePane.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
		this.add(battlePane);

		this.setPreferredSize(SCREENSIZE);
		this.setVisible(true);
		this.setLayout(null);
	}

	public void changeToBattleScene(){
		this.worldPane.setVisible(false);
		this.battlePane.setVisible(true);
	}

}
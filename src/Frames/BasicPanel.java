package Frames;

import Frames.BattleUI.BattlePane;
import Frames.WorldUI.WorldPane;
import Observer.Observer;
import Worlds.World;
import BattleSystem.BattleSystem;

import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class BasicPanel extends JPanel{

	public static final int FONT_SIZE = 30;
	public static final int SCREENWIDTH = 600;
	public static final int SCREENHEIGHT = SCREENWIDTH;
	public static final Dimension SCREENSIZE = new Dimension (SCREENWIDTH, SCREENHEIGHT);

	private WorldPane worldPane;
	private BattlePane battlePane;
	private Observer stateMachineObserver;

	public BasicPanel(World world, Observer stateMachineObserver) {
		this.stateMachineObserver = stateMachineObserver;

		this.worldPane = new WorldPane(world, stateMachineObserver);
		this.worldPane.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
		this.add(worldPane);

		this.battlePane = new BattlePane();
		this.battlePane.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
		this.add(battlePane);

		this.setPreferredSize(SCREENSIZE);
		this.setVisible(true);
		this.setLayout(null);

		changeToBattleScene();
		changeToWorldScene();
	}

	public void changeToBattleScene(){
		this.worldPane.setVisible(false);
		this.battlePane.setVisible(true);
		this.battlePane.getBattleBox().setBattle(new BattleSystem(stateMachineObserver));
	}

	public void changeToWorldScene(){
		this.worldPane.setVisible(true);
		this.battlePane.setVisible(false);
	}

	public void reloadWorld(){
		this.worldPane.reloadWorld();
	}
	public void reloadEntities(){
		this.worldPane.reloadEntities();
	}
}

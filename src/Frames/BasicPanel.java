package Frames;

import BattleSystem.BattleSystem;
import Frames.BattleUI.BattlePane;
import Frames.WorldUI.WorldPane;
import Observer.Observer;
import Worlds.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
public class BasicPanel extends JPanel implements KeyListener {

	public static final int FONT_SIZE = 30;
	public static final int SCREENWIDTH = 600;
	public static final int SCREENHEIGHT = SCREENWIDTH;
	public static final Dimension SCREENSIZE = new Dimension(SCREENWIDTH, SCREENHEIGHT);

	private WorldPane worldPane;
	private BattlePane battlePane;
	private Observer stateMachineObserver;
	private int battleCooldown = 0;

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
		this.setFocusable(true);
		this.addKeyListener(this);
		changeToBattleScene();
		changeToWorldScene();
	}

	public void changeToBattleScene() {
		worldPane.setVisible(false);
		battleCooldown = 0;
		battlePane.setVisible(true);
		battlePane.setBattle(new BattleSystem(stateMachineObserver));
	}

	public void changeToWorldScene() {
		battlePane.setVisible(false);
		worldPane.setVisible(true);
	}

	public void startDialogue(String text) {
		worldPane.startDialogue(text);
	}

	public void reloadWorld() {
		this.worldPane.reloadWorld();
	}

	public void reloadEntities() {
		this.worldPane.reloadEntities();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (worldPane.isVisible()) worldPane.keyTyped(e);
		if (battlePane.isVisible()) battlePane.keyTyped(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (worldPane.isVisible()) worldPane.keyReleased(e);
		if (battlePane.isVisible())
			if (battleCooldown != 0)
				battlePane.keyReleased(e);
			else
				battleCooldown = 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
}

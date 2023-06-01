package Frames;

import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import Entity.FighterInventory;
import Frames.BattleUI.BattlePane;
import Frames.BattleUI.BattleParticipant;
import Frames.WorldUI.WorldPane;
import Observer.Observer;
import Worlds.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class BasicPanel extends JPanel implements KeyListener {

	// Screen setup variables
	public static final int FONT_SIZE = 30;
	public static final int SCREENWIDTH = 600;
	public static final int SCREENHEIGHT = SCREENWIDTH;
	public static final Dimension SCREENSIZE = new Dimension(SCREENWIDTH, SCREENHEIGHT);

	private final WorldPane worldPane;
	private final BattlePane battlePane;
	private final Observer stateMachineObserver;
	private final Timer timer;
	private int keyListenerCooldown = 0;

	/**
	 * Container with the scenes: worldPane, battlePane.
	 * The main KeyListener and the tickable method are called from here.
	 */
	public BasicPanel(World world, Observer stateMachineObserver) {
		timer = new Timer();
		this.startTickable();
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
		this.changeToWorldScene();
	}

	public void changeToBattleScene() {
		worldPane.setVisible(false);
		keyListenerCooldown = 0;
		battlePane.setVisible(true);
		battlePane.setBattle(new BattleSystem(stateMachineObserver, battlePane,
				worldPane.getPlayerFighterInv(), new FighterInventory(
				new Fighter[]{
						new Fighter("OpponentOne", BattleParticipant.OPPONENT, 6, 17, 2, 2, 7),
						new Fighter("OpponentTwo", BattleParticipant.OPPONENT, 7, 12, 2, 2, 7)
				}
		)));
	}

	public void changeToWorldScene() {
		battlePane.setVisible(false);
		worldPane.setVisible(true);
	}

	public void startDialogue(String text) {
		worldPane.startDialogue(text);
	}

	private void startTickable() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (worldPane != null && worldPane.isVisible())
					reloadWorld();
			}
		}, 0, 50);//wait 0 milliseconds before doing the action and do it every 1000ms (1 second)
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
			if (keyListenerCooldown != 0)
				battlePane.keyReleased(e);
			else
				keyListenerCooldown = 1;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
}

package Frames;

import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import Entity.FighterInventory;
import Entity.PlayerEntity;
import Frames.BattleUI.BattlePane;
import Frames.BattleUI.BattleParticipant;
import Frames.WorldUI.WorldPane;
import Observer.Observer;
import ReadAndWrite.ReadWritePlayer.ReadPlayerFromJson;
import Worlds.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class BasicPanel extends JPanel implements KeyListener {

	// Screen variables setup
	public static final int FONT_SIZE = 30;
	public static final int SCREENWIDTH = 600;
	public static final int SCREENHEIGHT = SCREENWIDTH;
	public static final Dimension SCREENSIZE = new Dimension(SCREENWIDTH, SCREENHEIGHT);

	private final WorldPane worldPane;
	private final BattlePane battlePane;
	private final Observer stateMachineObserver;
	private final Timer timer;
	private final PlayerEntity player;
	private int keyListenerCooldown = 0;

	// TODO TEST ENEMY
	private final FighterInventory enemy;

	/**
	 * Container with the scenes: worldPane, battlePane.
	 * The main KeyListener and the tickable method are called from here.
	 * Creates and holds the player.
	 */
	public BasicPanel(World world, Observer stateMachineObserver) {
		this.stateMachineObserver = stateMachineObserver;

		//TODO Test enemy
		enemy = new FighterInventory();
		enemy.addToFighterInventory(new Fighter("OpponentOne", BattleParticipant.OPPONENT, 6, 17, 2, 2, 7));
		enemy.addToFighterInventory(new Fighter("OpponentTwo", BattleParticipant.OPPONENT, 7, 12, 2, 2, 7));

		// Creates the tick with 20 ticks per second
		timer = new Timer();
		this.startTickable();

		player = ReadPlayerFromJson.readPlayerFromFile("player");

		// Creates the graphical world
		this.worldPane = new WorldPane(world, player, stateMachineObserver);
		this.worldPane.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
		this.add(worldPane);

		// Creates the graphical battle
		this.battlePane = new BattlePane();
		this.battlePane.setBounds(0, 0, SCREENWIDTH, SCREENHEIGHT);
		this.add(battlePane);

		// Window setup
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
			battlePane.setBattle(new BattleSystem(stateMachineObserver, battlePane, player.getPlayerFighters(), enemy));
		battlePane.setVisible(true);
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
				if (worldPane != null && worldPane.isVisible()){
					reloadWorld();
					reloadEntities();
				}
			}
		}, 0, 50);//wait 0 milliseconds before doing the action and do it every 50ms (0.05 seconds)
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
	public void keyPressed(KeyEvent e) {}
}

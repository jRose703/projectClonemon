package Frames.WorldUI;

import Entity.Entity;
import Entity.PlayerEntity;
import Frames.TextBox.DialogueType;
import Frames.TextBox.TextBox;
import Observer.ObserveType;
import Observer.Observer;
import Worlds.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WorldPane extends JLayeredPane implements KeyListener {

	private static final int TILE_SIZE = 60;

	private final PlayerEntity player;
	private final Observer stateMachineObserver;
	private final TextBox dialogueBox;

	private int moveCooldown;
	private TerrainPanel terrain;
	private EntityPanel entities;
	private World world;
	private final boolean FIXED_SIZE;

	/**
	 * Starts the graphical world.
	 * Terrain displays the world and entities displays all entities except the player.
	 */
	public WorldPane(World world, PlayerEntity player, Observer stateMachineObserver) {
		//Observer Init
		this.stateMachineObserver = stateMachineObserver;

		//Pane Init
		setVisible(true);
		setLayout(null);
		setFocusable(true);
		addKeyListener(this);

		terrain = new TerrainPanel(world, player, TILE_SIZE, world.getXLength(), world.getYLength());
		entities = new EntityPanel(world, player, TILE_SIZE, world.getXLength(), world.getYLength());
		this.world = world;
		add(terrain, Integer.valueOf(0));
		add(entities, Integer.valueOf(1));

		//Player + Player Label Init
		this.player = player;
		this.player.setCoordinates(3, 4);
		moveCooldown = 0;

		//playerLabel.setBounds(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);

		//Textbox Init
		dialogueBox = new TextBox(stateMachineObserver);
		add(dialogueBox, Integer.valueOf(3));

		this.FIXED_SIZE = true;
	}

	// an dieser Stelle sollte der Dialogtyp mit übergeben werden können
	public void startDialogue(String text) {
		dialogueBox.setMessage(text, DialogueType.BATTLE);
	}

	private void startCombat(Entity entity) {
		stateMachineObserver.update(ObserveType.BATTLE_START, entity);
	}

	public void reloadWorld() {
		terrain.reload();
	}

	public void reloadEntities() {
		entities.reload();
	}

	/**
	 * Updates the player location if the movement cooldown equals zero.
	 */
	private void moveAction(int direction) {
		if (moveCooldown == 0 && player.getFacing() == direction) {
			player.move(player.getFacing(), world);
			moveCooldown = 0;
		} else player.setFacing(direction);
		//updatePlayerLabel();
	}

	/**
	 * Updates the graphical player location.
	 */
	private void updatePlayerLabel() {
		//playerLabel.setLocation(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE);
		ImageIcon image;
		switch (player.getFacing()) {
			case 1 -> image = new ImageIcon("assets/entities/player_e.png");
			case 2 -> image = new ImageIcon("assets/entities/player_s.png");
			case 3 -> image = new ImageIcon("assets/entities/player_w.png");
			default -> image = new ImageIcon("assets/entities/player_n.png");
		}
	}

	// man sollte die Gegner von allen Seiten ansprechen können
	private void doCombat() {
		int x = player.getCoordinates().getX();
		int y = player.getCoordinates().getY();
		switch (player.getFacing()) {
			case 0 -> y += 1;
			case 1 -> x += 1;
			case 2 -> y -= 1;
			case 3 -> x -= 1;
		}
		if (world.getEntityArr()[x][y] != null)
			startCombat(world.getEntityArr()[x][y]); // an dieser Stelle sollte startDialogue gestartet werden
	}

	@Override
	public void keyTyped(KeyEvent e) {;
		if (dialogueBox.isVisible()) return;
		switch (e.getKeyChar()) {
			case 'a' -> moveAction(3);
			case 'd' -> moveAction(1);
			case 's' -> moveAction(2);
			case 'w' -> moveAction(0);
			case '\n' -> doCombat();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (dialogueBox.isVisible()) dialogueBox.keyReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {}
}
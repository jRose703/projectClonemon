package Frames.WorldUI;

import Entity.FighterInventory;
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
	private final JLabel playerLabel;
	private final Observer stateMachineObserver;
	private final TextBox dialogueBox;

	private int moveCooldown;
	private TerrainPanel terrain;
	private EntityPanel entities;
	private World world;

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

		terrain = new TerrainPanel(world, TILE_SIZE, 10, 10);
		entities = new EntityPanel(world, TILE_SIZE, 10, 10);
		this.world = world;
		add(terrain, Integer.valueOf(0));
		add(entities, Integer.valueOf(1));

		//Player + Player Label Init
		this.player = player;
		moveCooldown = 0;

		ImageIcon image;
		switch (player.getFacing()) {
			case 1 -> image = new ImageIcon("assets/entities/player_e.png");
			case 2 -> image = new ImageIcon("assets/entities/player_s.png");
			case 3 -> image = new ImageIcon("assets/entities/player_w.png");
			default -> image = new ImageIcon("assets/entities/player_n.png");
		}
		playerLabel = new JLabel(image);

		playerLabel.setBounds(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		add(playerLabel, Integer.valueOf(2));

		//Textbox Init
		dialogueBox = new TextBox(stateMachineObserver);
		add(dialogueBox, Integer.valueOf(3));
	}

	public void startDialogue(String text) {
		dialogueBox.setMessage(text, DialogueType.BATTLE);
	}

	private void startCombat() {
		stateMachineObserver.update(ObserveType.BATTLE_START, null);
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
		updatePlayerLabel();
	}

	/**
	 * Updates the graphical player location.
	 */
	private void updatePlayerLabel() {
		playerLabel.setLocation(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE);
		ImageIcon image;
		switch (player.getFacing()) {
			case 1 -> image = new ImageIcon("assets/entities/player_e.png");
			case 2 -> image = new ImageIcon("assets/entities/player_s.png");
			case 3 -> image = new ImageIcon("assets/entities/player_w.png");
			default -> image = new ImageIcon("assets/entities/player_n.png");
		}
		playerLabel.setIcon(image);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (dialogueBox.isVisible()) return;
		switch (e.getKeyChar()) {
			case 'a' -> moveAction(3);
			case 'd' -> moveAction(1);
			case 's' -> moveAction(2);
			case 'w' -> moveAction(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (dialogueBox.isVisible()) dialogueBox.keyReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {}
}
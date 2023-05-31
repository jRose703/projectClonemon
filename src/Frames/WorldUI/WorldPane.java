package Frames.WorldUI;

import Entity.PlayerEntity;
import Frames.TextBox.TextBox;
import Observer.*;
import Worlds.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WorldPane extends JLayeredPane implements KeyListener {

	private static final int TILE_SIZE = 60;

	private final World world;
	private final PlayerEntity player;
	private final JLabel playerLabel;
	private final Observer stateMachineObserver;

	private int moveCooldown;
	private TerrainPanel terrain;
	private EntityPanel entities;
	private TextBox dialogueBox;

	public WorldPane(World world, Observer stateMachineObserver) {
		//Observer Init
		this.stateMachineObserver = stateMachineObserver;

		//Pane Init
		setVisible(true);
		setLayout(null);
		setFocusable(true);
		addKeyListener(this);

		//World + World Panel Init
		this.world = world;

		terrain = new TerrainPanel(TILE_SIZE, 10, 10);
		entities = new EntityPanel(TILE_SIZE, 10, 10);
		add(terrain, Integer.valueOf(0));
		add(entities, Integer.valueOf(1));

		//Player + Player Label Init
		player = new PlayerEntity();
		player.setCoordinates(3, 4);
		moveCooldown = 0;

		playerLabel = new JLabel(new ImageIcon("assets/entities/player_n.png"));
		playerLabel.setBounds(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		add(playerLabel, Integer.valueOf(2));

		//Textbox Init
		dialogueBox = new TextBox();
		add(dialogueBox, Integer.valueOf(3));
	}

	public void startDialogue(String text) {
		dialogueBox.setMessage(text);
	}

	private void startCombat() {
		stateMachineObserver.update(ObserveType.BATTLE_START, null);
	}

	public void reloadWorld() {
		this.remove(terrain);
		terrain = new TerrainPanel(TILE_SIZE, 10, 10);
		terrain.reload(world);
		this.add(terrain, Integer.valueOf(0));
	}
	public void reloadEntities() {
		this.remove(entities);
		entities = new EntityPanel(TILE_SIZE, 10, 10);
		entities.reload(world);
		this.add(entities, Integer.valueOf(1));
	}
	private void moveAction(int direction) {
		if (moveCooldown == 0 && player.getFacing() == direction) {
			player.move(player.getFacing(), 1);
			moveCooldown = 0;
		}
		else player.setFacing(direction);
		updatePlayerLabel();
	}
	private void updatePlayerLabel() {
		playerLabel.setLocation(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE);
		ImageIcon image;
		switch (player.getFacing()){
			case 1 -> image = new ImageIcon("assets/entities/player_e.png");
			case 2 -> image = new ImageIcon("assets/entities/player_s.png");
			case 3 -> image = new ImageIcon("assets/entities/player_w.png");
			default -> image = new ImageIcon("assets/entities/player_n.png");
		}
		playerLabel.setIcon(image);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(dialogueBox.isVisible()) return;
		switch (e.getKeyChar()) {
			case 'a' -> moveAction(3);
			case 'd' -> moveAction(1);
			case 's' -> moveAction(2);
			case 'w' -> moveAction(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(dialogueBox.isVisible()) dialogueBox.keyReleased(e);
	}
}
package Frames.WorldUI;

import Entity.PlayerEntity;
import Worlds.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WorldPane extends JLayeredPane implements KeyListener {

	private static final int TILE_SIZE = 60;

	private final World world;
	private int moveCooldown;
	private PlayerEntity player;
	private JLabel playerLabel;
	private TerrainPanel terrain;
	private EntityPanel entities;
	public WorldPane(World world) {
		this.setVisible(true);
		this.setLayout(null);
		this.player = new PlayerEntity();
		player.setCoordinates(3, 4);
		this.moveCooldown = 0;

		this.world = world;
		terrain = new TerrainPanel(TILE_SIZE, 10, 10);
		entities = new EntityPanel(TILE_SIZE, 10, 10);
		playerLabel = new JLabel(new ImageIcon("assets/entities/player_n.png"));
		playerLabel.setBounds(player.getCoordinates().getX() * TILE_SIZE, player.getCoordinates().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		this.add(terrain, Integer.valueOf(0));
		this.add(entities, Integer.valueOf(1));
		this.add(playerLabel, Integer.valueOf(2));
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
	public void wPressed() {
		if (moveCooldown == 0) {
			player.move(player.facing_direction, 1);
			moveCooldown = 0;
		}
	}
	public void aPressed() {
		player.facing_direction = (player.facing_direction - 1) % 4;
	}
	public void sPressed() {
		player.facing_direction = (player.facing_direction + 1) % 4;
	}
	public void dPressed() {
		player.facing_direction = (player.facing_direction + 2) % 4;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 65 -> aPressed();
			case 68 -> dPressed();
			case 83 -> sPressed();
			case 87 -> wPressed();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
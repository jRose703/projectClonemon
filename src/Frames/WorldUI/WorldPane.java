package Frames.WorldUI;

import Entity.PlayerEntity;
import Worlds.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WorldPane extends JLayeredPane implements KeyListener {

	private static final int TILE_SIZE = 60;

	private World world;
	private PlayerEntity player;
	private TerrainPanel terrain;
	private EntityPanel entities;
	public WorldPane(World world) {
		this.setVisible(true);
		this.setLayout(null);
		this.player = new PlayerEntity();

		this.world = world;
		terrain = new TerrainPanel(TILE_SIZE, 10, 10);
		entities = new EntityPanel(TILE_SIZE, 10, 10);
		this.add(terrain, Integer.valueOf(0));
		this.add(entities, Integer.valueOf(1));
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

	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyCode()) {
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
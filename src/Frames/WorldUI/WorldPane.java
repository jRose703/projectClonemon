package Frames.WorldUI;

import Worlds.World;

import javax.swing.*;
public class WorldPane extends JLayeredPane{

	private static final int TILE_SIZE = 60;

	private TerrainPanel terrain;
	private EntityPanel entities;
	public WorldPane() {
		this.setVisible(true);
		this.setLayout(null);

		terrain = new TerrainPanel(TILE_SIZE, 10, 10);
		entities = new EntityPanel(TILE_SIZE, 10, 10);
		this.add(terrain, Integer.valueOf(0));
		this.add(entities, Integer.valueOf(1));
	}
	public void reloadWorld(World world) {
		this.remove(terrain);
		terrain = new TerrainPanel(TILE_SIZE, 10, 10);
		terrain.reload(world);
		this.add(terrain, Integer.valueOf(0));
	}
	public void reloadEntities(World world) {
		this.remove(entities);
		entities = new EntityPanel(TILE_SIZE, 10, 10);
		entities.reload(world);
		this.add(entities, Integer.valueOf(1));
	}
}
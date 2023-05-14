package Frames.WorldUI;

import Worlds.World;

import javax.swing.*;
public class WorldPane extends JLayeredPane{

	private static final int TILE_SIZE = 60;

	private final TerrainPane terrain;
	private final EntityPane entities;
	public WorldPane() {
		this.setVisible(true);
		this.setLayout(null);

		terrain = new TerrainPane(TILE_SIZE, 10, 10);
		entities = new EntityPane(TILE_SIZE, 10, 10);
		this.add(terrain, 0);
	}
	public void reloadWorld(World world) {
		terrain.reload(world);
		this.add(terrain, 0);
	}

	/*public void update(World world) {
		Tile[][] map = world.getTileArr();
		InteractableEntity[][] entities = world.getInteractableEntityArr();
		for (int x = 0; x < map.length; x++)
			for (int y = 0; y < map[x].length; y++) {

				ImageIcon image;
				if (map[x][y].getTexture_id() == 1) {
					image = new ImageIcon("assets/tiles/rock_tile.png");
				} else {
					image = new ImageIcon("assets/tiles/low_grass_tile.png");
				}
				System.out.println("Drawing " + map[x][y].getTexture_id() + " on coordinate (" + x + " | " + y + ")");
				JLabel label = new JLabel(image);
				label.setBounds(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				this.add(label);
				if (entities[x][y] != null) {
					ImageIcon entity = new ImageIcon("assets/tiles/rock_tile.png");
					JLabel entityLabel = new JLabel(entity);
					entityLabel.setBounds(x * TILE_SIZE + 10, y * TILE_SIZE + 10, TILE_SIZE - 20, TILE_SIZE - 20);
					this.add(entityLabel);
				}
			}
	}*/
	
}

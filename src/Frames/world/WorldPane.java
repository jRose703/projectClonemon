package Frames.world;

import Worlds.InteractableEntity;
import Worlds.Tiles.Tile;
import Worlds.World;

import javax.swing.*;
public class WorldPane extends JLayeredPane{

	private static final int TILE_WIDTH = 60;
	private static final int TILE_HEIGHT = 60;
	public WorldPane() {

		this.setVisible(true);
		this.setLayout(null);
	}
	public void update(World world) {
		Tile[][] map = world.getTileArr();
		InteractableEntity[][] entities = world.getInteractableEntityArr();
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++) {

				ImageIcon image;
				if (map[i][j].getTexture_id() == 1) {
					image = new ImageIcon("assets/tiles/rock_tile.png");
				} else {
					image = new ImageIcon("assets/tiles/low_grass_tile.png");
				}

				JLabel label = new JLabel(image);
				label.setBounds(i * TILE_WIDTH, j * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
				this.add(label);
				if (entities[i][j] != null) {
					ImageIcon entity = new ImageIcon("assets/tiles/rock_tile.png");
					JLabel entityLabel = new JLabel(entity);
					entityLabel.setBounds(i * TILE_WIDTH + 10, j * TILE_HEIGHT + 10, TILE_WIDTH - 20, TILE_HEIGHT - 20);
					this.add(entityLabel);
				}
			}
	}
	
}

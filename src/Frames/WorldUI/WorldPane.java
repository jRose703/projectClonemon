package Frames.WorldUI;

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
				label.setBounds(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
				this.add(label);
				if (entities[x][y] != null) {
					ImageIcon entity = new ImageIcon("assets/tiles/rock_tile.png");
					JLabel entityLabel = new JLabel(entity);
					entityLabel.setBounds(x * TILE_WIDTH + 10, y * TILE_HEIGHT + 10, TILE_WIDTH - 20, TILE_HEIGHT - 20);
					this.add(entityLabel);
				}
			}
	}
	
}

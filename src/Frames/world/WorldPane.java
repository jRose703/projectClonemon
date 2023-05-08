package Frames.world;

import Worlds.InteractableEntity;
import Worlds.Tiles.Tile;
import Worlds.World;

import javax.swing.*;

public class WorldPane extends JLayeredPane{

	private static int TILE_WIDTH = 60;
	private static int TILE_HEIGHT = 60;
	public WorldPane() {

		this.setVisible(true);
		this.setLayout(null);
	}
	public void update(World world) {
		Tile[][] map = world.getTileArr();
		InteractableEntity[][] entities = world.getInteractableEntityArr();
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++) {
				ImageIcon image = new ImageIcon();
				JLabel label = new JLabel();

			}
	}
	
}

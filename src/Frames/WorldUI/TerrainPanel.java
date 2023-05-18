package Frames.WorldUI;

import Worlds.Tiles.Tile;
import Worlds.World;

import javax.swing.*;
import java.awt.*;

public class TerrainPanel extends JPanel {

    private final int TILE_SIZE;
    private final int X_FIELDS;
    private final int Y_FIELDS;
    public TerrainPanel(int TILE_SIZE, int X_FIELDS, int Y_FIELDS) {
        this.TILE_SIZE = TILE_SIZE;
        this.X_FIELDS = X_FIELDS;
        this.Y_FIELDS = Y_FIELDS;
        this.setBounds(0, 0, TILE_SIZE * X_FIELDS, TILE_SIZE * Y_FIELDS);
        this.setBackground(Color.black);
        this.setLayout(null);
    }

    public void reload(World world) {
        Tile[][] map = world.getTileArr();
        for (int x = 0; x < X_FIELDS; x++)
            for (int y = 0; y < Y_FIELDS; y++) {

                ImageIcon image;
                if (map[x][y].getTexture_id() == 1) {
                    image = new ImageIcon("assets/tiles/rock_tile.png");
                } else {
                    image = new ImageIcon("assets/tiles/low_grass_tile.png");
                }
                JLabel label = new JLabel(image);
                label.setBounds(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                this.add(label);
            }
    }
}

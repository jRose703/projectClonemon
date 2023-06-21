package Frames.WorldUI;

import Entity.PlayerEntity;
import Worlds.Tiles.Tile;
import Worlds.World;

import javax.swing.*;
import java.awt.*;

public class TerrainPanel extends JPanel {

    private final int TILE_SIZE;
    private final int X_FIELDS;
    private final int Y_FIELDS;
    private final World WORLD;
    private final PlayerEntity PLAYER;

    public TerrainPanel(World world, PlayerEntity player, int TILE_SIZE, int X_FIELDS, int Y_FIELDS) {
        this.TILE_SIZE = TILE_SIZE;
        this.X_FIELDS = X_FIELDS;
        this.Y_FIELDS = Y_FIELDS;
        this.WORLD = world;
        this.PLAYER = player;
        this.setBounds(0, 0, TILE_SIZE * X_FIELDS, TILE_SIZE * Y_FIELDS);
        this.setBackground(Color.black);
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        Tile[][] map = WORLD.getTileArr();
        /*for (int x = 0; x < X_FIELDS; x++)
            for (int y = 0; y < Y_FIELDS; y++) {

                ImageIcon image;
                if (map[x][y].getTexture_id() == 1) {
                    image = new ImageIcon("assets/tiles/rock_tile.png");
                } else {
                    image = new ImageIcon("assets/tiles/low_grass_tile.png");
                }
                g.drawImage(image.getImage(), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }*/
        int screenX = 0;
        int screenY = 0;
        for (int x = PLAYER.getCoordinates().getX() - 4; x <= PLAYER.getCoordinates().getX() + 5; x++) {
            for (int y = PLAYER.getCoordinates().getY() - 4; y <= PLAYER.getCoordinates().getY() + 5; y++) {
                ImageIcon image;
                try {
                    switch (map[x][y].getTexture_id()) {
                        case 0 -> image = new ImageIcon("assets/tiles/low_grass_tile.png");
                        case 1 -> image = new ImageIcon("assets/tiles/rock_tile.png");
                        default -> image = new ImageIcon("assets/tiles/rock_tile.png");
                    }
                } catch (Exception ex) {
                    image = new ImageIcon("assets/tiles/rock_tile.png");
                }
                g.drawImage(image.getImage(), screenX * TILE_SIZE, screenY * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                screenY++;
            }
            screenX++;
            screenY = 0;
        }
    }

    public void reload() {
        repaint();
    }

}

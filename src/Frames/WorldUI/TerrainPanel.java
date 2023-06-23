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
        int screenX = 0;
        int screenY = 0;

        int centreX = PLAYER.getCoordinates().getX();
        int centreY = PLAYER.getCoordinates().getY();

        if(centreX < 4)
            centreX = 4;
        if(centreX > X_FIELDS - 5)
            centreX = X_FIELDS - 5;
        if(centreY < 4)
            centreY = 4;
        if(centreY > Y_FIELDS - 5)
            centreY = Y_FIELDS - 5;

        for (int x = centreX - 4; x <= centreX + 4; x++) {
            for (int y = centreY - 4; y <= centreY + 4; y++) {
                ImageIcon image;
                try {
                    switch (map[x][y].getTexture_id()) {
                        case 0 -> image = new ImageIcon("assets/tiles/low_grass_tile.png");
                        case 1 -> image = new ImageIcon("assets/tiles/rock_tile.png");
                        case 2 -> image = new ImageIcon("assets/tiles/water_tile.png");
                        case 3 -> image = new ImageIcon("assets/tiles/door_tile.png");
                        default -> image = new ImageIcon("assets/tiles/void_tile.png");
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

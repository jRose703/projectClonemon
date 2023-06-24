package Frames.WorldUI;

import Entity.*;
import Worlds.World;

import javax.swing.*;
import java.awt.*;

public class EntityPanel extends JPanel {

    private final int TILE_SIZE;
    private final int X_FIELDS;
    private final int Y_FIELDS;
    private final World WORLD;
    private final PlayerEntity PLAYER;

    public EntityPanel(World world, PlayerEntity player, int TILE_SIZE, int X_FIELDS, int Y_FIELDS) {
        this.TILE_SIZE = TILE_SIZE;
        this.X_FIELDS = X_FIELDS;
        this.Y_FIELDS = Y_FIELDS;
        this.WORLD = world;
        this.PLAYER = player;
        this.setBounds(0, 0, TILE_SIZE * X_FIELDS, TILE_SIZE * Y_FIELDS);
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        Entity[][] entities = WORLD.getEntityArr();
        int screenX = 0;
        int screenY = 0;

        int playerOffsetX = 0;
        int playerOffsetY = 0;

        int centreX = PLAYER.getCoordinates().getX();
        int centreY = PLAYER.getCoordinates().getY();

        if(centreX < 4) {
            playerOffsetX = 4 - centreX;
            centreX = 4;
        }
        if(centreX > X_FIELDS - 5) {
            playerOffsetX = (X_FIELDS - 5) - centreX;
            centreX = X_FIELDS - 5;
        }

        if(centreY < 4) {
            playerOffsetY = 4 - centreY;
            centreY = 4;
        }
        if(centreY > Y_FIELDS - 5) {
            playerOffsetY = (Y_FIELDS - 5) - centreY;
            centreY = Y_FIELDS - 5;
        }

        for (int x = centreX - 4; x <= centreX + 4; x++) {
            for (int y = centreY - 4; y <= centreY + 4; y++) {
                ImageIcon icon;
                try {
                    if(entities[x][y] != null) {
                        if (entities[x][y] instanceof OpponentEntity) {
                            switch (((OpponentEntity) entities[x][y]).getFacing()) {
                                case 1 -> icon = new ImageIcon("assets/entities/entity_e.png");
                                case 2 -> icon = new ImageIcon("assets/entities/entity_s.png");
                                case 3 -> icon = new ImageIcon("assets/entities/entity_w.png");
                                default -> icon = new ImageIcon("assets/entities/entity_n.png");
                            }
                        }
                        else {icon = new ImageIcon();}
                    }
                    else{
                        icon = new ImageIcon();}
                } catch (Exception ex) {
                    icon = new ImageIcon();
                }
                g.drawImage(icon.getImage(), screenX * TILE_SIZE + 10, screenY * TILE_SIZE + 10, null);

                screenY++;
            }
            screenX++;
            screenY = 0;
        }
        ImageIcon image;
        switch (PLAYER.getFacing()) {
            case 1 -> image = new ImageIcon("assets/entities/player_e.png");
            case 2 -> image = new ImageIcon("assets/entities/player_s.png");
            case 3 -> image = new ImageIcon("assets/entities/player_w.png");
            default -> image = new ImageIcon("assets/entities/player_n.png");
        }
        g.drawImage(image.getImage(), (4 - playerOffsetX) * TILE_SIZE + 10, (4 - playerOffsetY) * TILE_SIZE + 10, null);
    }

    public void reload() {
        repaint();
    }
}

package Frames.WorldUI;

import Entity.Entity;
import Worlds.World;

import javax.swing.*;
import java.awt.*;

public class EntityPanel extends JPanel {

    private final int TILE_SIZE;
    private final int X_FIELDS;
    private final int Y_FIELDS;
    private final World WORLD;

    public EntityPanel(World world, int TILE_SIZE, int X_FIELDS, int Y_FIELDS) {
        this.TILE_SIZE = TILE_SIZE;
        this.X_FIELDS = X_FIELDS;
        this.Y_FIELDS = Y_FIELDS;
        this.WORLD = world;
        this.setBounds(0, 0, TILE_SIZE * X_FIELDS, TILE_SIZE * Y_FIELDS);
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        Entity[][] entities = WORLD.getEntityArr();
        for (int x = 0; x < X_FIELDS; x++)
            for (int y = 0; y < Y_FIELDS; y++) {
                ImageIcon icon;
                if (entities[x][y] == null)
                    continue;

                switch (1) { //TODO Replace the switch with some kind of system that tracks the entities in the world
                    case 1 -> icon = new ImageIcon("assets/entities/entity_e.png");
                    case 2 -> icon = new ImageIcon("assets/entities/entity_s.png");
                    case 3 -> icon = new ImageIcon("assets/entities/entity_w.png");
                    default -> icon = new ImageIcon("assets/entities/entity_n.png");
                }
                g.drawImage(icon.getImage(), x * TILE_SIZE + 10, y * TILE_SIZE + 10, TILE_SIZE, TILE_SIZE, null);
            }
    }

    public void reload() {
        repaint();
    }

    /*public void reload(World world) {
        InteractableEntity[][] entities = world.getInteractableEntityArr();
        for (int x = 0; x < X_FIELDS; x++)
            for (int y = 0; y < Y_FIELDS; y++) {
                ImageIcon icon;
                if (entities[x][y] == null)
                    continue;

                switch (1) { //TODO Replace the switch with some kind of system that tracks the entities in the world
                    case 1 ->
                            icon = new ImageIcon("assets/entities/entity_e.png");
                    case 2 ->
                            icon = new ImageIcon("assets/entities/entity_s.png");
                    case 3 ->
                            icon = new ImageIcon("assets/entities/entity_w.png");
                    default ->
                            icon = new ImageIcon("assets/entities/entity_n.png");
                }
                JLabel entitylabel = new JLabel(icon);
                entitylabel.setBounds(x * TILE_SIZE + 10, y * TILE_SIZE + 10, TILE_SIZE, TILE_SIZE);
                this.add(entitylabel);
            }
    }*/
}

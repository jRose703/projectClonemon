package Frames.WorldUI;

import javax.swing.*;

public class EntityPanel extends JPanel {

    private final int TILE_SIZE;
    private final int X_FIELDS;
    private final int Y_FIELDS;
    public EntityPanel(int TILE_SIZE, int X_FIELDS, int Y_FIELDS) {
        this.TILE_SIZE = TILE_SIZE;
        this.X_FIELDS = X_FIELDS;
        this.Y_FIELDS = Y_FIELDS;
        this.setBounds(100, 100, 200, 200);
        this.setVisible(true);
        this.setOpaque(false);
        this.setLayout(null);
    }
    public void reload() {

    }
}

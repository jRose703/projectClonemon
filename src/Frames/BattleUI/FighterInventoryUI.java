package Frames.BattleUI;

import BattleSystem.Fighter;
import Entity.FighterInventory;
import Frames.BasicPanel;

import javax.swing.*;
import java.awt.*;

public class FighterInventoryUI extends JPanel {

    FighterInventory playerFighters;

    // cursor coordinates
    private int cursor_x;
    private int cursor_y;

    public FighterInventoryUI(FighterInventory playerFighters) {
        this.playerFighters = playerFighters;

        // Panel setup
        this.setBounds(0, 0, BasicPanel.SCREENWIDTH, BasicPanel.SCREENHEIGHT);
        this.setBackground(Color.lightGray);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;

        for (Fighter fighter : playerFighters.getFighterInventory()) {
            g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));
            g.drawString(fighter.getName(),
                    (int) (x * 0.5 * BasicPanel.SCREENWIDTH + 0.1 * BasicPanel.SCREENWIDTH),
                    (int) (y * 0.25 * BasicPanel.SCREENWIDTH + 0.15 * BasicPanel.SCREENWIDTH));

            g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE / 2));
            g.drawString("HP: " + fighter.getHitpoints() + " / " + fighter.getMaxHitpoints(),
                    (int) (x * 0.5 * BasicPanel.SCREENWIDTH + 0.1 * BasicPanel.SCREENWIDTH),
                    (int) (y * 0.25 * BasicPanel.SCREENWIDTH + 0.185 * BasicPanel.SCREENWIDTH));
            if (x != 1)
                x++;
            else {
                x = 0;
                y++;
            }
        }

        g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));
        g.drawString("BACK", 8 * BasicPanel.SCREENWIDTH / 10, 95 * BasicPanel.SCREENHEIGHT / 100);

/*        Polygon triangle = BasicPanel.drawCursor(g, this.cursor_x, this.cursor_y);

        g.drawPolygon(triangle);
        g.fillPolygon(triangle);*/
    }

    public void showUI() {
        repaint();
        setVisible(true);
    }

}

package Frames.BattleUI;

import Frames.BasicPanel;

import javax.swing.*;
import java.awt.*;

public class FighterUI extends JPanel {

    private final int backSprite_x = BasicPanel.SCREENWIDTH * 2 / 15;
    private final int frontSprite_x = BasicPanel.SCREENWIDTH * 6 / 10;

    private int maxHP = 10; // the max value changes every time a new fighter enters the battle
    private String name = "";
    private ImageIcon frontSprite;
    private ImageIcon backSprite;
    private final JProgressBar hitpointBar;

    public FighterUI() {
        // Adds the hitpointBar and configures it
        hitpointBar = new JProgressBar();
        hitpointBar.setForeground(Color.GREEN);
        hitpointBar.setMinimum(0);
        hitpointBar.setBounds(frontSprite_x, BasicPanel.SCREENHEIGHT * 2 / 10, BasicPanel.SCREENWIDTH / 4, BasicPanel.SCREENHEIGHT / 80);
        this.add(hitpointBar);

        // Panel setup
        this.setVisible(true);
        this.setLayout(null);
    }

    public void updateNewFighter(String name, int maxHP, int newHP, ImageIcon backSprite, ImageIcon frontSprite) {
        this.name = name;
        this.backSprite = backSprite;
        this.frontSprite = frontSprite;
        if (this.frontSprite != null)
            hitpointBar.setBounds(backSprite_x, BasicPanel.SCREENHEIGHT * 2 / 10, BasicPanel.SCREENWIDTH / 4, BasicPanel.SCREENHEIGHT / 80);

        this.maxHP = maxHP;
        hitpointBar.setMaximum(maxHP);
        updateHitpointbar(newHP);
    }

    public void updateHitpointbar(int newHP) {
        hitpointBar.setValue(newHP);
        repaint();
        if (5 * hitpointBar.getValue() <= maxHP) hitpointBar.setForeground(Color.RED);
        else if (2 * hitpointBar.getValue() <= maxHP) hitpointBar.setForeground(Color.YELLOW);
        else hitpointBar.setForeground(Color.GREEN);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE / 2));

        if (frontSprite != null)
            g.drawImage(frontSprite.getImage(), frontSprite_x, BasicPanel.SCREENHEIGHT * 7 / 60, BasicPanel.SCREENWIDTH / 4, BasicPanel.SCREENHEIGHT / 4, null);
        if (backSprite != null)
            g.drawImage(backSprite.getImage(), backSprite_x, BasicPanel.SCREENHEIGHT * 7 / 60, BasicPanel.SCREENWIDTH / 4, BasicPanel.SCREENHEIGHT / 4, null);

        int hp_x_coordinate = (frontSprite != null) ? backSprite_x : frontSprite_x;
        g.drawString(name, hp_x_coordinate, BasicPanel.SCREENHEIGHT * 18 / 100);
        g.drawString("HP: " + hitpointBar.getValue() + "/" + hitpointBar.getMaximum(), hp_x_coordinate, BasicPanel.SCREENHEIGHT * 24 / 100);
    }
}

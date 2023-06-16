package Frames.BattleUI;

import Frames.BasicPanel;

import javax.swing.*;
import java.awt.*;

public class FighterUI extends JPanel {

    private int maxHP = 10; // the max value changes every time a new fighter enters the battle
    private final JProgressBar hitpointBar;

    public FighterUI() {
        // Adds the hitpointBar and configures it
        hitpointBar = new JProgressBar();
        hitpointBar.setForeground(Color.GREEN);
        hitpointBar.setMinimum(0);
        hitpointBar.setMaximum(maxHP);
        hitpointBar.setValue(maxHP);
        hitpointBar.setBounds(55, 40, BasicPanel.SCREENWIDTH / 4, BasicPanel.SCREENHEIGHT / 80);
        this.add(hitpointBar);

        // Panel setup
        this.setBackground(Color.darkGray);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void updateNewFighter(int maxHitpoints, int newHitpoints) {
        maxHP = maxHitpoints;
        hitpointBar.setMaximum(maxHitpoints);
        updateHitpointbar(newHitpoints);
    }

    public void updateHitpointbar(int newHitpoints) {
        hitpointBar.setValue(newHitpoints);
        if (5 * hitpointBar.getValue() <= maxHP) hitpointBar.setForeground(Color.RED);
        else if (2 * hitpointBar.getValue() <= maxHP) hitpointBar.setForeground(Color.YELLOW);
        else hitpointBar.setForeground(Color.GREEN);
    }

}

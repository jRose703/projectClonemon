package Frames.BattleUI;

import Frames.BasicPanel;

import javax.swing.*;
import java.awt.*;

public class FighterUI extends JPanel {

    private final int MAX_HP = 10; // TODO
    private JProgressBar hitpointBar;

    public FighterUI() {
        hitpointBar = new JProgressBar();
        hitpointBar.setForeground(Color.GREEN);
        hitpointBar.setMinimum(0);
        hitpointBar.setMaximum(MAX_HP);
        hitpointBar.setValue(MAX_HP);
        hitpointBar.setBounds(55, 40, BasicPanel.SCREENWIDTH / 4, BasicPanel.SCREENHEIGHT / 80);
        this.add(hitpointBar);

        this.setBackground(Color.darkGray);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void updateHitpointbar(int newHitpoints) {
        hitpointBar.setValue(newHitpoints);
        if (2 * hitpointBar.getValue() <= MAX_HP && 5 * hitpointBar.getValue() > MAX_HP)
            hitpointBar.setForeground(Color.YELLOW);
        if (5 * hitpointBar.getValue() <= MAX_HP) hitpointBar.setForeground(Color.RED);
    }

}

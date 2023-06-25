package Frames.TextBox;

import BattleSystem.BattleAction;
import BattleSystem.BattleSystem;
import Frames.BasicPanel;
import Frames.BattleUI.FighterInventoryUI;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BattleMenuBox extends AbstractTextBox{
    private BattleSystem battle;
    private final FighterInventoryUI fighterInventoryUI;

    // for code readability
    private final int LEFT = TEXT_BOX_CENTER_X * 2 / 5 - BasicPanel.FONT_SIZE;
    private final int RIGHT = TEXT_BOX_CENTER_X * 4 / 3 - BasicPanel.FONT_SIZE;
    private final int TOP = TEXT_BOX_CENTER_Y * 5 / 6 - BasicPanel.FONT_SIZE * 2 / 3;
    private final int BOTTOM = TEXT_BOX_CENTER_Y * 3 / 2 - BasicPanel.FONT_SIZE * 2 / 3;

    // cursor coordinates
    private int cursor_x = LEFT;
    private int cursor_y = TOP;

    public BattleMenuBox(FighterInventoryUI fighterInventoryUI) {
        super();
        this.fighterInventoryUI = fighterInventoryUI;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));

        g.drawString("Fight", TEXT_BOX_CENTER_X * 2 / 5, TEXT_BOX_CENTER_Y * 5 / 6);
        g.drawString("Clmn", TEXT_BOX_CENTER_X * 4 / 3, TEXT_BOX_CENTER_Y * 5 / 6);
        g.drawString("Items", TEXT_BOX_CENTER_X * 2 / 5, TEXT_BOX_CENTER_Y * 3 / 2);
        g.drawString("Run", TEXT_BOX_CENTER_X * 4 / 3, TEXT_BOX_CENTER_Y * 3 / 2);

        BasicPanel.drawCursor(g, this.cursor_x, this.cursor_y);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10 -> chooseAction();
            case 37 -> {
                cursor_x = LEFT;
                repaint();
            }
            case 38 -> {
                cursor_y = TOP;
                repaint();
            }
            case 39 -> {
                cursor_x = RIGHT;
                repaint();
            }
            case 40 -> {
                cursor_y = BOTTOM;
                repaint();
            }
        }
    }

    public void setBattle(BattleSystem newBattle) {
        this.battle = newBattle;
    }

    private void chooseAction() {
        if (cursor_x == LEFT && cursor_y == TOP)
            this.battle.round(BattleAction.FIGHT);
        else if (cursor_x == LEFT && cursor_y == BOTTOM)
            System.out.println(BattleAction.ITEMS);
        else if (cursor_x == RIGHT && cursor_y == TOP)
            fighterInventoryUI.showUI(true);
        else if (cursor_x == RIGHT && cursor_y == BOTTOM)
            this.battle.round(BattleAction.RUN);
        else throw new IllegalArgumentException("Cursor coorinates are out of bound!");
    }
}

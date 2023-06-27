package Frames.TextBox;

import BattleSystem.BattleSystem;
import BattleSystem.enums.BattleAction;
import Frames.BasicPanel;
import Frames.InventoryUI.FighterInventoryUI;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuBox extends AbstractTextBox {
    private BattleSystem battle;
    private final FighterInventoryUI fighterInventoryUI;
    private final MenuType menuType;

    // for code readability
    private final int LEFT = TEXT_BOX_CENTER_X * 2 / 5 - BasicPanel.FONT_SIZE;
    private final int RIGHT = TEXT_BOX_CENTER_X * 4 / 3 - BasicPanel.FONT_SIZE;
    private final int TOP = TEXT_BOX_CENTER_Y * 5 / 6 - BasicPanel.FONT_SIZE * 2 / 3;
    private final int BOTTOM = TEXT_BOX_CENTER_Y * 3 / 2 - BasicPanel.FONT_SIZE * 2 / 3;

    // cursor coordinates
    private int cursor_x = LEFT;
    private int cursor_y = TOP;

    public MenuBox(FighterInventoryUI fighterInventoryUI, MenuType menuType) {
        super();
        this.fighterInventoryUI = fighterInventoryUI;
        this.menuType = menuType;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));

        String drawString = (menuType.equals(MenuType.BATTLE)) ? "Fight" : "Bag";
        g.drawString(drawString, TEXT_BOX_CENTER_X * 2 / 5, TEXT_BOX_CENTER_Y * 5 / 6);

        g.drawString("Fighter", TEXT_BOX_CENTER_X * 4 / 3, TEXT_BOX_CENTER_Y * 5 / 6);

        drawString = (menuType.equals(MenuType.BATTLE)) ? "Items" : "Save";
        g.drawString(drawString, TEXT_BOX_CENTER_X * 2 / 5, TEXT_BOX_CENTER_Y * 3 / 2);

        drawString = (menuType.equals(MenuType.BATTLE)) ? "Run" : "Exit";
        g.drawString(drawString, TEXT_BOX_CENTER_X * 4 / 3, TEXT_BOX_CENTER_Y * 3 / 2);

        BasicPanel.drawCursor(g, this.cursor_x, this.cursor_y);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10 -> chooseAction();
            case 27 -> {
                if (menuType.equals(MenuType.WORLD)) setVisible(false);
            }
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
        if (menuType.equals(MenuType.WORLD)) return;
        this.battle = newBattle;
    }

    private void chooseAction() {
        if (cursor_x == LEFT && cursor_y == TOP) {
            if (menuType.equals(MenuType.BATTLE)) this.battle.round(BattleAction.FIGHT);
            else System.out.println("BAG");
        } else if (cursor_x == LEFT && cursor_y == BOTTOM) {
            if (menuType.equals(MenuType.BATTLE)) System.out.println(BattleAction.ITEMS);
            else System.out.println("SAVE");
        } else if (cursor_x == RIGHT && cursor_y == TOP) {
            fighterInventoryUI.showUI(true);
        } else if (cursor_x == RIGHT && cursor_y == BOTTOM) {
            if (menuType.equals(MenuType.BATTLE)) this.battle.round(BattleAction.RUN);
            else this.setVisible(false);
        } else throw new IllegalArgumentException("Cursor coordinates are out of bound!");
    }
}

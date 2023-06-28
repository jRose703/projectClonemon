package Frames.TextBox;

import BattleSystem.BattleSystem;
import BattleSystem.enums.BattleAction;
import Entity.Entities.PlayerEntity;
import Frames.BasicPanel;
import Frames.InventoryUI.FighterInventoryUI;
import Frames.InventoryUI.ItemInventoryUI;
import ReadAndWrite.PlayerOperations.WritePlayerToJson;
import ReadAndWrite.WorldOperations.WriteWorldToJson;
import Worlds.World;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuBox extends AbstractTextBox {
    private BattleSystem battle;
    private final FighterInventoryUI fighterInventoryUI;
    private final ItemInventoryUI itemInventoryUI;
    private final MenuType menuType;

    private PlayerEntity player;
    private World world;

    // for code readability
    private final int LEFT = TEXT_BOX_CENTER_X * 2 / 5 - BasicPanel.FONT_SIZE;
    private final int RIGHT = TEXT_BOX_CENTER_X * 4 / 3 - BasicPanel.FONT_SIZE;
    private final int TOP = TEXT_BOX_CENTER_Y * 5 / 6 - BasicPanel.FONT_SIZE * 2 / 3;
    private final int BOTTOM = TEXT_BOX_CENTER_Y * 3 / 2 - BasicPanel.FONT_SIZE * 2 / 3;

    // cursor coordinates
    private int cursor_x = LEFT;
    private int cursor_y = TOP;

    public MenuBox(FighterInventoryUI fighterInventoryUI, ItemInventoryUI itemInventoryUI, MenuType menuType) {
        super();
        this.fighterInventoryUI = fighterInventoryUI;
        this.itemInventoryUI = itemInventoryUI;
        this.menuType = menuType;
    }

    public void setSaveRequirements(PlayerEntity player, World world) {
        this.player = player;
        this.world = world;
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
                if (menuType.equals(MenuType.WORLD))
                    closeMenu();
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
            else itemInventoryUI.showUI();

        } else if (cursor_x == LEFT && cursor_y == BOTTOM) {
            if (menuType.equals(MenuType.BATTLE)) itemInventoryUI.showUI();
            else {
                WriteWorldToJson.saveWorld(world, player.getCurrentWorld());
                WritePlayerToJson.savePlayer(player, "player");
            }

        } else if (cursor_x == RIGHT && cursor_y == TOP) {
            fighterInventoryUI.showUI(true, false, 0);

        } else if (cursor_x == RIGHT && cursor_y == BOTTOM) {
            if (menuType.equals(MenuType.BATTLE)) this.battle.round(BattleAction.RUN);
            else closeMenu();

        } else throw new IllegalArgumentException("Cursor coordinates are out of bound!");
    }

    private void closeMenu() {
        cursor_x = LEFT;
        cursor_y = TOP;
        setVisible(false);
    }

}

package Frames.InventoryUI;

import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import BattleSystem.enums.BattleAction;
import Entity.FighterInventory;
import Frames.BasicPanel;
import Frames.TextBox.MenuType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FighterInventoryUI extends JPanel implements KeyListener {

    private final FighterInventory playerFighters;
    private final int numberOfFighters;
    private final int numberOfColumns;
    private ItemInventoryUI inventoryUI;

    private final int leftEdge = BasicPanel.SCREENWIDTH * 6 / 100;
    private final int upperEdge = BasicPanel.SCREENWIDTH * 12 / 100;
    private final int cursor_back_button = BasicPanel.SCREENWIDTH * 75 / 100;
    private final Map<List<Integer>, Integer> lookupIndex = new HashMap<>();

    private BattleSystem battle;
    private final MenuType menuType;
    private boolean isNewRound = true;

    private boolean isItemUse = false;
    private int healAmount;

    // cursor coordinates
    private int cursor_x;
    private int cursor_y;

    public FighterInventoryUI(FighterInventory playerFighters, MenuType menuType) {
        this.playerFighters = playerFighters;
        this.numberOfFighters = playerFighters.getFighterInventory().size();
        this.numberOfColumns = (int) (numberOfFighters * 0.5) + numberOfFighters % 2;
        this.menuType = menuType;

        // Cursor start coordinates setup in the upper left corner
        cursor_x = leftEdge;
        cursor_y = upperEdge;

        // lookup map setup
        mapSetup();

        // Panel setup
        this.setBounds(0, 0, BasicPanel.SCREENWIDTH, BasicPanel.SCREENHEIGHT);
        this.setBackground(Color.lightGray);
        this.setVisible(false);
        this.setLayout(null);
    }

    public void setBattle(BattleSystem battle) {
        if (menuType.equals(MenuType.WORLD)) return;
        this.battle = battle;
    }

    public void setItemInventoryUI(ItemInventoryUI inventoryUI) {
        this.inventoryUI = inventoryUI;
    }

    public void showUI(boolean isNewRound, boolean isItemUse, int healAmount) {
        this.isNewRound = isNewRound;
        this.isItemUse = isItemUse;
        this.healAmount = healAmount;

        this.cursor_x = leftEdge;
        this.cursor_y = upperEdge;
        repaint();
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;

        for (Fighter fighter : playerFighters.getFighterInventory()) {
            // Draws the name of the fighter
            g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));
            g.drawString(fighter.getName(),
                    BasicPanel.SCREENWIDTH / 10 + x * BasicPanel.SCREENWIDTH / 2, // starts at 1/10 screenwidth
                    BasicPanel.SCREENHEIGHT * 15 / 100 + y * BasicPanel.SCREENHEIGHT / 4); // starts at 15/100 screenheight

            // Draws the hitpoints of the fighter
            g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE / 2));
            g.drawString("HP: " + fighter.getHitpoints() + " / " + fighter.getMaxHitpoints(),
                    BasicPanel.SCREENWIDTH / 10 + x * BasicPanel.SCREENWIDTH / 2, // starts at 1/10 screenwidth
                    BasicPanel.SCREENHEIGHT * 185 / 1000 + y * BasicPanel.SCREENHEIGHT / 4); // starts at 185/1000 screenheight

            // Draws the type of the fighter
            g.drawString("TYPE: " + fighter.getType(),
                    BasicPanel.SCREENWIDTH / 10 + x * BasicPanel.SCREENWIDTH / 2, // starts at 1/10 screenwidth
                    BasicPanel.SCREENHEIGHT * 22 / 100 + y * BasicPanel.SCREENHEIGHT / 4); // starts at 22/100 screenheight


            if (x != 1)
                x++;
            else {
                x = 0;
                y++;
            }
        }

        // Draws the BACK string to get out of the inventory
        if (isNewRound) {
            g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));
            g.drawString("BACK", 8 * BasicPanel.SCREENWIDTH / 10, 95 * BasicPanel.SCREENHEIGHT / 100); // draws in the lower right corner
        }
        BasicPanel.drawCursor(g, this.cursor_x, this.cursor_y);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10 -> { // If enter is pressed:
                if (cursor_x == cursor_back_button) {
                    if (isItemUse) inventoryUI.successfulItemUse(false);
                    setVisible(false);
                } else
                    chooseFighter();
            }
            case 37 -> moveCursor(Direction.LEFT);
            case 38 -> moveCursor(Direction.UP);
            case 39 -> moveCursor(Direction.RIGHT);
            case 40 -> moveCursor(Direction.DOWN);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    private void mapSetup() {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 2; x++) {
                List<Integer> cursor_cords = new ArrayList<>();
                cursor_cords.add(leftEdge + x * BasicPanel.SCREENWIDTH / 2);
                cursor_cords.add(upperEdge + y * BasicPanel.SCREENWIDTH / 4);
                lookupIndex.put(cursor_cords, x + 2 * y);
            }
    }

    /**
     * Moves the cursor across the screen.
     * The method also takes the number of rows into consideration as well
     * whether the menu was opened because a fighter was defeated or not.
     */
    private void moveCursor(Direction direction) {
        switch (direction) {
            case UP -> {
                if (cursor_y == upperEdge) return;

                if (cursor_x == cursor_back_button) { // if cursor is pointing to the back button: return the cursor to the fighter section
                    cursor_x = leftEdge;
                    cursor_y = upperEdge + (numberOfColumns - 1) * BasicPanel.SCREENWIDTH / 4;
                    break;
                }

                cursor_y -= BasicPanel.SCREENWIDTH / 4; // move the cursor 1/4 of the screenwidth
            }

            case DOWN -> {
                if (cursor_x == cursor_back_button) return;

                // if cursor is on the lowest row: let the cursor point to the back button
                if (cursor_y == upperEdge + (numberOfColumns - 1) * BasicPanel.SCREENWIDTH / 4) {
                    if (!isNewRound) return;
                    cursor_x = cursor_back_button;
                    cursor_y = upperEdge + BasicPanel.SCREENWIDTH * 3 / 4 + BasicPanel.SCREENWIDTH * 5 / 100;
                    break;
                }

                // if the cursor is right above a row that's not full: set cursor_x at the left edge
                if (cursor_y == upperEdge + (numberOfColumns - 2) * BasicPanel.SCREENWIDTH / 4)
                    cursor_x = leftEdge;

                cursor_y += BasicPanel.SCREENWIDTH / 4; // move the cursor 1/4 of the screenheight
            }

            case LEFT -> {
                // if cursor is as far left as possible or on the back button: do nothing
                if (cursor_x == leftEdge || cursor_x == cursor_back_button) return;
                cursor_x -= BasicPanel.SCREENWIDTH / 2;
            }

            case RIGHT -> {
                // if cursor is as far right as possible or on the back button: do nothing
                if (cursor_x == leftEdge + BasicPanel.SCREENWIDTH / 2 || cursor_x == cursor_back_button) return;

                // if cursor is as far down as possible in the fighter section and the numbers of fighters is uneven: do nothing
                if (cursor_y == upperEdge + (numberOfColumns - 1) * BasicPanel.SCREENWIDTH / 4 && numberOfFighters % 2 != 0)
                    return;

                cursor_x += BasicPanel.SCREENWIDTH / 2; // move the cursor across half the screen
            }

        }
        repaint();
    }

    private void chooseFighter() {
        if (!isItemUse && menuType.equals(MenuType.WORLD)) return;

        List<Integer> cursor_coords = new ArrayList<>();
        cursor_coords.add(cursor_x);
        cursor_coords.add(cursor_y);

        int fighterIndex = lookupIndex.getOrDefault(cursor_coords, -1);
        cursor_coords.clear();

        if (fighterIndex == -1) throw new IllegalStateException("Cursor can't be here!");

        if (!isItemUse) {
            if (playerFighters.getFighter(fighterIndex).isDefeated()) return;

            if (isNewRound) battle.round(BattleAction.SWITCH, fighterIndex);
            else battle.switchAfterDefeated(fighterIndex);
            setVisible(false);
        } else {
            itemUse(fighterIndex);
            if (menuType.equals(MenuType.BATTLE)) battle.round(BattleAction.ITEMS);
            setVisible(false);
        }
    }

    private void itemUse(int fighterIndex) {
        if (healAmount >= 1000000) {
            if (playerFighters.getFighter(fighterIndex).isDefeated()) {
                playerFighters.getFighter(fighterIndex).heal(healAmount);
                inventoryUI.successfulItemUse(true);
            } else
                inventoryUI.successfulItemUse(false);
        } else {
            if (playerFighters.getFighter(fighterIndex).getHitpoints() != playerFighters.getFighter(fighterIndex).getMaxHitpoints()) {
                playerFighters.getFighter(fighterIndex).heal(healAmount);
                inventoryUI.successfulItemUse(true);
            } else
                inventoryUI.successfulItemUse(false);
        }
    }

    /**
     * Directions that the cursor can move
     */
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

}

package Frames.BattleUI;

import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import Entity.FighterInventory;
import Frames.BasicPanel;

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
    private final int leftEdge = 6 * BasicPanel.SCREENWIDTH / 100;
    private final int upperEdge = 12 * BasicPanel.SCREENWIDTH / 100;
    private final int cursor_back_button = 75 * BasicPanel.SCREENWIDTH / 100;
    private BattleSystem battle;
    private boolean isNewRound = true;
    private Map<List<Integer>, Integer> lookupIndex = new HashMap<>();

    // cursor coordinates
    private int cursor_x;
    private int cursor_y;

    public FighterInventoryUI(FighterInventory playerFighters) {
        this.playerFighters = playerFighters;

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

    private void mapSetup() {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 2; x++) {
                List<Integer> cursor_cords = new ArrayList<>();
                cursor_cords.add(leftEdge + x * BasicPanel.SCREENWIDTH / 2);
                cursor_cords.add(upperEdge + y * BasicPanel.SCREENWIDTH / 4);
                lookupIndex.put(cursor_cords, x + 2 * y);
            }
    }

    private void switchFighter() {
        List<Integer> cursor_coords = new ArrayList<>();
        cursor_coords.add(cursor_x);
        cursor_coords.add(cursor_y);

        int fighterIndex = lookupIndex.getOrDefault(cursor_coords, -1);
        cursor_coords.clear();

        if (fighterIndex == -1) throw new IllegalStateException("Cursor can't be here!");
        if (isNewRound) battle.round("switch", fighterIndex);
        setVisible(false);
    }

    public void showUI(boolean isNewRound) {
        repaint();
        setVisible(true);
    }

    public void setBattle(BattleSystem battle) {
        this.battle = battle;
    }

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
                    BasicPanel.SCREENHEIGHT * 185 / 1000 + y * BasicPanel.SCREENHEIGHT / 4); // startws at 185/1000 screenheight

            if (x != 1)
                x++;
            else {
                x = 0;
                y++;
            }
        }

        // Draws the BACK string to get out of the inventory
        g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));
        g.drawString("BACK", 8 * BasicPanel.SCREENWIDTH / 10, 95 * BasicPanel.SCREENHEIGHT / 100); // draws in the lower right corner

        BasicPanel.drawCursor(g, this.cursor_x, this.cursor_y);
    }

    private void moveCursor(Direction direction) {
        switch (direction) {
            case UP -> {
                if (cursor_y == upperEdge) return;

                if (cursor_x == cursor_back_button) { // if cursor is pointing to the back button: return the cursor to the fighter
                    cursor_x = leftEdge + BasicPanel.SCREENWIDTH / 2;
                    cursor_y -= 5 * BasicPanel.SCREENWIDTH / 100;
                }
                cursor_y -= BasicPanel.SCREENWIDTH / 4; // move the cursor 1/4 of the screenwidth
            }
            case DOWN -> {
                if (cursor_x == cursor_back_button) return;

                if (cursor_y == upperEdge + BasicPanel.SCREENWIDTH / 2) { // if cursor is on the third row: let it point to the back button
                    cursor_x = cursor_back_button;
                    cursor_y += 5 * BasicPanel.SCREENWIDTH / 100;
                }

                cursor_y += BasicPanel.SCREENWIDTH / 4; // move the cursor 1/4 of the screenwidth
            }
            case LEFT -> {
                if (cursor_x == leftEdge || cursor_x == cursor_back_button) return;
                cursor_x -= BasicPanel.SCREENWIDTH / 2;
            }
            case RIGHT -> {
                if (cursor_x == leftEdge + BasicPanel.SCREENWIDTH / 2 || cursor_x == cursor_back_button) return;
                cursor_x += BasicPanel.SCREENWIDTH / 2;
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10 -> {
                if (cursor_x == cursor_back_button)
                    setVisible(false);
                else
                    switchFighter();
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

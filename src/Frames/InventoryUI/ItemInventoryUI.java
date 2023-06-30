package Frames.InventoryUI;

import Entity.ItemInventory;
import Entity.Items.DamageItem;
import Entity.Items.HealItem;
import Entity.Items.Item;
import Entity.Items.ItemType;
import Frames.BasicPanel;
import Frames.TextBox.MenuType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ItemInventoryUI extends JPanel implements KeyListener {

    private final MenuType menuType;
    private final ItemInventory inventory;
    private final FighterInventoryUI fighterInventoryUI;
    private final List<Item> lookup = new ArrayList<>();

    private final int leftEdge = BasicPanel.SCREENWIDTH * 6 / 100;
    private final int rightEdge = BasicPanel.SCREENWIDTH * 6 / 100 + BasicPanel.SCREENWIDTH * 2 / 3;
    private final int upperEdge = BasicPanel.SCREENHEIGHT * 12 / 100;
    private final int cursor_back_button = BasicPanel.SCREENWIDTH * 75 / 100;

    private int cursor_pressed = leftEdge;
    private int numberOfItems = 0;
    private int currentRow = 0;
    private int rowEndCoords;
    private int tempIndex = -1;

    // cursor coordinates
    private int cursor_x;
    private int cursor_y;

    public ItemInventoryUI(FighterInventoryUI fighterInventoryUI, ItemInventory inventory, MenuType menuType) {
        this.fighterInventoryUI = fighterInventoryUI;
        this.menuType = menuType;
        this.inventory = inventory;

        // Cursor setup
        cursor_x = leftEdge;
        cursor_y = upperEdge;

        // Panel setup
        this.setBounds(0, 0, BasicPanel.SCREENWIDTH, BasicPanel.SCREENHEIGHT);
        this.setBackground(Color.lightGray);
        this.setVisible(false);
        this.setLayout(null);
    }

    public void showUI() {
        cursor_x = leftEdge;
        cursor_y = upperEdge;
        cursor_pressed = leftEdge;
        currentRow = 0;
        redoList();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BasicPanel.drawCursor(g, cursor_x, cursor_y);

        g.setFont(new Font("Arial", Font.BOLD, BasicPanel.FONT_SIZE));
        g.drawString("Healing", BasicPanel.SCREENWIDTH / 10, BasicPanel.SCREENHEIGHT * 15 / 100);
        g.drawString("Damage", BasicPanel.SCREENWIDTH / 10 + BasicPanel.SCREENWIDTH * 9 / 30, BasicPanel.SCREENHEIGHT * 15 / 100);
        g.drawString("Catch", BasicPanel.SCREENWIDTH / 10 + BasicPanel.SCREENWIDTH * 2 / 3, BasicPanel.SCREENHEIGHT * 15 / 100);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(5));
        g2D.drawLine(cursor_pressed, BasicPanel.SCREENHEIGHT * 18 / 100, cursor_pressed + BasicPanel.SCREENWIDTH / 4, BasicPanel.SCREENHEIGHT * 18 / 100);

        int y = 0;
        numberOfItems = 0;

        if (cursor_pressed == leftEdge)
            for (Item healItem : inventory.getInventory())
                if (healItem.getItemType() == ItemType.HEAL) {
                    drawItems(g, healItem, y);
                    numberOfItems++;
                    y++;
                }

        if (cursor_pressed == rightEdge)
            for (Item catchItem : inventory.getInventory())
                if (catchItem.getItemType() == ItemType.CATCH) {
                    drawItems(g, catchItem, y);
                    y++;
                    numberOfItems++;
                }

        if (cursor_pressed != rightEdge && cursor_pressed != leftEdge)
            for (Item damageItem : inventory.getInventory())
                if (damageItem.getItemType() == ItemType.DAMAGE) {
                    drawItems(g, damageItem, y);
                    y++;
                    numberOfItems++;
                }

        g.drawString("BACK", 8 * BasicPanel.SCREENWIDTH / 10, 95 * BasicPanel.SCREENHEIGHT / 100);
    }

    private void drawItems(Graphics g, Item item, int y) {
        g.drawString(item.getName(), BasicPanel.SCREENWIDTH / 10, BasicPanel.SCREENHEIGHT / 4 + y * BasicPanel.SCREENHEIGHT / 16);
        g.drawString("" + item.getAmount(), BasicPanel.SCREENWIDTH * 8 / 10, BasicPanel.SCREENHEIGHT / 4 + y * BasicPanel.SCREENHEIGHT / 16);
    }

    private void redoList() {
        lookup.clear();

        if (cursor_pressed == leftEdge)
            for (Item healItem : inventory.getInventory())
                if (healItem.getItemType() == ItemType.HEAL)
                    lookup.add(healItem);

        if (cursor_pressed == rightEdge)
            for (Item catchItem : inventory.getInventory())
                if (catchItem.getItemType() == ItemType.CATCH)
                    lookup.add(catchItem);

        if (cursor_pressed != rightEdge && cursor_pressed != leftEdge)
            for (Item damageItem : inventory.getInventory())
                if (damageItem.getItemType() == ItemType.DAMAGE)
                    lookup.add(damageItem);

        repaint();
    }

    public void successfulItemUse(boolean isSuccessful) {
        if (isSuccessful) inventory.removeFromInventory(lookup.get(tempIndex));
        tempIndex = -1;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10 -> { // If enter is pressed:
                if (cursor_y == upperEdge) {
                    cursor_pressed = cursor_x;
                    redoList();
                } else if (cursor_x == cursor_back_button)
                    setVisible(false);
                else {
                    chooseItem();
                    redoList();
                }
            }
            case 37 -> moveCursor(FighterInventoryUI.Direction.LEFT);
            case 38 -> moveCursor(FighterInventoryUI.Direction.UP);
            case 39 -> moveCursor(FighterInventoryUI.Direction.RIGHT);
            case 40 -> moveCursor(FighterInventoryUI.Direction.DOWN);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    private void moveCursor(FighterInventoryUI.Direction direction) {
        switch (direction) {
            case RIGHT -> {
                if (cursor_y != upperEdge) return;
                if (cursor_x == leftEdge) cursor_x += BasicPanel.SCREENWIDTH * 9 / 30;
                else cursor_x = rightEdge;

            }
            case LEFT -> {
                if (cursor_y != upperEdge) return;
                if (cursor_x == rightEdge) cursor_x -= BasicPanel.SCREENWIDTH * 11 / 30;
                else cursor_x = leftEdge;
            }
            case UP -> {
                if (cursor_y == upperEdge) return;
                if (cursor_x == cursor_back_button) {
                    cursor_x = leftEdge;
                    cursor_y = rowEndCoords;
                } else {
                    cursor_y = (cursor_y == upperEdge + BasicPanel.SCREENHEIGHT / 10) ? upperEdge : cursor_y - BasicPanel.SCREENHEIGHT / 16;
                    currentRow--;
                }
            }
            case DOWN -> {
                if (cursor_x == cursor_back_button) return;
                if (currentRow == numberOfItems) {
                    rowEndCoords = cursor_y;
                    cursor_x = cursor_back_button;
                    cursor_y = upperEdge + BasicPanel.SCREENHEIGHT * 3 / 4 + BasicPanel.SCREENHEIGHT * 5 / 100;
                } else {
                    cursor_x = leftEdge;
                    cursor_y = (cursor_y == upperEdge) ? upperEdge + BasicPanel.SCREENHEIGHT / 10 : cursor_y + BasicPanel.SCREENHEIGHT / 16;
                    currentRow++;
                }
            }
        }
        repaint();
    }

    private void chooseItem() {
        if (menuType.equals(MenuType.WORLD) && cursor_pressed != leftEdge) return;
        tempIndex = (cursor_y - upperEdge - BasicPanel.SCREENWIDTH / 10) / (BasicPanel.SCREENHEIGHT / 16);

        if (cursor_pressed == leftEdge) {
            int heal = ((HealItem) lookup.get(tempIndex)).getHealValue();
            fighterInventoryUI.showUI(true, true, heal);  //false to block the back button for now
        }

        if (cursor_pressed != leftEdge && cursor_pressed != rightEdge) {
            int damage = ((DamageItem) lookup.get(tempIndex)).getDamageValue();
            // here would be some connection to the battle to start a new round and
            // the round would get a new enum value for item_damage
        }

        cursor_y = upperEdge;
        currentRow = 0;

        if (menuType.equals(MenuType.BATTLE))
            setVisible(false);
    }

}

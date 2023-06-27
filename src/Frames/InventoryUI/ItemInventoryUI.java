package Frames.InventoryUI;

import Entity.ItemInventory;
import Entity.Items.Heal.*;
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

    // cursor coordinates
    private int cursor_x;
    private int cursor_y;

    public ItemInventoryUI(FighterInventoryUI fighterInventoryUI, ItemInventory inventory, MenuType menuType) {
        this.fighterInventoryUI = fighterInventoryUI;
        this.menuType = menuType;
        this.inventory = inventory;

        inventory.addToInventory(new Potion());
        inventory.addToInventory(new SuperPotion());
        inventory.addToInventory(new HyperPotion());
        inventory.addToInventory(new Revive());
        inventory.addToInventory(new TopRevive());

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
        repaint();
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
        boolean redoList = lookup.isEmpty();

        if (cursor_pressed == leftEdge)
            for (Item healItem : inventory.getInventory())
                if (healItem.getItemType() == ItemType.HEAL) {
                    if (redoList) lookup.add(healItem);
                    drawItems(g, healItem, y);
                    numberOfItems++;
                    y++;
                }

        if (cursor_pressed == rightEdge)
            for (Item catchItem : inventory.getInventory())
                if (catchItem.getItemType() == ItemType.CATCH) {
                    if (redoList) lookup.add(catchItem);
                    drawItems(g, catchItem, y);
                    y++;
                    numberOfItems++;
                }

        if (cursor_pressed != rightEdge && cursor_pressed != leftEdge)
            for (Item damageItem : inventory.getInventory())
                if (damageItem.getItemType() == ItemType.DAMAGE) {
                    if (redoList) lookup.add(damageItem);
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

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10 -> { // If enter is pressed:
                if (cursor_y == upperEdge) {
                    cursor_pressed = cursor_x;
                    lookup.clear();
                    repaint();
                } else if (cursor_x == cursor_back_button)
                    setVisible(false);
                else
                    chooseItem();
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
        int index = (cursor_y - upperEdge - BasicPanel.SCREENWIDTH / 10) / (BasicPanel.SCREENHEIGHT / 16);

        if (cursor_pressed == leftEdge) {
            int heal = ((HealItem) lookup.get(index)).getHealValue();
            fighterInventoryUI.showUI(false, true, heal);  //false to block the back button for now
//TODO: stop concurrentModificationException and if possible, wait with the removal until the use is confirmed
            inventory.removeFromInventory(lookup.get(index));
        }
        cursor_y = upperEdge;
        if (menuType.equals(MenuType.BATTLE))
            setVisible(false);
    }

}

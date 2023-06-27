package Entity;

import Entity.Items.Heal.*;
import Entity.Items.Item;

import java.util.Objects;
import java.util.Vector;

public class ItemInventory {
    private Vector<Item> inventory = new Vector<>();

    public ItemInventory() {
        //TODO read inventory out of JSON
        addToInventory(new Potion());
        addToInventory(new SuperPotion());
        addToInventory(new HyperPotion(), 6);
        addToInventory(new Revive());
        addToInventory(new TopRevive());
    }

    public void addToInventory(Item item_given) {
        boolean done = false;
        for (Item item : this.inventory) {
            if (Objects.equals(item.getName(), item_given.getName())) {
                item.addItem();
                done = true;
                break;
            }
        }
        if (!done) {
            this.inventory.add(item_given);
        }
    }

    public void addToInventory(Item item_given, int amount) {
        boolean done = false;
        for (Item item : this.inventory) {
            if (Objects.equals(item.getName(), item_given.getName())) {
                item.addItem(amount);
                done = true;
                break;
            }
        }
        if (!done) {
            item_given.addItem(amount - 1);
            this.inventory.add(item_given);
        }
    }

    public void removeFromInventory(Item item_given) {
        if (!inventory.contains(item_given)) throw new IllegalArgumentException("This Item is not in the inventory");
        inventory.get(inventory.indexOf(item_given)).reduceItem();
        if (inventory.get(inventory.indexOf(item_given)).getAmount() < 1)
            inventory.remove(item_given);
    }

    public Vector<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(Vector<Item> inventory) {
        this.inventory = inventory;
    }

}

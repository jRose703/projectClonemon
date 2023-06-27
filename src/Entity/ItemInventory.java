package Entity;

import Entity.Items.Item;

import java.util.Objects;
import java.util.Vector;

public class ItemInventory {
    private Vector<Item> inventory = new Vector<>();

    public ItemInventory() {
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

    public void removeFromInventory(Item item_given) {
        boolean done = false;
        for (Item item : this.inventory) {
            if (Objects.equals(item.getName(), item_given.getName())) {
                if (item.getAmount() > 1) {
                    item.reduceItem();
                    done = true;
                } else if (item.getAmount() == 1) {
                    this.inventory.remove(item);
                    done = true;
                }
            }
        }
        if (!done) {
            throw new IllegalArgumentException("Item not in Inventory");
        }
    }

    public Vector<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(Vector<Item> inventory) {
        this.inventory = inventory;
    }

}

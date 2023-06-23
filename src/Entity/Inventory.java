package Entity;

import java.util.Objects;
import java.util.Vector;

public class Inventory {
    private Vector<Item> inventory = new Vector<>();

    public Inventory() {}

    public void setInventory(Vector<Item> inventory) {
        this.inventory = inventory;
    }

    public Vector<Item> getInventory() {
        return this.inventory;
    }
    public void addToInventory(Item item_given){
        boolean done = false;
        for (Item item : this.inventory) {
            if (Objects.equals(item.getName(), item_given.getName())) {
                item.add_Item();
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
                    item.reduceAmount();
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
}

package Entity;

import java.util.Objects;
import java.util.Vector;

public class Inventory {
    private Vector<Item> Inventory = new Vector<>();
    public Inventory(){}
    public Vector<Item> getInventory(){
        return this.Inventory;
    }
    public void addToInventory(Item item_given){
        boolean done = false;
        for (Item item : this.Inventory) {
            if (Objects.equals(item.getName(), item_given.getName())) {
                item.add_Item();
                done = true;
                break;
            }
        }
        if (!done){
            this.Inventory.add(item_given);
        }
    }
    public void removeFromInventory(Item item_given){
        boolean done = false;
        for (Item item: this.Inventory){
            if (Objects.equals(item.getName(), item_given.getName())){
                if (item.getAmount() > 1){
                    item.reduceAmount();
                    done = true;
                }
                else if (item.getAmount() == 1){
                    this.Inventory.remove(item);
                    done = true;
                }
            }
        }
        if (!done){
            throw new IllegalArgumentException("Item not in Inventory");
        }
    }
}

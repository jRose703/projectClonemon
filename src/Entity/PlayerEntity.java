package Entity;

import java.util.Objects;
import java.util.Vector;

public class PlayerEntity extends Entity {
    public Vector<Item> Inventory = new Vector<>();
    public Vector<Item> getInventory(){
        return this.Inventory;
    }
    public void addToInventory(Item item_given){
        boolean done = false;
        for (Item item : this.Inventory) {
            if (Objects.equals(item.getType(), item_given.getType())) {
                item.add_Item();
                done = true;
                break;
            }
        }
        if (!done){
            this.Inventory.add(item_given);
        }
    }

    @Override
    public void interact() {
        super.interact();
    }
}
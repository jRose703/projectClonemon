package Entity;

public class PlayerEntity extends Entity {
    public Item[] Inventory = new Item[50];
    public Item[] getInventory(){
        return this.Inventory;
    }
    public void addToInventory(Item item_given){
        //TODO
    }

    @Override
    public void interact() {
        super.interact();
    }
}
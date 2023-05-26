package Entity;

public class PlayerEntity extends Entity {
    public Inventory Inventory = new Inventory();
    public ClonemonsInventory clonemonsInventory = new ClonemonsInventory();
    @Override
    public void interact() {
        super.interact();
    }
}
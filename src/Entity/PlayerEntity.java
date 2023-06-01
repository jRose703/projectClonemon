package Entity;

public class PlayerEntity extends Entity {
    public Inventory Inventory = new Inventory();
    private FighterInventory fighterInventory = new FighterInventory();

    public FighterInventory getFighterInventory() {
        return this.fighterInventory;
    }
}
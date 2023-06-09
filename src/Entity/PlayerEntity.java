package Entity;

public class PlayerEntity extends Entity {
    public PlayerEntity(){
        this.currentWorld = "world";
        this.money = 0;
        setCoordinates(0,0);
    }
    private int money;
    private String currentWorld;
    private Inventory inventory = new Inventory();
    private FighterInventory fighterInventory = new FighterInventory();

    public FighterInventory getFighterInventory() {
        return this.fighterInventory;
    }
}
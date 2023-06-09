package Entity;

import Worlds.Coordinates;

public class PlayerEntity extends Entity {
    public PlayerEntity(){
        setCoordinates(0,0);
        this.currentWorld = "world";
        this.money = 0;
        this.inventory = new Inventory();
        this.clonemonsInventory = new ClonemonsInventory();
        clonemonsInventory.addToClonemonsInventory(new Clonemon("Snorlax",1,"normal",1));
        clonemonsInventory.addToClonemonsInventory(new Clonemon("Dialga",2,"dragon",100));
    }
    private String currentWorld;
    private int money;
    private Inventory inventory;
    private ClonemonsInventory clonemonsInventory;


}
package Entity;

import BattleSystem.Fighter;

public class PlayerEntity extends Entity {
    public PlayerEntity(){
        this.currentWorld = "world";
        this.money = 0;
        setCoordinates(0,0);
    }
    private int money;
    private String currentWorld;
    private Inventory Inventory = new Inventory();
    private FighterInventory playerFighters = new FighterInventory();

    public FighterInventory getPlayerFighters() {
        return this.playerFighters;
    }

    public void addToFighterInventory(Fighter fighter){
        playerFighters.addToFighterInventory(fighter);
    }
}
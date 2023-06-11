package Entity;

import BattleSystem.Fighter;

public class PlayerEntity extends Entity {
    public Inventory Inventory = new Inventory();
    private FighterInventory playerFighters = new FighterInventory();

    public FighterInventory getPlayerFighters() {
        return this.playerFighters;
    }

    public void addToFighterInventory(Fighter fighter){
        playerFighters.addToFighterInventory(fighter);
    }
}
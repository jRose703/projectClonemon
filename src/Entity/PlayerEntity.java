package Entity;

import BattleSystem.Fighter;
import Worlds.Coordinates;

public class PlayerEntity extends Entity {
    public PlayerEntity(){
    }
    private FighterInventory playerFighters;
    private String currentWorld;
    private int money;
    private Inventory inventory;

    public FighterInventory getPlayerFighters() {
        return this.playerFighters;
    }

    public void addToFighterInventory(Fighter fighter){
        playerFighters.addToFighterInventory(fighter);
    }

    public String getCurrentWorld() {
        return currentWorld;
    }

    @Override
    public Coordinates getCoordinates() {
        return super.getCoordinates();
    }

    @Override
    public int getFacing() {
        return super.getFacing();
    }

    public int getMoney() {
        return money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setCurrentWorld(String currentWorld) {
        this.currentWorld = currentWorld;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPlayerFighters(FighterInventory playerFighters) {
        this.playerFighters = playerFighters;
    }
}
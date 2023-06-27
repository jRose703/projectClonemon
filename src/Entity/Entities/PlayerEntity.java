package Entity.Entities;

import BattleSystem.Fighter;
import Entity.FighterInventory;
import Entity.Inventory;
import Entity.Items.Item;
import Worlds.Coordinates;

public class PlayerEntity extends Entity {

    private FighterInventory playerFighters;
    private Inventory inventory;
    private String currentWorld;
    private int money;

    public PlayerEntity() {
    }

    public void addToInventory(Item item) {
        inventory.addToInventory(item);
    }

    public void addToFighterInventory(Fighter fighter) {
        playerFighters.addToFighterInventory(fighter);
    }

    public FighterInventory getPlayerFighters() {
        return this.playerFighters;
    }

    public String getCurrentWorld() {
        return currentWorld;
    }

    public int getMoney() {
        return money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public Coordinates getCoordinates() {
        return super.getCoordinates();
    }

    @Override
    public int getFacing() {
        return super.getFacing();
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
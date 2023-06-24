package Entity;

import BattleSystem.Fighter;

import java.util.Objects;
import java.util.Vector;

public class FighterInventory {
    private Vector<Fighter> fighterInventory = new Vector<>();

    public FighterInventory() {
    }

    public FighterInventory(Fighter[] fighters) {
        for (Fighter fighter : fighters)
            addToFighterInventory(fighter);
    }

    public void addToFighterInventory(Fighter fighter) {
        if (fighterInventory.size() < 6) {
            fighterInventory.add(fighter);
        } else {
            throw new IllegalArgumentException("FighterInventory full");
        }
    }

    public void removeFromFighterInventory(Fighter fighter) {
        boolean done = false;
        for (Fighter existingFighter : this.fighterInventory) {
            if (Objects.equals(existingFighter.getID(), fighter.getID())) {
                this.fighterInventory.remove(fighter);
                done = true;
                break;
            }
        }
        if (!done) {
            throw new IllegalArgumentException("Fighter not in FighterInventory");
        }
    }

    public Fighter getFighter(int index) {
        return fighterInventory.get(index);
    }

    public int getFighterIndex(Fighter fighter) {
        if (fighter == null) throw new IllegalArgumentException();
        return fighterInventory.indexOf(fighter);
    }

    public boolean hasNext() {
        for (Fighter fighter : fighterInventory) {
            if (!fighter.isDefeated()) return true;
        }
        return false;
    }

    public Vector<Fighter> getFighterInventory() {
        return this.fighterInventory;
    }

    public int size(){
        return fighterInventory.size();
    }

}

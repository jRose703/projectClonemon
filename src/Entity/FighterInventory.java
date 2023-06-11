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
            throw new IllegalArgumentException("ClonemonsInventory full");
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

    public boolean hasNext(int index) {
        return fighterInventory.size() > index;
    }

    public Vector<Fighter> getFighterInventory() {
        return this.fighterInventory;
    }

}

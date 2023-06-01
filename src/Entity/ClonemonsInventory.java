package Entity;

import java.util.Objects;
import java.util.Vector;

public class ClonemonsInventory {
    public Vector<Clonemon> ClonemonsInventory = new Vector<>();
    public ClonemonsInventory(){}
    public void addToClonemonsInventory(Clonemon clonemon_given){
        if (ClonemonsInventory.size() <6){
            ClonemonsInventory.add(clonemon_given);
        }
        else{
            throw new IllegalArgumentException("ClonemonsInventory full");
        }
    }
    public Vector<Clonemon> getClonemonsInventory(){
        return this.ClonemonsInventory;
    }
    public void removeFromClonemonsInventory(Clonemon clonemon_given){
        boolean done = false;
        for (Clonemon clonemon : this.ClonemonsInventory) {
            if (Objects.equals(clonemon.getID(), clonemon_given.getID())) {
                this.ClonemonsInventory.remove(clonemon_given);
                done = true;
                break;
            }
        }
        if (!done){
            throw new IllegalArgumentException("Clonemon not in ClonemonsInventory");
        }
    }
}

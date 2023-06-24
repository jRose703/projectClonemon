package Entity.Items;
import Entity.Item;
public abstract class ThrowableItem extends Item {
    private boolean offensive = true;
    public ThrowableItem(String name){
        super(name);
    }
}

package Entity.Items;
import Entity.Item;

public abstract class ThrowableItem extends Item {
    private final boolean offensive = true;

    public ThrowableItem(String name) {
        super(name);
    }
}

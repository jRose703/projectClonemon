package Entity.Items;
import Entity.Item;

public abstract class SupportItem extends Item {
    private final boolean offensive = false;

    public SupportItem(String name) {
        super(name);
    }
}

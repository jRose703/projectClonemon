package Entity.Items;

public abstract class ThrowableItem extends Item {
    private final boolean offensive = true;

    public ThrowableItem(String name, ItemType itemType) {
        super(name, itemType);
    }
}

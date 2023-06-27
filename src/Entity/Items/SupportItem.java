package Entity.Items;

public abstract class SupportItem extends Item {
    private final boolean offensive = false;

    public SupportItem(String name, ItemType itemType) {
        super(name, itemType);
    }
}

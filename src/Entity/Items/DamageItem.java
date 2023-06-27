package Entity.Items;

public abstract class DamageItem extends Item {
    private final boolean offensive = true;


    public DamageItem(String name, ItemType itemType) {
        super(name, itemType);
    }

    public abstract int getDamageValue();
}

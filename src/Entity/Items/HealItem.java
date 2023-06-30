package Entity.Items;

public abstract class HealItem extends Item {
    private final boolean offensive = false;


    public HealItem(String name, ItemType itemType) {
        super(name, itemType);
    }

    public abstract int getHealValue();
}

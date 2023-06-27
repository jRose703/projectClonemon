package Entity.Items.Damage;

import Entity.Items.DamageItem;
import Entity.Items.ItemType;

public class PoisonPotion extends DamageItem {
    private final ItemType type = ItemType.DAMAGE;
    private final int VALUE = 5;

    public PoisonPotion(String name) {
        super(name, ItemType.DAMAGE);
    }

    @Override
    public int getDamageValue() {
        return VALUE;
    }
}

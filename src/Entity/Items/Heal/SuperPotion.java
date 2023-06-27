package Entity.Items.Heal;

import Entity.Items.HealItem;
import Entity.Items.ItemType;

public class SuperPotion extends HealItem {
    private final ItemType type = ItemType.HEAL;
    private final int VALUE = 50;

    public SuperPotion(String name) {
        super(name, ItemType.HEAL);
    }

    public int getHealValue() {
        return VALUE;
    }
}

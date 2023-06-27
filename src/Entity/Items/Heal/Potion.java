package Entity.Items.Heal;

import Entity.Items.HealItem;
import Entity.Items.ItemType;

public class Potion extends HealItem {
    private final ItemType type = ItemType.HEAL;
    private final int VALUE = 20;

    public Potion(String name) {
        super(name, ItemType.HEAL);
    }

    public int getHealValue() {
        return VALUE;
    }
}

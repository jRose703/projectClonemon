package Entity.Items.Heal;

import Entity.Items.ItemType;
import Entity.Items.SupportItem;

public class Potion extends SupportItem {
    private final ItemType type = ItemType.HEAL;
    private final int VALUE = 20;

    public Potion(String name) {
        super(name, ItemType.HEAL);
    }
}

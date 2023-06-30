package Entity.Items.Heal;

import Entity.Items.HealItem;
import Entity.Items.ItemType;

public class HyperPotion extends HealItem {

    public HyperPotion() {
        super("Hyper Potion", ItemType.HEAL);
    }

    public int getHealValue() {
        return 200;
    }
}

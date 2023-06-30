package Entity.Items.Heal;

import Entity.Items.HealItem;
import Entity.Items.ItemType;

public class Potion extends HealItem {

    public Potion() {
        super("Potion", ItemType.HEAL);
    }

    public int getHealValue() {
        return 20;
    }
}

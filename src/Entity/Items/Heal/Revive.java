package Entity.Items.Heal;

import Entity.Items.HealItem;
import Entity.Items.ItemType;

public class Revive extends HealItem {

    public Revive() {
        super("Revive", ItemType.HEAL);
    }

    public int getHealValue() {
        return 1000000;
    }
}

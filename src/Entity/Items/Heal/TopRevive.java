package Entity.Items.Heal;

import Entity.Items.HealItem;
import Entity.Items.ItemType;

public class TopRevive extends HealItem {

    public TopRevive() {
        super("Top Revive", ItemType.HEAL);
    }

    public int getHealValue() {
        return 1000000000;
    }
}

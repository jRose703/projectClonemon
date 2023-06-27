package Entity.Items.Heal;

import Entity.Items.HealItem;
import Entity.Items.ItemType;

public class SuperPotion extends HealItem {

    public SuperPotion() {
        super("Super Potion", ItemType.HEAL);
    }

    public int getHealValue() {
        return 50;
    }
}

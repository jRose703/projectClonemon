package Entity.Items.Damage;

import Entity.Items.DamageItem;
import Entity.Items.ItemType;

public class PoisonPotion extends DamageItem {

    public PoisonPotion() {
        super("Poison Potion", ItemType.DAMAGE);
    }

    @Override
    public int getDamageValue() {
        return 5;
    }
}

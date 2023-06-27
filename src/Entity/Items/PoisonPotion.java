package Entity.Items;

public class PoisonPotion extends ThrowableItem {
    private final ItemType type = ItemType.DAMAGE;
    private final int VALUE = 5;

    public PoisonPotion(String name) {
        super(name);
    }

}

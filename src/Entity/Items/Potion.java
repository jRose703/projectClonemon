package Entity.Items;
public class Potion extends SupportItem {
    private final ItemType type = ItemType.HEAL;
    private final int VALUE = 20;

    public Potion(String name) {
        super(name);
    }
}

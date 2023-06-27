package Entity.Items.Catch;

import Entity.Items.ItemType;
import Entity.Items.ThrowableItem;

public class Pokedodekaeder extends ThrowableItem {
    private final ItemType type = ItemType.CATCH;
    private final int VALUE = 1;

    public Pokedodekaeder(String name) {
        super(name, ItemType.CATCH);
    }
}

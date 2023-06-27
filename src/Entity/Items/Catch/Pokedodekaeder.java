package Entity.Items.Catch;

import Entity.Items.ItemType;
import Entity.Items.ThrowableItem;

public class Pokedodekaeder extends ThrowableItem {

    public Pokedodekaeder() {
        super("Pokedodekaeder", ItemType.CATCH);
    }


    @Override
    public int getThrowableValue() {
        return 1;
    }
}

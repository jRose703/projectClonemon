package Entity.Items;

public abstract class Item {
    private final String name;
    private final ItemType itemType;
    private int amount;

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
        this.amount = 1;
    }

    public void addItem() {
        this.amount++;
    }

    public void addItem(int amount) {
        this.amount += amount;
    }

    public void reduceItem() {
        this.amount = Math.max(this.amount - 1, 0);
    }

    public void reduceItem(int amount) {
        this.amount = Math.max(this.amount - amount, 0);
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public ItemType getItemType() {
        return itemType;
    }
}

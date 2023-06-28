package Entity.Items;

public abstract class Item {
    private String name;
    private int amount;

    public Item(String name) {
        this.name = name;
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

    public void setName(String name){this.name = name;}

    public int getAmount() {
        return amount;
    }

}

package Entity;

public abstract class Item {
    private String name;
    private int amount;
    private boolean battle;

    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
    public Item(String name, boolean offensive){
        this.name = name;
        this.amount=1;
        this.battle = offensive;
    }
    public void add_Item(){
        this.amount ++;
    }
    public void add_Item(int amount){
        this.amount += amount;
    }
    public void reduceAmount(){
        if (this.amount>1){
            amount--;
        }
    }

    public boolean getBattle(){
        return this.battle;
    }

}

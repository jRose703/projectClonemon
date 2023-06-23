package Entity;

public abstract class Item {
    private String name;
    private int amount;
    public String getName() {
        return name;
    }
    public int getAmount() {
        return amount;
    }
    public Item(String name){
        this.name = name;
        this.amount=1;
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
}

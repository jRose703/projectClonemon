package Entity;

public class Item {
    private String name = "";
    private int amount = 0;
    private boolean battle = false;

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

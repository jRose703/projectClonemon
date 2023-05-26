package Entity;

public class Item {
    public String Type = "";
    public int amount = 0;
    public boolean battle = false;

    public String getType() {
        return Type;
    }

    public int getAmount() {
        return amount;
    }

    public Item(String Type, boolean offensive){
        this.Type = Type;
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

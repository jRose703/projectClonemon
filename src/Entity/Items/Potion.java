package Entity.Items;
public class Potion extends SupportItem {
    private String Type = "heal";
    private int value = 20;
    public Potion(String name){
        super(name);
    }
}

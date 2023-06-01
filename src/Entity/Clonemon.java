package Entity;

public class Clonemon {
    public String name;
    public int ID;
    public int hp = 1;
    public int max_hp = 1;
    public int xp = 0;
    public String Type;
    public Item item;
    public int lvl;
    public Clonemon(String name, int ID, String Type, int lvl){
        this.name = name;
        this.ID = ID;
        this.Type = Type;
        this.lvl = lvl;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void heal(){
        this.hp = max_hp;
    }
    public int getID() {
        return ID;
    }
    public int getLvl() {
        return lvl;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return Type;
    }
    public void increaseLvl(){
        if (this.lvl<100){
            this.lvl++;
            //TODO: increase hp, max_hp, reset xp (or not ( if we check for certain xp borders))
        }
        else{
            throw new IllegalArgumentException("Clonemon already has maximum lvl");
        }
    }
}

package Entity;

public class Clonemon {
    public String name;
    public int ID;
    public String Type;
    public int lvl;
    public Clonemon(String name, int ID, String Type, int lvl){
        this.name = name;
        this.ID = ID;
        this.Type = Type;
        this.lvl = lvl;
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
        }
        else{
            throw new IllegalArgumentException("Clonemon already has maximum lvl");
        }
    }
}

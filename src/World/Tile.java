package World;

public abstract class Tile {
    public boolean accessible;
    public int texture_id;
    public Tile(){
        accessible = false;
        texture_id = -1;

    }

}

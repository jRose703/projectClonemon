package World.Tiles;

public abstract class Tile {
    boolean accessible;
    int texture_id;

    public Tile(){
        accessible = false;
        int texture_id = -1;

    }
    public boolean getAccessible(){
        return accessible;
    }
    public int getTexture_id(){
        return texture_id;
    }
    public void setAccessible(){
        accessible = true;
    }

}

package Worlds.Tiles;

public abstract class Tile {
    boolean accessible;
    int texture_id;

    public boolean getAccessible() {
        return accessible;
    }

    public int getTexture_id() {
        return texture_id;
    }

    public void setAccessible() {
        accessible = true;
    }

}

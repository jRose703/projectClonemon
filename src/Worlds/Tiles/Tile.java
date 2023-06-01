package Worlds.Tiles;

public abstract class Tile {
    boolean accessible;
    int texture_id;

    String tileType;

    public boolean getAccessible() {
        return accessible;
    }

    public String getTileType() {
        return tileType;
    }

    public int getTexture_id() {
        return texture_id;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

}

package Worlds.Tiles;

public abstract class Tile {
    private final int TEXTURE_ID;
    private final String tileType;
    private boolean accessible;

    public Tile(boolean accessible, int TEXTURE_ID, String tileType) {
        this.accessible = accessible;
        this.TEXTURE_ID = TEXTURE_ID;
        this.tileType = tileType;
    }

    public boolean getAccessible() {
        return accessible;
    }

    public String getTileType() {
        return tileType;
    }

    public int getTextureID() {
        return TEXTURE_ID;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

}

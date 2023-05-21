package Worlds.Tiles;

public class WaterTile extends Tile{

    public WaterTile() {
        super(); // needed?
        this.accessible = false;
        this.texture_id = 2;
        this.tileType = "WaterTile";
    }

}

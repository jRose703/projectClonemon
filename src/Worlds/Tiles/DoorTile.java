package Worlds.Tiles;

import Worlds.Coordinates;

public class DoorTile extends Tile{
    String connectedTo;
    Coordinates newCoordinates;

    DoorTile(){
        this.accessible = true;
        this.texture_id = 0;
        this.tileType = "DoorTile";
        this.connectedTo = "world";
        this.newCoordinates = new Coordinates(9,9);
    }

}

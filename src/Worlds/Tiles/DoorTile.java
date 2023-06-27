package Worlds.Tiles;

import Worlds.Coordinates;

public class DoorTile extends Tile {
    private String connectedTo;
    private Coordinates newCoordinates;

    DoorTile() {
        super(true, 4, "DoorTile");
        this.connectedTo = "world";
        this.newCoordinates = new Coordinates(9, 9);
    }

    public String getConnectedTo() {
        return connectedTo;
    }

    public Coordinates getNewCoordinates() {
        return newCoordinates;
    }
}

package Worlds;

import Entity.Entities.Entity;
import Worlds.Tiles.LowGrassTile;
import Worlds.Tiles.Tile;

public class World {

    private int[] size;
    private Tile[][] tileArr;
    private Entity[][] entityArr;
    private boolean status;

    public World(int x_size, int y_size) {
        if (x_size < 2 || y_size < 2) {
            throw new IllegalArgumentException("World must be at least 2x2 big");
        }

        size = new int[]{x_size, x_size};
        tileArr = new Tile[x_size][y_size];
        entityArr = new Entity[x_size][y_size];
        status = false;

        for (int i = 0; i < tileArr[0].length; i++) {
            for (int j = 0; j < tileArr.length; j++) {
                tileArr[j][i] = new LowGrassTile();
            }
        }

        // +EntityArr initialising
    }

    // This function prints out the world with every texture_id of the Tiles.
/*    public void printWorld_ids() {
        for (int i = 0; i < tileArr[0].length; i++) {
            for (Tile[] tiles : tileArr) {
                System.out.print(tiles[i].getTexture_id() + ",");
            }
            System.out.print("\n");
        }
    }*/

    /**
     * This method returns a boolean depending on if the tile is accessible or not.
     */
    public boolean isAccessible(int x, int y) {
        if (x < 0 || y < 0 || x >= getXLength() || y >= getYLength())
            return false;
        if (!getTileArr()[x][y].getAccessible())
            return false;
        if (getEntityArr()[x][y] != null)
            return false;
        return true;
        //else return getTileArr()[x][y].getAccessible();
    }

    /**
     * This function checks return a boolean based on if an enemy/entity is on given Coordinates.
     */
    public boolean tileContainsEnemy(Coordinates coordinates) {
        return entityArr[coordinates.getX()][coordinates.getY()] != null;
    }

    public int getXLength() {
        return this.tileArr.length;
    }

    public int getYLength() {
        return this.tileArr[0].length;
    }

    public boolean getStatus() {
        return this.status;
    }

    public Tile[][] getTileArr() {
        return this.tileArr;
    }

    public Entity[][] getEntityArr() {
        return this.entityArr;
    }

    public Tile getTile(Coordinates coordinates) {
        return this.tileArr[coordinates.getX()][coordinates.getY()];
    }

    public Entity getEntity(Coordinates coordinates) {
        return this.entityArr[coordinates.getX()][coordinates.getY()];
    }

    public void setEntityArr(Entity[][] entityArr) {
        this.entityArr = entityArr;
    }

    public void setTileArr(Tile[][] tileArr) {
        this.tileArr = tileArr;
    }

    /**
     * Sets an entity on a given set of coordinates.
     */
    public void setEntity(Coordinates coordinates, Entity entity) {
        this.entityArr[coordinates.getX()][coordinates.getY()] = entity;
    }

    /**
     * Sets a tile on a given set of coordinates.
     */
    public void setTile(Coordinates coordinates, Tile tile) {
        this.tileArr[coordinates.getX()][coordinates.getY()] = tile;
    }

    public void enable() {
        this.status = true;
    }

    public void disable() {
        this.status = false;
    }

}

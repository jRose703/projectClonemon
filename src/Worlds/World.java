package Worlds;
import Entity.*;
import Worlds.Tiles.*;

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

    public boolean accessible (int x, int y) {
        if (x < 0 || y < 0 || x >= getXLength() || y >= getYLength())
            return false;
        if(!getTileArr()[x][y].getAccessible())
            return false;
        if(getEntityArr()[x][y] != null) {
            return false;
        }
        return true;
        //else return getTileArr()[x][y].getAccessible();
    }


    /**
     * This function prints out the world with every texture_id of the Tiles.
     */
    public void printWorld_ids() {

        for (int i = 0; i < tileArr[0].length; i++) {
            for (Tile[] tiles : tileArr) {
                System.out.print(tiles[i].getTexture_id() + ",");
            }
            System.out.print("\n");
        }
    }


    // get-Methods


    /**
     * This function can get one Tile in the tileArr at given Coordinates to the given Tile
     */
    public int getXLength() {
        return this.tileArr.length;
    }

    /**
     * This function can get one Tile in the tileArr at given Coordinates to the given Tile
     */
    public int getYLength() {
        return this.tileArr[0].length;
    }

    /**
     * This function returns the status of the World.
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * This function returns tileArr of the world
     */
    public Tile[][] getTileArr() {
        return this.tileArr;
    }

    /**
     * This function sets tileArr of the world to given arr
     */
    public void setTileArr(Tile[][] tileArr) {
        this.tileArr = tileArr;
    }

    /**
     * This function returns entityArr of the world
     */
    public Entity[][] getEntityArr() {
        return this.entityArr;
    }


    // set-Methods


    /**
     * This function enables the World.
     */
    public void disable() {
        this.status = false;
    }

    /**
     * This function disables the World.
     */
    public void enable() {
        this.status = true;
    }

    /**
     * This function sets entityArr of the world to given arr
     */
    public void setEntityArr(Entity[][] entityArr) {
        this.entityArr = entityArr;
    }

    /**
     * This function can get one Tile in the tileArr at given Coordinates to the given Tile
     */
    public Tile getTile(Coordinates coordinates) {
        return this.tileArr[coordinates.getX()][coordinates.getY()];
    }

    /**
     * This function can set one Entity in the entityArr at given Coordinates to the given Entity
     */
    public void setEntity(Coordinates coordinates, Entity entity) {
        this.entityArr[coordinates.getX()][coordinates.getY()] = entity;
    }

    /**
     * This function can set one Tile in the tileArr at given Coordinates to the given Tile
     */
    public void setTile(Coordinates coordinates, Tile tile) {
        this.tileArr[coordinates.getX()][coordinates.getY()] = tile;
    }

    /**
     * This function checks return a boolean based on if an enemy/entity is on given Coordinates.
     */
    public boolean enemy_check(Coordinates coordinates) {
        return entityArr[coordinates.getX()][coordinates.getY()] != null;
    }
}

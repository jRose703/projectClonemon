package Worlds;
import Entity.*;
import Worlds.Tiles.LowGrassTile;
import Worlds.Tiles.*;

public class World {

    public Tile[][] tileArr;
    public Entity[][] entityArr;
    public boolean status;


    public World(int x_size, int y_size) {
        if (x_size< 2 || y_size < 2){
            throw new IllegalStateException("World must be at least 2x2 big");
        }

        tileArr = new Tile[x_size][y_size];
        entityArr = new Entity[x_size][y_size];
        status = true;

        for (int i = 0; i < tileArr[0].length; i++) {
            for (int j = 0; j < tileArr.length; j++) {
                tileArr[j][i] = new LowGrassTile();
            }
        }

        // +EntityArr initialising
    }





    /**
     * This function prints out the world with every texture_id of the Tiles.
     */
    public void printWorld_ids() {

        for (int i = 0; i < tileArr[0].length; i++) {
            for (int j = 0; j < tileArr.length; j++) {
                System.out.print(tileArr[j][i].getTexture_id() + ",");
            }
            System.out.print("\n");
        }

    }

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
     * This function returns the status of the World.
     */
    public boolean getStatus(){
        return this.status;
    }

    /**
     * This function checks return a boolean based on if an enemy/entity is on given Coordinates.
     */
    public boolean enemy_check(Coordinates coordinates) {
        return entityArr[coordinates.getX()][coordinates.getY()] != null;
    }

    /**
     * This function returns tileArr of the world
     */
    public Tile[][] getTileArr() {
        return this.tileArr;
    }

    /**
     * This function returns entityArr of the world
     */
    public Entity[][] getEntityArr() {
        return  this.entityArr;
    }

    /**
     * This function can set one Entity in the entityArr at given Coordinates to the given Entity
     */
    public void setEntity(Coordinates coordinates,Entity entity) {
        this.entityArr[coordinates.getX()][coordinates.getY()] = entity;
    }

    /**
     * This function can set one Tile in the tileArr at given Coordinates to the given Tile
     */
    public void setTile(Coordinates coordinates,Tile tile) {
        this.tileArr[coordinates.getX()][coordinates.getY()] = tile;
    }

}

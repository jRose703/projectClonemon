package Worlds;

import Worlds.Tiles.LowGrassTile;
import Worlds.Tiles.RockTile;
import Worlds.Tiles.Tile;

public class World {

    public Tile[][] tileArr;
    public InteractableEntity[][] InteractableEntityArr;
    public boolean status;


    public World(int x_size, int y_size) {
        tileArr = new Tile[x_size][y_size];
        InteractableEntityArr = new InteractableEntity[x_size][y_size];
        status = true;

        for (int i = 0; i < tileArr.length; i++) {
            for (int j = 0; j < tileArr.length; j++) {
                tileArr[i][j] = new LowGrassTile();
            }
        }


    }


    // gegner array hier noch einfügen


    /**
     * This function prints out the world with every texture_id of the Tiles.
     */
    public void printWorld_ids() {
        for (Tile[] tiles : tileArr) {
            for (int j = 0; j < tileArr.length; j++) {
                System.out.print(tiles[j].getTexture_id() + ",");
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
     * This function checks return a boolean based on if an enemy/entity is on given Coordinates.
     */
    public boolean enemy_check() {
        // coordinates class in argument anpassen
        // if enemy is at position
        // return true;

        return false;
    }
}

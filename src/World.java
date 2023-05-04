import java.util.Arrays;
public class World {

    Tile[][] tileArr;
    InteractableEntity[][] InteractableEntityArr;
    boolean status;



    public World(int x_size, int y_size){
        tileArr = new Tile[x_size][y_size];
        InteractableEntityArr =  new InteractableEntity[x_size][y_size];
        status = true;

        for (int i = 0; i < tileArr.length; i++) {
            for (int j = 0; j < tileArr.length; j++) {
                tileArr[i][j] = new LowGrassTile();
            }
        }


        tileArr[0][0] = new RockTile();
        tileArr[0][1] = new LowGrassTile();
        tileArr[0][2] = new Tile();
    }


    // gegner array hier noch einfügen

    public void printWorld_ids(){
        for (Tile[] tiles : tileArr) {
            for (int j = 0; j < tileArr.length; j++) {
                System.out.print(tiles[j].texture_id + ",");
            }
            System.out.print("\n");
        }

    }

    public void disable(){
        this.status = false;
    }
    public void enable(){
        this.status = true;
    }

    public boolean enemy_check(Coordinates position){
        // if enemy is at position
        // return true;

        return false;

    }

}

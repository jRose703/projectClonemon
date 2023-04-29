import java.util.Arrays;
public class World {

    Tile[][] tileArr;
    InteractableEntity[][] InteractableEntityArr;
    boolean status;



    public World(int x_size, int y_size){
        tileArr = new Tile[x_size][y_size];
        InteractableEntityArr =  new InteractableEntity[x_size][y_size];
        status = true;



        tileArr[0][0] = new RockTile();
        tileArr[0][1] = new LowGrassTile();
        tileArr[0][2] = new Tile();
    }


    // gegner array hier noch einfügen

    public void disable(){
        this.status = false;
    }
    public void enable(){
        this.status = true;
    }


    public static void enemy_check(){


    }

}

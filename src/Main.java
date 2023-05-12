import Worlds.Tiles.LowGrassTile;
import Worlds.Tiles.RockTile;
import Worlds.World;

public class Main {

    public static void main(String[] args) {

        System.out.println("Das wird ein Pokemonclone");
        World W1 = new World(3, 3);
        World W2 = new World(3, 3);

        W1.tileArr[0][0] = new RockTile();
        W1.tileArr[0][1] = new LowGrassTile();
        W1.tileArr[0][2] = new RockTile();


        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][0].getClass());
        System.out.println(W1.tileArr[0][0].getTexture_id());
        System.out.println(W1.tileArr[0][0].getAccessible());

        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][1].getClass());
        System.out.println(W1.tileArr[0][1].getTexture_id());
        System.out.println(W1.tileArr[0][1].getAccessible());

        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][2].getClass());
        System.out.println(W1.tileArr[0][2].getTexture_id());
        System.out.println(W1.tileArr[0][2].getAccessible());

        System.out.println("-------------------------------------------");

        W1.printWorld_ids();

        System.out.println("-------------------------------------------");

        W2.printWorld_ids();

        System.out.println("-------------------------------------------");

        Worlds.WriteToJsonFile.saveArr(W1);
        W2.tileArr = Worlds.ReadFromJsonFile.readTileFile();
        W2.printWorld_ids();
    }
}
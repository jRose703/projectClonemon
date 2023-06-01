import Worlds.Coordinates;
import Worlds.ReadAndWrite.ReadFromJsonFile;
import Worlds.ReadAndWrite.WriteToJsonFile;
import Worlds.Tiles.LowGrassTile;
import Worlds.Tiles.RockTile;
import Worlds.World;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Das wird ein Pokemonclone");
        World W1 = new World(3, 3);
        World W2 = new World(3, 3);

        W1.setTile(new Coordinates(0, 0), new RockTile());
        W1.setTile(new Coordinates(0, 1), new LowGrassTile());
        W1.setTile(new Coordinates(0, 2), new RockTile());

        System.out.println(Arrays.deepToString(W1.getTileArr()));
        System.out.println("-------------------------------------------");

        System.out.println(W1.getTile(new Coordinates(0, 0)).getClass());
        System.out.println(W1.getTile(new Coordinates(0, 0)).getTexture_id());
        System.out.println(W1.getTile(new Coordinates(0, 0)).getAccessible());

        System.out.println("-------------------------------------------");

        System.out.println(W1.getTile(new Coordinates(0, 1)).getClass());
        System.out.println(W1.getTile(new Coordinates(0, 1)).getTexture_id());
        System.out.println(W1.getTile(new Coordinates(0, 1)).getAccessible());

        System.out.println("-------------------------------------------");

        System.out.println(W1.getTile(new Coordinates(0, 2)).getClass());
        System.out.println(W1.getTile(new Coordinates(0, 2)).getTexture_id());
        System.out.println(W1.getTile(new Coordinates(0, 2)).getAccessible());

        System.out.println("-------------------------------------------");

        W1.printWorld_ids();

        System.out.println("-------------------------------------------");

        W2.printWorld_ids();

        System.out.println("-------------------------------------------");

        WriteToJsonFile.saveArr(W1, "testworld");
        W2.setTileArr(ReadFromJsonFile.readTilesFromFile("testworld"));
        W2.printWorld_ids();
        World W3 = ReadFromJsonFile.readWorldFromFile("testworld");
        W3.printWorld_ids();
    }
}

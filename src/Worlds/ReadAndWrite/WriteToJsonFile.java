package Worlds.ReadAndWrite;

import Worlds.Coordinates;
import Worlds.World;
import com.google.gson.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Worlds.Tiles.*;

public class WriteToJsonFile {
    private static FileWriter file;
  
    /**
     * This function saves the tileArr[][] to world.json.
     */
    public static void saveArr(World SWorld) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Tile> tileList = new ArrayList<>();


        try {
            file = new FileWriter("SaveFiles\\world.json");

            // not used right now
            for (int i = 0; i < SWorld.getXLength(); i++) {
                for (int j = 0; j < SWorld.getYLength(); j++) {
                    tileList.add(SWorld.getTile(new Coordinates(j,i)));
                }
            }
            String tileJson =  gson.toJson(tileList);
            /*
            List<String> WorldList = new ArrayList<>();
            Coordinates coordinates = new Coordinates(SWorld.tileArr[0].length,SWorld.tileArr.length);
            String jsonW =  gson.toJson(coordinates);
            System.out.println(jsonW);
            String json = gson.toJson(WorldList);
            file.write("[\n");
            file.write(jsonW);
            file.write(",\n");
            file.write(tileJson);
            file.write("\n]");
             */
            file.write(tileJson);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

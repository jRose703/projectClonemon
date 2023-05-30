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
    public static void saveArr(World SWorld,String filename) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Tile> tileList = new ArrayList<>();


        try {
            file = new FileWriter("SaveFiles\\" + filename +".json");

            // not used right now
            for (int i = 0; i < SWorld.getXLength(); i++) {
                for (int j = 0; j < SWorld.getYLength(); j++) {
                    tileList.add(SWorld.getTile(new Coordinates(j,i)));
                }
            }

            String Json =  gson.toJson(SWorld);

            file.write(Json);

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

package Worlds.ReadAndWrite;


import Worlds.Tiles.*;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.io.IOException;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

public class ReadFromJsonFile {


    /**
     * This function reads the world.json and returns a tileArr[][].
     */
    public static Tile[][] readTileFile() {

        RuntimeTypeAdapterFactory<Tile> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Tile.class, "tileType")
                .registerSubtype(LowGrassTile.class, "LowGrassTile")
                .registerSubtype(RockTile.class, "RockTile")
                .registerSubtype(VoidTile.class, "VoidTile");
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();



        try {
            StringBuilder json = new StringBuilder();
            FileReader reader = new FileReader("SaveFiles\\world.json");
            int content;
            while ((content = reader.read()) != -1) {
                json.append((char) content);
            }
            // initialising
            Type listType = new TypeToken<List<Tile>>(){}.getType();

            List<Tile> fromJson = gson.fromJson(json.toString(), listType);

            List<Tile> tileList = new ArrayList<>();


            tileList.addAll(fromJson);

            int x = 3;
            int y = 3;

            Tile[][] tileArr = new Tile[x][y];

            int counter = 0;
            for (int i = 0; i < tileArr.length; i++) {
                for (int j = 0; j < tileArr[0].length; j++) {
                    tileArr[j][i] = tileList.get(counter);
                    counter++;
                }
            }
            return tileArr;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null; // grad eizige lösung scheint mir komisch
    }


}

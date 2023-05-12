package Worlds;

import Worlds.Tiles.LowGrassTile;
import Worlds.Tiles.Tile;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

import java.io.FileWriter;
import java.io.IOException;

public class ReadFromJsonFile {


    /**
     * This function reads the world.json and returns a tileArr[][].
     */
    public static Tile[][] readTileFile() {
        Gson gson = new Gson();


        try {
            JsonReader reader = new JsonReader(new FileReader("src\\Worlds\\world.json"));
            //System.out.println(reader);

            Tile[][] data = gson.fromJson(reader, LowGrassTile[][].class);
            System.out.println(Arrays.toString(data));
            System.out.println(Arrays.toString(data[0]));
            return data;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null; // grad eizige lösung scheint mir komisch
    }


}

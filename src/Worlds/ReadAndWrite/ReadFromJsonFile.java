package Worlds.ReadAndWrite;


import Entity.*;
import Worlds.Tiles.*;
import Worlds.World;
import com.google.gson.*;

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
    public  static Tile[][] readTilesFromFile() {

        RuntimeTypeAdapterFactory<Tile> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Tile.class, "tileType")
                .registerSubtype(LowGrassTile.class, "LowGrassTile")
                .registerSubtype(RockTile.class, "RockTile")
                .registerSubtype(VoidTile.class, "VoidTile");
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

        JsonObject jsonObject = getJsonObjectFromFile();

        String tileArrKey = "tileArr";
        JsonObject tileArrObject = new JsonObject();
        tileArrObject.add(tileArrKey, jsonObject.remove(tileArrKey));
        JsonArray tileJsonArr = tileArrObject.get(tileArrKey).getAsJsonArray();
        StringBuilder tileListString = new StringBuilder();
        tileListString.append("[");
        for (int i = 0; i < tileJsonArr.size(); i++) {
            String z = tileJsonArr.get(i).toString();
            StringBuilder sb = new StringBuilder(z);
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);

            tileListString.append(sb);
            if (i != tileJsonArr.size()-1){
                tileListString.append(",");
            }
        }
        tileListString.append("]");

        // tileArr stuff
        Type listType = new TypeToken<List<Tile>>(){}.getType();

        List<Tile> fromJson = gson.fromJson(tileListString.toString(), listType);

        List<Tile> tileList = new ArrayList<>(fromJson);

        int x = readSizeFromFile()[0];
        int y = readSizeFromFile()[1];

        Tile[][] tileArr = new Tile[x][y];

        int counter = 0;
        for (int i = 0; i < tileArr[0].length; i++) {
            for (int j = 0; j < tileArr.length; j++) {
                tileArr[i][j] = tileList.get(counter);
                counter++;
            }
        }
        return tileArr;



    }


    /**
     * This function reads the world.json and returns a entityArr[][].
     */
    public static Entity[][] readEntitysFromFile(){

        RuntimeTypeAdapterFactory<Entity> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Entity.class, "entityType")
                .registerSubtype(OpponentEntity.class, "OpponentEntity");

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

        JsonObject jsonObject = getJsonObjectFromFile();

        String entityArrKey = "entityArr";
        JsonObject entityArrObject = new JsonObject();
        entityArrObject.add(entityArrKey, jsonObject.remove(entityArrKey));
        JsonArray entityJsonArr = entityArrObject.get(entityArrKey).getAsJsonArray();
        StringBuilder entityListString = new StringBuilder();
        entityListString.append("[");
        for (int i = 0; i < entityJsonArr.size(); i++) {
            String z = entityJsonArr.get(i).toString();
            StringBuilder sb = new StringBuilder(z);
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);

            entityListString.append(sb);
            if (i != entityJsonArr.size()-1){
                entityListString.append(",");
            }
        }
        entityListString.append("]");

        // entityArr stuff
        Type listType = new TypeToken<List<Entity>>(){}.getType();

        List<Entity> fromJson = gson.fromJson(entityListString.toString(), listType);

        List<Entity> entityList = new ArrayList<>(fromJson);

        int x = readSizeFromFile()[0];
        int y = readSizeFromFile()[1];

        Entity[][] entityArr = new Entity[x][y];

        int counter = 0;
        for (int i = 0; i < entityArr[0].length; i++) {
            for (int j = 0; j < entityArr.length; j++) {
                entityArr[i][j] = entityList.get(counter);
                counter++;
            }
        }
        return entityArr;
    }

    /**
     * This function reads the world.json and returns an int[] with x size and y size.
     */
    public static int[] readSizeFromFile(){

        JsonObject jsonObject = getJsonObjectFromFile();

        String sizeKey = "size";
        JsonObject sizeObject = new JsonObject();
        sizeObject.add(sizeKey, jsonObject.remove(sizeKey));
        JsonArray sizeArr = sizeObject.get(sizeKey).getAsJsonArray();

        int x = Integer.parseInt(String.valueOf(sizeArr.get(0)));
        int y = Integer.parseInt(String.valueOf(sizeArr.get(1)));

        return new int[]{x,y};
    }

    /**
     * This function reads the world.json and returns a complete World.
     */
    public static World readWorldFromFile(){
        World world = new World(readSizeFromFile()[0],readSizeFromFile()[1]);

        world.setTileArr(readTilesFromFile());
        world.setEntityArr(readEntitysFromFile());

        return world;
    }

    /**
     * This function reads the world.json and returns a JsonObject to use for other methods.
     */
    private static JsonObject getJsonObjectFromFile(){
        try {
            StringBuilder json = new StringBuilder();
            FileReader reader = new FileReader("SaveFiles\\world.json");

            int content;
            while ((content = reader.read()) != -1) {
                json.append((char) content);
            }
            return new Gson().fromJson(json.toString(), JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

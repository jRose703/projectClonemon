package ReadAndWrite.ReadWriteWorld;

import ReadAndWrite.ReadObjectFromFile;
import Entity.Entity;
import Entity.OpponentEntity;
import Worlds.Tiles.LowGrassTile;
import Worlds.Tiles.RockTile;
import Worlds.Tiles.Tile;
import Worlds.Tiles.VoidTile;
import Worlds.World;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReadFromJsonFile {


    /**
     * This function reads the jsonObject and returns a tileArr[][].
     */
    public static Tile[][] readTilesFromJson(JsonObject jsonObject) {

        RuntimeTypeAdapterFactory<Tile> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Tile.class, "tileType")
                .registerSubtype(LowGrassTile.class, "LowGrassTile")
                .registerSubtype(RockTile.class, "RockTile")
                .registerSubtype(VoidTile.class, "VoidTile");
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

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
            sb.deleteCharAt(sb.length() - 1);

            tileListString.append(sb);
            if (i != tileJsonArr.size() - 1) {
                tileListString.append(",");
            }
        }
        tileListString.append("]");

        // tileArr stuff
        Type listType = new TypeToken<List<Tile>>() {
        }.getType();

        List<Tile> fromJson = gson.fromJson(tileListString.toString(), listType);

        List<Tile> tileList = new ArrayList<>(fromJson);

        int[] size = readSizeFromJson(jsonObject);
        int x = size[0];
        int y = size[1];

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
     * This function reads the jsonObject and returns a entityArr[][].
     */
    public static Entity[][] readEntitysFromJson(JsonObject jsonObject) {

        RuntimeTypeAdapterFactory<Entity> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Entity.class, "entityType")
                .registerSubtype(OpponentEntity.class, "OpponentEntity");

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();


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
            sb.deleteCharAt(sb.length() - 1);

            entityListString.append(sb);
            if (i != entityJsonArr.size() - 1) {
                entityListString.append(",");
            }
        }
        entityListString.append("]");

        // entityArr stuff
        Type listType = new TypeToken<List<Entity>>() {
        }.getType();

        List<Entity> fromJson = gson.fromJson(entityListString.toString(), listType);

        List<Entity> entityList = new ArrayList<>(fromJson);
        int[] size = readSizeFromJson(jsonObject);
        int x = size[0];
        int y = size[1];

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
     * This function reads the jsonObject and returns an int[] with x size and y size.
     */
    public static int[] readSizeFromJson(JsonObject jsonObject) {



        String sizeKey = "size";
        JsonObject sizeObject = new JsonObject();
        sizeObject.add(sizeKey, jsonObject.get(sizeKey));
        JsonArray sizeArr = sizeObject.get(sizeKey).getAsJsonArray();

        int x = Integer.parseInt(String.valueOf(sizeArr.get(0)));
        int y = Integer.parseInt(String.valueOf(sizeArr.get(1)));

        return new int[]{x, y};
    }

    /**
     * This function reads the world.json and returns a complete World.
     */
    public static World readWorldFromFile(String filename) {
        JsonObject jsonObject = ReadObjectFromFile.getJsonObjectFromFile(filename);
        int[] size = readSizeFromJson(jsonObject);
        World world = new World(size[0], size[1]);

        world.setTileArr(readTilesFromJson(jsonObject));
        world.setEntityArr(readEntitysFromJson(jsonObject));

        return world;
    }


}

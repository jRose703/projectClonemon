package ReadAndWrite.WorldOperations;

import BattleSystem.Fighter;
import BattleSystem.Fighters.Citizen;
import BattleSystem.Fighters.Exorcist;
import BattleSystem.Fighters.Undead;
import BattleSystem.FightingType;
import ReadAndWrite.ReadObjectFromFile;
import Entity.Entity;
import Entity.OpponentEntity;
import Worlds.Tiles.*;
import Worlds.World;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Entity.FighterInventory;

public class ReadWorldFromJson {


    /**
     * This function reads the jsonObject and returns a tileArr[][].
     */
    public static Tile[][] readTilesFromJson(JsonObject jsonObject) {

        RuntimeTypeAdapterFactory<Tile> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Tile.class, "tileType")
                .registerSubtype(LowGrassTile.class, "LowGrassTile")
                .registerSubtype(RockTile.class, "RockTile")
                .registerSubtype(WaterTile.class, "WaterTile")
                .registerSubtype(DoorTile.class, "DoorTile")
                .registerSubtype(VoidTile.class, "VoidTile");
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

        String tileArrKey = "tileArr";


        //Todo --> try to simplify and maybe extract into different methode
        String tileListString =  ReadObjectFromFile.Arr2Dto1D(jsonObject, tileArrKey);

        // tileArr stuff
        Type listType = new TypeToken<List<Tile>>() {
        }.getType();

        List<Tile> fromJson = gson.fromJson(tileListString, listType);

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
        RuntimeTypeAdapterFactory<Fighter> runtimeTypeAdapterFactory2 = RuntimeTypeAdapterFactory
                .of(Fighter.class, "type")
                .registerSubtype(Citizen.class, "CITIZEN")
                .registerSubtype(Undead.class, "UNDEAD")
                .registerSubtype(Exorcist.class, "Exorcist");

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).registerTypeAdapterFactory(runtimeTypeAdapterFactory2).create();


        String entityArrKey = "entityArr";


        String entityListString = ReadObjectFromFile.Arr2Dto1D(jsonObject, entityArrKey);

        System.out.println(entityListString);

        // entityArr stuff
        Type listType = new TypeToken<List<Entity>>() {}.getType();

        List<Entity> fromJson = gson.fromJson(entityListString, listType);

        for (Entity e: fromJson) {
            if (e instanceof OpponentEntity) {

                ReadObjectFromFile.addBackBattleType(((OpponentEntity) e).getFighterInventory());

            }
        }

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

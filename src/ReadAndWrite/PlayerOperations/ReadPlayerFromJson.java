package ReadAndWrite.PlayerOperations;

import BattleSystem.Fighter;
import BattleSystem.Fighters.Citizen;
import BattleSystem.Fighters.Exorcist;
import BattleSystem.Fighters.Undead;
import Entity.Entities.PlayerEntity;
import Entity.FighterInventory;
import Entity.Inventory;
import ReadAndWrite.ReadObjectFromFile;
import Worlds.Coordinates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.util.List;

public class ReadPlayerFromJson {


    /**
     * Reads the filename.json and recreates the player entity
     */
    public static PlayerEntity readPlayerFromFile(String filename) {
        JsonObject jsonObject = ReadObjectFromFile.getJsonObjectFromFile(filename);
        PlayerEntity player = new PlayerEntity();

        player.setCoordinates(readCoordinates(jsonObject).getX(),readCoordinates(jsonObject).getY());
        player.setFacing(readFacing(jsonObject));
        player.setCurrentWorld(readCurrentWorld(jsonObject));
        player.setMoney(readMoney(jsonObject));
        player.setInventory(readInventory(jsonObject));
        player.setPlayerFighters(readFighterInventory(jsonObject));
        return player;
    }

    /**
     * Reads player-coordinates and returns them
     */
    private static Coordinates readCoordinates(JsonObject jsonObject){
        int x = jsonObject.get("coordinates").getAsJsonObject().get("x").getAsInt();
        int y = jsonObject.get("coordinates").getAsJsonObject().get("y").getAsInt();

        return new Coordinates(x,y);
    }


    /**
     * Reads player-facing-direction
     */
    private static int readFacing(JsonObject jsonObject){

        int direction = jsonObject.get("facing_direction").getAsInt();

        if (direction < 0 || direction > 3){
            throw new IllegalStateException();
        }
        return direction;
    }

    /**
     * Reads players-currentWorld
     */
    private static String readCurrentWorld(JsonObject jsonObject){
        return jsonObject.get("currentWorld").getAsString();
    }

    /**
     * Reads player-money
     */
    private static int readMoney(JsonObject jsonObject){
        return jsonObject.get("money").getAsInt();
    }

    /**
     * Reads player-inventory
     */
    //#Todo read Inventory
    private static Inventory readInventory(JsonObject jsonObject){
        //JsonArray jsonArray = jsonObject.get("inventory").getAsJsonObject().get("Inventory").getAsJsonArray();
        return new Inventory();
    }

    /**
     * Reads player-FighterInventory
     */
    private static FighterInventory readFighterInventory(JsonObject jsonObject){

        RuntimeTypeAdapterFactory<Fighter> runtimeTypeAdapterFactory2 = RuntimeTypeAdapterFactory
                .of(Fighter.class, "type")
                .registerSubtype(Citizen.class, "CITIZEN")
                .registerSubtype(Undead.class, "UNDEAD")
                .registerSubtype(Exorcist.class, "Exorcist");

        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory2).create();


        Type listType = new TypeToken<List<Fighter>>() {}.getType();
        JsonArray jsonArray = jsonObject.get("playerFighters").getAsJsonObject().get("fighterInventory").getAsJsonArray();

        List<Fighter> fromJson = gson.fromJson(jsonArray, listType);

        FighterInventory fighterInventory = new FighterInventory();

        for (Fighter fighter:fromJson) {
            fighterInventory.addToFighterInventory(fighter);
        }
        ReadObjectFromFile.addBackBattleType(fighterInventory);

        return fighterInventory;
    }




}

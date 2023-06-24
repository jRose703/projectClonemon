package ReadAndWrite.PlayerOperations;

import BattleSystem.Fighter;
import BattleSystem.Fighters.Citizen;
import BattleSystem.Fighters.Exorcist;
import BattleSystem.Fighters.Undead;
import BattleSystem.FightingType;
import Entity.Entity;
import Entity.FighterInventory;
import Entity.Inventory;
import Entity.PlayerEntity;
import Frames.BattleUI.BattleParticipant;
import ReadAndWrite.ReadObjectFromFile;
import Worlds.Coordinates;
import Worlds.Tiles.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.util.List;

public class ReadPlayerFromJson {

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
    private static Coordinates readCoordinates(JsonObject jsonObject){
        int x = jsonObject.get("coordinates").getAsJsonObject().get("x").getAsInt();
        int y = jsonObject.get("coordinates").getAsJsonObject().get("y").getAsInt();

        return new Coordinates(x,y);
    }

    private static int readFacing(JsonObject jsonObject){

        int direction = jsonObject.get("facing_direction").getAsInt();

        if (direction < 0 || direction > 3){
            throw new IllegalStateException();
        }
        return direction;
    }

    private static String readCurrentWorld(JsonObject jsonObject){
        return jsonObject.get("currentWorld").getAsString();
    }

    private static int readMoney(JsonObject jsonObject){
        return jsonObject.get("money").getAsInt();
    }

    //#Todo
    private static Inventory readInventory(JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.get("inventory").getAsJsonObject().get("Inventory").getAsJsonArray();
        System.out.println(jsonArray);
        return new Inventory();
    }

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
        System.out.println(fromJson);

        FighterInventory fighterInventory = new FighterInventory();

        for (Fighter fighter:fromJson) {
            fighterInventory.addToFighterInventory(fighter);
        }
        /*
        for (JsonElement element:jsonArray) {


            JsonObject object = element.getAsJsonObject();
            Fighter fighter = new Citizen(object.get("name").getAsString(),
                    ReadObjectFromFile.toFightingType(object.get("type").getAsString()),
                    object.get("ID").getAsInt(),
                    BattleParticipant.PLAYER,
                    object.get("maxHitpoints").getAsInt(),
                    object.get("attackStat").getAsInt(),
                    object.get("defenseStat").getAsInt(),
                    object.get("initStat").getAsInt());
            fighter.setDefeated(object.get("isDefeated").getAsBoolean());
            fighterInventory.addToFighterInventory(fighter);
        }
        */
        ReadObjectFromFile.addBackBattleType(fighterInventory);

        return fighterInventory;
    }




}

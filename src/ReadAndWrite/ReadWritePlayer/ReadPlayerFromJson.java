package ReadAndWrite.ReadWritePlayer;

import Entity.FighterInventory;
import Entity.Inventory;
import Entity.PlayerEntity;
import ReadAndWrite.ReadObjectFromFile;
import Worlds.Coordinates;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReadPlayerFromJson {

    public static PlayerEntity readPlayerFromFile(String filename) {
        JsonObject jsonObject = ReadObjectFromFile.getJsonObjectFromFile(filename);
        PlayerEntity player = new PlayerEntity();

        player.setCoordinates(readCoordinates(jsonObject).getX(),readCoordinates(jsonObject).getY());
        player.setFacing(readFacing(jsonObject));
        player.setCurrentWorld(readCurrentWorld(jsonObject));
        player.setMoney(readMoney(jsonObject));
        //player.setInventory(readInventory(jsonObject));
        //player.setPlayerFighters(readFighterInventory(jsonObject));


        return player;
    }
    private static Coordinates readCoordinates(JsonObject jsonObject){
        int x = 0;
        int y = 0;
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
        JsonArray jsonArray = jsonObject.get("inventory").getAsJsonArray();
        System.out.println(jsonArray.getAsString());
        return null;
    }

    //#Todo
    private static FighterInventory readFighterInventory(JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.get("playerFighters").getAsJsonArray();
        System.out.println(jsonArray.getAsString());
        return null;
    }



}

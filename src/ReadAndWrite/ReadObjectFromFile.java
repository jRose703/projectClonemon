package ReadAndWrite;

import BattleSystem.FightingType;
import Entity.Entity;
import Frames.BattleUI.BattleParticipant;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import Entity.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadObjectFromFile {


    /**
     * This function reads the world.json and returns a JsonObject to use for other methods.
     */
    public static JsonObject getJsonObjectFromFile(String filename) {
        try {
            StringBuilder json = new StringBuilder();
            FileReader reader = new FileReader("SaveFiles\\" + filename + ".json");

            int content;
            while ((content = reader.read()) != -1) {
                json.append((char) content);
            }
            return new Gson().fromJson(json.toString(), JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static FightingType toFightingType(String string){
        switch (string){
            case "CITIZEN" -> {
                return FightingType.CITIZEN;
            }
            case "UNDEAD" -> {
                return FightingType.UNDEAD;
            }
            case "EXORCIST" -> {
                return FightingType.EXORCIST;
            }
            default -> throw new IllegalStateException();
        }
    }

    public static BattleParticipant toBattleParticipant(String string){
        switch (string){
            case "PLAYER" -> {
                return BattleParticipant.PLAYER;
            }
            case "OPPONENT" -> {
                return BattleParticipant.OPPONENT;
            }
            default -> throw new IllegalStateException();
            }
        }

    public static FighterInventory addBackBattleType(FighterInventory fighterInventory){


                for (int i = 0; i < fighterInventory.size(); i++) {

                    switch (fighterInventory.getFighter(i).getClass().toString()){
                        case "class BattleSystem.Fighters.Citizen" ->{fighterInventory.getFighter(i).setType(FightingType.CITIZEN); break;}
                        case "class BattleSystem.Fighters.Undead" ->{fighterInventory.getFighter(i).setType(FightingType.UNDEAD); break;}
                        case "class BattleSystem.Fighters.Exorcist" ->{fighterInventory.getFighter(i).setType(FightingType.EXORCIST); break;}
                        default -> throw new IllegalStateException();
                    }
                }

    return fighterInventory;
    }

    public static String Arr2Dto1D(JsonObject jsonObject, String entityArrKey) {
        JsonObject entityArrObject = new JsonObject();
        entityArrObject.add(entityArrKey, jsonObject.get(entityArrKey));
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
        return entityListString.toString();
    }
}

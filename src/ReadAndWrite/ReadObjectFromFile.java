package ReadAndWrite;

import BattleSystem.FightingSide;
import BattleSystem.FightingType;
import Entity.FighterInventory;
import Entity.Items.Item;
import Entity.Items.PoisonPotion;
import Entity.Items.Pokedodekaeder;
import Entity.Items.Potion;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

    /**
     * This function adds back the BattleType to the given FighterInventory, because it's set to null by gson reading
     */
    public static FighterInventory addBackBattleType(FighterInventory fighterInventory){
        // ! it says return value is never used but game crashes if the function is not being used


        for (int i = 0; i < fighterInventory.getSize(); i++) {

            switch (fighterInventory.getFighter(i).getClass().toString()) {
                case "class BattleSystem.Fighters.Citizen" -> {
                    fighterInventory.getFighter(i).setType(FightingType.CITIZEN);
                    break;
                }
                case "class BattleSystem.Fighters.Undead" -> {
                    fighterInventory.getFighter(i).setType(FightingType.UNDEAD);
                    break;
                }
                case "class BattleSystem.Fighters.Exorcist" -> {
                    fighterInventory.getFighter(i).setType(FightingType.EXORCIST);
                    break;
                }
                default -> throw new IllegalStateException();
            }
        }

    return fighterInventory;
    }


    /**
     * This funktion adds back the ItemName after it's removed in the reading process
     */
    public static List<Item> addBackItemName(List<Item> itemList){

        for (Item item:itemList) {
            if(item.getClass().equals(Potion.class)){
                item.setName("Potion");
            }
            else if(item.getClass().equals(PoisonPotion.class)){
                item.setName("PoisonPotion");
            }
            else if(item.getClass().equals(Pokedodekaeder.class)){
                item.setName("Pokedodekaeder");
            }
        }
        return itemList;
    }

    /**
     * This function takes the 2D-Array read by gson and converts it to a 1D-Array to convert all objects back to the right type
     */
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

package ReadAndWrite.PlayerOperations;

import Entity.PlayerEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
public class WritePlayerToJson {

    private static FileWriter file;

    public static void savePlayer(PlayerEntity player, String filename){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            file = new FileWriter("SaveFiles\\" + filename + ".json");
            file.write(gson.toJson(player));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

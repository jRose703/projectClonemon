package ReadAndWrite.ReadWriteWorld;
import Worlds.World;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToJsonFile {
    private static FileWriter file;

    /**
     * This function saves the tileArr[][] to filename.json.
     */
    public static void saveWorld(World SWorld, String filename) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            file = new FileWriter("SaveFiles\\" + filename + ".json");

            String Json = gson.toJson(SWorld);
            file.write(Json);

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

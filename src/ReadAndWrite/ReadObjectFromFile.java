package ReadAndWrite;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

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
}

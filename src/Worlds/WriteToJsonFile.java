package Worlds;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;


import java.io.FileWriter;
import java.io.IOException;

public class WriteToJsonFile {
    private static FileWriter file;

    public static void saveArr(World SWorld) {
        JsonArray jsonWorldArr = new JsonArray();




        try {
            file = new FileWriter("src\\Worlds\\world.json");



            for (int i = 0; i < SWorld.tileArr.length; i++) {
                for (int j = 0; j < SWorld.tileArr.length; j++) {
                    JsonObject obj = new JsonObject();


                    obj.put("accessible", SWorld.tileArr[i][j].getAccessible());
                    obj.put("texture_id", SWorld.tileArr[i][j].getTexture_id());
                    //file.write();
                    jsonWorldArr.add(obj);
                }
            }
            //System.out.println(jsonWorldArr.toJson().getClass());
            //System.out.println(jsonWorldArr.toJson());
            file.write(jsonWorldArr.toJson());

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

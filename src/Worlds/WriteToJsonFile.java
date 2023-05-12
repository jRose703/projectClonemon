package Worlds;


import com.google.gson.*;



import java.io.FileWriter;
import java.io.IOException;

public class WriteToJsonFile {
    private static FileWriter file;

    /**
     * This function saves the tileArr[][] to world.json.
     */
    public static void saveArr(World SWorld) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println();
        StringBuilder tileClasses = new StringBuilder();

        try {
            file = new FileWriter("src\\Worlds\\world.json");


            for (int i = 0; i < SWorld.tileArr.length; i++) {
                for (int j = 0; j < SWorld.tileArr.length; j++) {
                    tileClasses.append(SWorld.tileArr[i][j].getClass());
                }
            }


            String tilestring = String.valueOf(tileClasses.delete(0, 6));
            String[] tileNameArr = tilestring.split(" ");


            //file.write("[\n"+gson.toJson(tileNameArr)+ ",\n"+gson.toJson(SWorld.tileArr) +"\n]");
            file.write(gson.toJson(SWorld.tileArr));

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




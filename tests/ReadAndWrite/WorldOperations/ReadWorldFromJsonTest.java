package ReadAndWrite.WorldOperations;

import ReadAndWrite.ReadObjectFromFile;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadWorldFromJsonTest {

    private JsonObject jsonObject;
    @BeforeEach
    void setUp(){
        jsonObject = ReadObjectFromFile.getJsonObjectFromFile("world");
    }

    @Test
    void readTilesFromJson() {
        ReadWorldFromJson.readTilesFromJson(jsonObject);
    }

    @Test
    void readEntitysFromJson() {
        ReadWorldFromJson.readEntitysFromJson(jsonObject);
    }

    @Test
    void readSizeFromJson() {
        ReadWorldFromJson.readSizeFromJson(jsonObject);
    }

    @Test
    void readWorldFromFile() {
        ReadWorldFromJson.readWorldFromFile("world");
    }
}
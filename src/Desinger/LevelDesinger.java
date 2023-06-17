package Desinger;

import ReadAndWrite.WorldOperations.ReadWorldFromJson;
import Worlds.World;

import java.util.Objects;

public class LevelDesinger {


    public World accessWorld(String filename) {
        World editWorld;
        if(Objects.equals(filename, "")){
            int x = 10; // --> input hier
            int y = 10; // input hier
            editWorld = new World(x,y);
        }
        else {
            editWorld = ReadWorldFromJson.readWorldFromFile(filename);
        }
        return editWorld;
    }









}

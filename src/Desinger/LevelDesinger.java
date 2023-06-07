package Desinger;

import Worlds.ReadAndWrite.ReadFromJsonFile;
import Worlds.World;

import java.io.FileNotFoundException;
import java.io.IOException;
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
            editWorld = ReadFromJsonFile.readWorldFromFile(filename);
        }
        return editWorld;
    }









}

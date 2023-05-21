package Worlds;

import Entity.Entity;
import Entity.OpponentEntity;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {


    private World world;
    private World world3x3;
    private World world3x4;
    private World world4x3;
    private World world100x100;

    private Coordinates coordinates;



    WorldTest(){
        world = new World(10,10);
        world3x3 = new World(3,3);
        world3x4 = new World(3,4);
        world4x3 = new World(4,3);
        world100x100 = new World(100,100);
        coordinates = new Coordinates(0,0);

    }
    @Disabled
    @Test
    void printWorld_ids() {
        // need idea for pretty console output of the ids
    }

    @Test
    void disable() {
        world.disable();
        assertFalse(world.getStatus());
    }

    @Test
    void enable() {
        world.enable();
        assertTrue(world.getStatus());
    }

    @Test
    void enemy_check() {
        // add an enemy to the entity array here at 0 0
        Entity opponent = new OpponentEntity();
        world.setEntity(coordinates,opponent);

        assertTrue(world.enemy_check(coordinates));
        coordinates.setX(1);
        assertFalse(world.enemy_check(coordinates));

    }

    @Disabled
    @Test
    void getTileArr() {


    }

    @Disabled
    @Test
    void getEntityArr() {
    }
}
package Worlds;

import Entity.Entity;
import Entity.OpponentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {


    private World world;
    private World world1x1;
    private World world3x3;
    private World world3x4;
    private World world4x3;
    private World world100x100;

    private Coordinates coordinates;


    @BeforeEach
    void setUp() {
        world = new World(10, 10);
        world3x3 = new World(3, 3);
        world3x4 = new World(3, 4);
        world4x3 = new World(4, 3);
        world100x100 = new World(100, 100);
        coordinates = new Coordinates(0, 0);

    }

    @Test
    void smallWorldTest() {

        try {
            world1x1 = new World(1, 1);
            fail("World must be at least 2x2 big");
        } catch (IllegalArgumentException ignored) {

        }
        assertNull(world1x1);

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
        world.setEntity(coordinates, opponent);

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

    @Disabled
    @Test
    void getStatus() {
    }

    @Disabled
    @Test
    void setEntity() {
    }

    @Disabled
    @Test
    void getTile() {
    }

    @Disabled
    @Test
    void setTile() {
    }

    @Disabled
    @Test
    void getXLength() {
    }

    @Disabled
    @Test
    void getYLength() {
    }

    @Disabled
    @Test
    void setTileArr() {
    }
}
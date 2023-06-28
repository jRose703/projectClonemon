package Worlds;

import Entity.Entities.Entity;
import Entity.Entities.OpponentEntity;
import Worlds.Tiles.LowGrassTile;
import Worlds.Tiles.RockTile;
import Worlds.Tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {


    private World world;
    private World world1x1;
    private World world3x3;
    private World world3x4;
    private World world4x3;
    private World world100x100;

    private Coordinates coordinates;
    private Tile[][] tileArr;


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

    @Test
    void disableTest() {
        world.disable();
        assertFalse(world.getStatus());
    }

    @Test
    void enableTest() {
        world.enable();
        assertTrue(world.getStatus());
    }

    @Test
    void enemy_checkTest() {
        Entity opponent = new OpponentEntity();
        world.setEntity(coordinates, opponent);
        assertTrue(world.tileContainsEnemy(coordinates));
        coordinates.setX(1);
        assertFalse(world.tileContainsEnemy(coordinates));
    }

    @Disabled
    @Test
    void getTileArrTest() {


    }

    @Disabled
    @Test
    void getEntityArrTest() {
    }

    @Test
    void getStatusTest() {
        world.enable();
        assertTrue(world.getStatus());
        world.disable();
        assertFalse(world.getStatus());
    }

    @Test
    void setEntityTest() {
        Entity entity =  new OpponentEntity();
        world.setEntity(coordinates, entity);
        assertEquals(world.getEntity(coordinates),entity);
    }

    @Disabled
    @Test
    void getTileTest() {
    }

    @Test
    void setTileTest() {
        Tile tile =  new RockTile();
        world.setTile(coordinates, tile);
        assertEquals(world.getTile(coordinates),tile);

    }

    @Test
    void getXLengthTest() {
        assertEquals(world3x3.getXLength(),3);
        assertEquals(world3x4.getXLength(),3);
        assertEquals(world4x3.getXLength(),4);
        assertEquals(world.getXLength(),10);
        assertEquals(world100x100.getXLength(),100);
    }


    @Test
    void getYLengthTest() {
        assertEquals(world3x3.getYLength(),3);
        assertEquals(world3x4.getYLength(),4);
        assertEquals(world4x3.getYLength(),3);
        assertEquals(world.getYLength(),10);
        assertEquals(world100x100.getYLength(),100);
    }

}
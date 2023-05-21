package Worlds.Tiles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LowGrassTileTest {

    private Tile tile;

    LowGrassTileTest(){
        tile = new LowGrassTile();
    }

    @Test
    void getAccessible() {
        assertTrue(tile.getAccessible());
    }

    @Test
    void getTexture_id() {
        assertEquals(tile.getTexture_id(),0);
    }

    @Test
    void getTileType() {
        assertEquals(tile.getTileType(),"LowGrassTile");
    }


    @Test
    void setAccessible() {
        tile.setAccessible(false);
        assertFalse(tile.getAccessible());
        tile.setAccessible(true);
        assertTrue(tile.getAccessible());
    }

}
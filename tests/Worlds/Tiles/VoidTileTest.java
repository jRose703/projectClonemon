package Worlds.Tiles;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoidTileTest {

    private Tile tile;

    VoidTileTest(){
        tile =  new VoidTile();
    }


    @Test
    void getAccessible() {
        assertFalse(tile.getAccessible());
    }

    @Test
    void getTexture_id() {
        assertEquals(tile.getTexture_id(),-1);
    }

    @Test
    void getTileType() {
        assertEquals(tile.getTileType(),"VoidTile");
    }


    @Test
    void setAccessible() {
        tile.setAccessible(false);
        assertFalse(tile.getAccessible());
        tile.setAccessible(true);
        assertTrue(tile.getAccessible());
    }

}
package Worlds.Tiles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RockTileTest {

    private Tile tile;

    RockTileTest() {
        tile = new RockTile();
    }

    @Test
    void getAccessible() {
        assertFalse(tile.getAccessible());
    }

    @Test
    void getTexture_id() {
        assertEquals(tile.getTextureID(), 1);
    }

    @Test
    void getTileType() {
        assertEquals(tile.getTileType(), "RockTile");
    }


    @Test
    void setAccessible() {
        tile.setAccessible(false);
        assertFalse(tile.getAccessible());
        tile.setAccessible(true);
        assertTrue(tile.getAccessible());
    }

}
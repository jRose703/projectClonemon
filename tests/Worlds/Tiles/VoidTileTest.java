package Worlds.Tiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoidTileTest {

    private Tile tile;

    @BeforeEach
    void setUp() {
        tile = new VoidTile();
    }

    @Test
    void getAccessible() {
        assertFalse(tile.getAccessible());
    }

    @Test
    void getTexture_id() {
        assertEquals(tile.getTextureID(), -1);
    }

    @Test
    void getTileType() {
        assertEquals(tile.getTileType(), "VoidTile");
    }


    @Test
    void setAccessible() {
        tile.setAccessible(false);
        assertFalse(tile.getAccessible());
        tile.setAccessible(true);
        assertTrue(tile.getAccessible());
    }

}
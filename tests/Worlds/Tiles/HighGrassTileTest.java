package Worlds.Tiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighGrassTileTest {
    private Tile tile;

    @BeforeEach
    void setUp() {
        tile = new HighGrassTile();
    }

    @Test
    void getAccessible() {
        assertTrue(tile.getAccessible());
    }

    @Test
    void getTexture_id() {
        assertEquals(tile.getTextureID(), 2);
    }

    @Test
    void getTileType() {
        assertEquals(tile.getTileType(), "HighGrassTile");
    }


    @Test
    void setAccessible() {
        tile.setAccessible(false);
        assertFalse(tile.getAccessible());
        tile.setAccessible(true);
        assertTrue(tile.getAccessible());
    }

}
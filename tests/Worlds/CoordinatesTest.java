package Worlds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinatesTest {

    private Coordinates coordinates;


    @BeforeEach
    void setUp() {
        coordinates = new Coordinates(0, 0);
    }
    @Test
    void setX() {
        coordinates.setX(1);
        assertEquals(1, coordinates.getX());
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> coordinates.setX(-1),
                "Coordinates can not be negative!"
        );
        assertEquals(thrown.getMessage(),"Coordinates can not be negative!");
    }


    @Test
    void setY() {
        coordinates.setY(1);
        assertEquals(1, coordinates.getY());
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> coordinates.setY(-1),
                "Coordinates can not be negative!"
        );
        assertEquals(thrown.getMessage(),"Coordinates can not be negative!");
    }

    @Test
    void getX() {
        assertEquals(0, coordinates.getX());
    }

    @Test
    void getY() {
        assertEquals(0, coordinates.getY());
    }

    @Test
    void testToString() {
        assertEquals("0 0", coordinates.toString());
    }
}
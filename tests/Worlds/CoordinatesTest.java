package Worlds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    private Coordinates coordinates;
    CoordinatesTest(){
        coordinates =  new Coordinates(0,0);
    }



    @Test
    void setX() {
        coordinates.setX(1);
        assertEquals(1,coordinates.getX());


        // --> test if throws error if negative coordinates
        coordinates.setX(-1);
        //assertThrows(IllegalStateException(),);
    }

    @Test
    void setY() {
        coordinates.setY(1);
        assertEquals(1,coordinates.getY());


        // --> test if throws error if negative coordinates
        coordinates.setY(-1);
        //assertThrows(IllegalStateException(),);
    }

    @Test
    void getX() {
        assertEquals(0,coordinates.getX());
    }

    @Test
    void getY() {
        assertEquals(0,coordinates.getY());
    }

    @Test
    void testToString() {
        assertEquals("0 0", coordinates.toString());
    }
}
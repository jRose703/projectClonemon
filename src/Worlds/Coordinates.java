package Worlds;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x_given, int y_given) {
        if (x_given < 0 || y_given < 0){
            throw new IllegalStateException("Coordinates can not be negative!");
        }
        this.x = x_given;
        this.y = y_given;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        if (x <0){
            throw new IllegalStateException("Coordinates can not be negative!");
        }
        this.x = x;
    }

    public void setY(int y) {
        if (y <0){
            throw new IllegalStateException("Coordinates can not be negative!");
        }
        this.y = y;
    }

    public String toString() {
        return (x + " " + y);
    }
}

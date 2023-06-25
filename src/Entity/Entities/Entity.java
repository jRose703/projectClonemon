package Entity.Entities;

import Worlds.Coordinates;
import Worlds.World;

public abstract class Entity {
    private Coordinates coordinates;
    private int facing_direction;

    public Entity() {
    }

    public void move(int direction, World world) {
        if (direction % 2 != 0) {
            if (direction == 1) {
                if (world.accessible(this.coordinates.getX() + 1, this.coordinates.getY()))
                    this.coordinates.setX(this.coordinates.getX() + 1);
            } else if (direction == 3) {
                if (world.accessible(this.coordinates.getX() - 1, this.coordinates.getY()))
                    this.coordinates.setX(this.coordinates.getX() - 1);
            } else {
                throw new IllegalArgumentException("invalid direction");
            }

        } else {
            if (direction == 2) {
                if (world.accessible(this.coordinates.getX(), this.coordinates.getY() + 1))
                    this.coordinates.setY(this.coordinates.getY() + 1);
            } else if (direction == 0) {
                if (world.accessible(this.coordinates.getX(), this.coordinates.getY() - 1))
                    this.coordinates.setY(this.coordinates.getY() - 1);
            } else {
                throw new IllegalArgumentException("invalid direction");
            }
        }

    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getFacing() {
        return facing_direction;
    }

    public void setFacing(int direction_given) {
        this.facing_direction = direction_given;
    }

    public void setCoordinates(int x_given, int y_given) {
        this.coordinates = new Coordinates(x_given, y_given);
    }

}

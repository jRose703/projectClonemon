package Entity;

import Worlds.Coordinates;
import Worlds.World;

public class Entity{
    public Coordinates coordinates;
    public int facing_direction;
    public Entity(){}
    public void setCoordinates(int x_given, int y_given){
        this.coordinates = new Coordinates(x_given, y_given);
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setFacing(int direction_given){
        this.facing_direction = direction_given;
    }
    public int getFacing(){
        return facing_direction;
    }
    public void move(int direction, World world){
        if (direction %2 != 0){
            if (direction == 1){
                if (world.accessible(this.coordinates.x + 1, this.coordinates.y))
                    this.coordinates.x += 1;
            }
            else if (direction == 3){
                if (world.accessible(this.coordinates.x - 1, this.coordinates.y))
                    this.coordinates.x -= 1;
            }
            else{
                throw new IllegalArgumentException("invalid direction");
            }

        }
        else{
            if (direction == 2){
                if (world.accessible(this.coordinates.x, this.coordinates.y + 1))
                    this.coordinates.y += 1;
            }
            else if (direction == 0){
                if (world.accessible(this.coordinates.x, this.coordinates.y - 1))
                    this.coordinates.y -= 1;
            }
            else{
                throw new IllegalArgumentException("invalid direction");
            }
        }

    }

}

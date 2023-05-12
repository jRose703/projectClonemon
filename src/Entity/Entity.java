package Entity;

import World.Coordinates;
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
    public void interact(){
        // do something
    }
    public void move(int direction, int amount){
        if (direction %2 != 0){
            if (direction == 1){
                this.coordinates.x += amount;

            }
            else if (direction == 3){
                this.coordinates.x -=amount;
            }
            else{
                throw new IllegalArgumentException("invalid direction");
            }

        }
        else{
            if (direction == 2){
                this.coordinates.y +=amount;
            }
            else if (direction == 0){
                this.coordinates.y -=amount;
            }
            else{
                throw new IllegalArgumentException("invalid direction");
            }
        }

    }

}

public class Character {
    public int x;
    public int y;
    public int direction;
    public Coordinates coordinates = new Coordinates(x,y);
    //TODO public String[] Inventory;

    public void setCoordinates(int x_given, int y_given){
        this.x = x_given;
        this.y = y_given;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setDirection(int direction_given){
        this.direction = direction_given;
    }
    public int getDirection(){
        return direction;
    }
    public void interact(){
        // do something
    }
    public void move(int direction, int amount){
        if (direction %2 != 0){
            if (direction == 1){
                this.x += amount;

            }
            else if (direction == 3){
                this.x -=amount;
            }
            else{
                throw new IllegalArgumentException("invalid direction");
            }

        }
        else{
            if (direction == 2){
                this.y -=amount;
            }
            else if (direction == 4){
                this.y +=amount;
            }
            else{
                throw new IllegalArgumentException("invalid direction");
            }
        }

    }


}

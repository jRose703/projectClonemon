public class Enemy {
    public Coordinates coordinates;
    public boolean status = false;
    public int direction;
    public Enemy(){}
    public void setCoordinates(int x_given, int y_given){
        this.coordinates = new Coordinates(x_given, y_given);
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
    public void setStatus(boolean status_given){
        this.status = status_given;
    }
    public boolean getStatus(){
        return status;
    }

    public boolean check(){
        //TODO
        return false;
    }


}

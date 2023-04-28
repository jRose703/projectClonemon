public class Charakter {
    public int x;
    public int y;
    public int direction;
    public Coordinates coordinates = new Coordinates(x,y);
    //TODO public String[] Inventar;

    public void setCoordinates(int x_given, int y_given){
        this.x = x_given;
        this.y = y_given;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setCoordinates(int direction_given){
        this.direction = direction_given;
    }
    public int getDirection(){
        return direction;
    }


}

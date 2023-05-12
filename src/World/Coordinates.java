package World;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x_given, int y_given){
        this.x = x_given;
        this.y = y_given;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public String toString(){
        return (x + " " + y);
    }
}

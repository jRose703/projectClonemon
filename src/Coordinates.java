public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x_given, int y_given){
        this.x = x_given;
        this.y = y_given;
    }
    public String toString(){
        return (x + " " + y);
    }
}

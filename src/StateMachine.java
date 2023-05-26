import Frames.Frame;
import Observer.Observer;
import Worlds.World;

public class StateMachine implements Observer {

    private Frame frame;

    public StateMachine(int worldSizeX, int worldSizeY){
        this.frame = new Frame(new World(worldSizeX, worldSizeY), this);
        frame.reloadWorld();
    }

    @Override
    public void update(Object o) {
        System.out.println((String) o);
    }
}

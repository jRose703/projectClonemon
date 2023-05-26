import Frames.Frame;
import Observer.*;
import Worlds.World;

public class StateMachine implements Observer {

    private Frame frame;

    public StateMachine(int worldSizeX, int worldSizeY){
        this.frame = new Frame(new World(worldSizeX, worldSizeY), this);
        frame.reloadWorld();
    }

    @Override
    public void update(ObserveType t, Object o) {
        switch (t){
            case BATTLE_START -> System.out.println("Battle Start!");
            case BATTLE_END -> System.out.println("Battle Ended!");
            case DIALOGUE -> System.out.println("Dialogue Started!");
        }
    }
}

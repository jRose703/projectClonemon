import Frames.Frame;
import Observer.*;
import Worlds.World;

public class StateMachine implements Observer {

    private final Frame frame;

    public StateMachine(int worldSizeX, int worldSizeY){
        this.frame = new Frame(new World(worldSizeX, worldSizeY), this);
        frame.reloadWorld();
    }

    @Override
    public void update(ObserveType t, Object o) {
        switch (t){
            case BATTLE_START -> startBattle();
            case BATTLE_END -> endBattle();
            case DIALOGUE -> startDialogue();
        }
    }
    private void startBattle() {
        System.out.println("Battle Started");
        frame.changeToBattleScene();
    }
    private void endBattle() {
        System.out.println("Battle Ended!");
        frame.changeToWorldScene();
    }
    private void startDialogue() {
        System.out.println("Dialogue Started!");
    }
}

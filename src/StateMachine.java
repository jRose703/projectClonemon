import Frames.Frame;
import Frames.TextBox.DialogueType;
import Observer.ObserveType;
import Observer.Observer;
import Worlds.World;

public class StateMachine implements Observer {

    private final Frame frame;

    public StateMachine(int worldSizeX, int worldSizeY){
        this.frame = new Frame(new World(worldSizeX, worldSizeY), this);
        frame.reloadWorld();
        startDialogue("Start Battle!");
    }

    @Override
    public void update(ObserveType t, Object o) {
        switch (t) {
            case BATTLE_START -> startBattle();
            case BATTLE_END -> endBattle();
            case DIALOGUE_START -> startDialogue((String) o);
            case DIALOGUE_END -> endDialogue((DialogueType) o);
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

    private void startDialogue(String text) {
        System.out.println("Dialogue Started!");
        frame.startDialogue(text);
    }

    private void endDialogue(DialogueType type) {
        switch (type) {
            case BATTLE:
                startBattle();
                break;
            default:
                break;
        }
    }

}

import Frames.Frame;
import Frames.TextBox.DialogueType;
import Observer.ObserveType;
import Observer.Observer;
import ReadAndWrite.ReadWriteWorld.ReadFromJsonFile;

/**
 * Observes the state of the game and manipulates the game accordingly
 */
public class StateMachine implements Observer {

    private final Frame frame;

    /** Creates the game frame */
    public StateMachine(){
        this.frame = new Frame(ReadFromJsonFile.readWorldFromFile("world"), this);
        startDialogue("Let's Fight!");  //TEST
    }

    /** Listens for game state updates */
    @Override
    public void update(ObserveType t, Object o) {
        switch (t) {
            case BATTLE_START -> startBattle();
            case BATTLE_END -> endBattle();
            case DIALOGUE_START ->
                    startDialogue((String) o);  //Würde ich später direkt von den Entities auslösen lassen, da es ja worldPane intern ist
            case DIALOGUE_END -> endDialogue((DialogueType) o);
        }
    }

    private void startBattle() {
        frame.changeToBattleScene();
    }

    private void endBattle() {
        frame.changeToWorldScene();
    }

    private void startDialogue(String text) {
        frame.startDialogue(text);
    }

    /** Starts the event a dialogue causes */
    private void endDialogue(DialogueType type) {
        switch (type) {
            case BATTLE:
                startBattle();
                break;
            case TEXT:
                break;
        }
    }

}

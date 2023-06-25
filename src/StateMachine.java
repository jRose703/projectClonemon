import BattleSystem.Fighter;
import BattleSystem.FightingSide;
import Entity.Entities.OpponentEntity;
import Frames.Frame;
import Observer.ObserveType;
import Observer.Observer;
import ReadAndWrite.WorldOperations.ReadWorldFromJson;

/**
 * Observes the state of the game and manipulates the game accordingly
 */
public class StateMachine implements Observer {

    private final Frame frame;

    /** Creates the game frame */
    public StateMachine(){
        this.frame = new Frame(ReadWorldFromJson.readWorldFromFile("world"), this);
        //startDialogue("Let's Fight!");  //TEST
    }

    /**
     * Listens for game state updates
     */
    @Override
    public void update(ObserveType t, Object o) {
        switch (t) {
            case BATTLE_START -> startBattle(null, (Fighter) o, false); //TODO
            case BATTLE_END -> endBattle((Object[]) o);
            case DIALOGUE_END -> endDialogue((OpponentEntity) o);
        }
    }

    private void startBattle(OpponentEntity opponent, Fighter wildFighter, boolean isTrainerBattle) {
        frame.changeToBattleScene(opponent, wildFighter, isTrainerBattle);
    }

    private void endBattle(Object[] o) {
        if ((boolean) o[0] && o[1] != null && o[1].equals(FightingSide.PLAYER))
            frame.setOpponentDefeated();
        frame.changeToWorldScene();
    }

    /**
     * Starts an event based on the dialogueType.
     */
    private void endDialogue(OpponentEntity opponent) {
        switch (opponent.getInteractionType()) {
            case BATTLE:
                startBattle(opponent, null, true);
                break;
            case TEXT:
                break;
        }
    }

}

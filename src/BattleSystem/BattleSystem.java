package BattleSystem;

import Entity.FighterInventory;
import Frames.BattleUI.BattleObserver;
import Observer.ObserveType;
import Observer.Observer;

@SuppressWarnings("FieldMayBeFinal")
public class BattleSystem {

    private Fighter player;
    private Fighter opponent;

    private FighterInventory playerFighter;
    private FighterInventory opponentFighter;

    private int playerIndex = 0;
    private int opponentIndex = 0;

    private int round = 1;  // just for testing purposes
    private final Observer stateMachineObserver;
    private final BattleObserver battleObserver;

    public BattleSystem(Observer stateMachineObserver, BattleObserver battleObserver,
                        FighterInventory playerFighter, FighterInventory opponentFighter) {
        this.player = playerFighter.getFighter(playerIndex);
        this.opponent = opponentFighter.getFighter(opponentIndex);

        this.playerFighter = playerFighter;
        this.opponentFighter = opponentFighter;

        this.stateMachineObserver = stateMachineObserver;
        this.battleObserver = battleObserver;

        battleObserver.setFighter(player);
        battleObserver.setFighter(opponent);
    }

    /**
     * This method is the battle loop. It starts when a new BattleSystem is created and ends if the player flees
     * or one fighter's HP drop to 0.
     */
    public void round(String action) {
        System.out.println("Round " + round);  // just for testing purposes

        // decides whether the player or the opponent is faster with their action
        if(this.player.getInitStat() >= this.opponent.getInitStat()){
            this.playerAction(action);
            this.attacks(this.opponent, this.player);

        }else{
            this.attacks(this.opponent, this.player);
            this.playerAction(action);
        }
        round++;  // just for testing purposes
    }

    /**
    This method executes the actionInput that was either "attack" or "flee".
    */
    private void playerAction(String chosenAction){
        switch (chosenAction){
            case "fight": this.attacks(this.player, this.opponent); break;
            case "run": this.player.flee();
            default: throw new IllegalStateException("Chosen action was not attack or flee!");
        }
    }

    /**
    This method takes in an attacking and a defending fighter.
     The attacking fighter then uses its attack method to inflict damage to the defender.
     If the defender is defeated the battle ends.
    */
    private void attacks(Fighter attacker, Fighter defender) {
        attacker.attack(defender);
        System.out.println(defender.getHitpoints());
        battleObserver.updateHitpointBar(defender.getBattleParty(), defender.getHitpoints());  // just for testing purposes

        if (defender.isDefeated()) {
            switch (defender.getBattleParty()) {
                case PLAYER -> {
                    playerIndex++;
                    if (playerFighter.hasNext(playerIndex)) {
                        player = playerFighter.getFighter(playerIndex);
                        battleObserver.setFighter(player);
                    } else
                        this.endBattle();
                }
                case OPPONENT -> {
                    opponentIndex++;
                    if (opponentFighter.hasNext(opponentIndex)) {
                        opponent = opponentFighter.getFighter(opponentIndex);
                        battleObserver.setFighter(opponent);
                    } else
                        this.endBattle();
                }
            }
            System.out.printf("%s is defeated! Battle ends now!", defender.getName());  // just for testing purposes
        }
    }

    /**
    Ends the battle. The battle is currently ended by ending the program.
    */
    private void endBattle(){
        stateMachineObserver.update(ObserveType.BATTLE_END, null);
    }
}

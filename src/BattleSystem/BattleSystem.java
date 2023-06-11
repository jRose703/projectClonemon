package BattleSystem;

import Entity.FighterInventory;
import Frames.BattleUI.BattleObserver;
import Observer.ObserveType;
import Observer.Observer;

public class BattleSystem {

    private Fighter player;
    private Fighter opponent;

    private final FighterInventory playerFighter;
    private final FighterInventory opponentFighter;

    private int playerIndex = 0;
    private int opponentIndex = 0;

    private int round = 1;  // just for testing purposes
    private boolean isEnded = false;
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
        if(isEnded) return;
        System.out.println("Round " + round);  // just for testing purposes

        // decides whether the player or the opponent is faster with their action
        if(player.getInitStat() >= opponent.getInitStat()){
            this.playerAction(action);
            if(isEnded) return;
            this.attacks(this.opponent, this.player);

        }else{
            this.attacks(this.opponent, this.player);
            if(isEnded) return;
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
            case "run": if(player.flee()) endBattle(); break;
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
        battleObserver.updateHitpoints(defender.getBattleParty(), defender.getHitpoints());  // just for testing purposes

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
            System.out.printf("%s is defeated!\n", defender.getName());  // just for testing purposes
        }
    }

    /**
    Ends the battle. The battle is currently ended by ending the program.
    */
    private void endBattle(){
        stateMachineObserver.update(ObserveType.BATTLE_END, null);
        isEnded = true;
    }
}

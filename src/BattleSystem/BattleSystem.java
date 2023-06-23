package BattleSystem;

import Entity.FighterInventory;
import Frames.BattleUI.BattleObserver;
import Frames.BattleUI.BattleParticipant;
import Observer.ObserveType;
import Observer.Observer;

public class BattleSystem {

    private Fighter player;
    private Fighter opponent;

    private final FighterInventory playerFighter;
    private final FighterInventory opponentFighter;

    private int playerIndex;
    private int opponentIndex = 0;

    private int round = 1;  // just for testing purposes
    private boolean isCurrentFighterDefeated = false;
    private boolean isTrainerBattle;
    private boolean isEnded = false;
    private BattleParticipant winner;

    private final Observer stateMachineObserver;
    private final BattleObserver battleObserver;

    public BattleSystem(Observer stateMachineObserver, BattleObserver battleObserver,
                        FighterInventory playerFighter, FighterInventory opponentFighter, boolean isTrainerBattle) {

        for (Fighter fighter : playerFighter.getFighterInventory())
            if (!fighter.isDefeated()) {
                playerIndex = playerFighter.getFighterIndex(fighter);
                break;
            }

        this.player = playerFighter.getFighter(playerIndex);
        this.opponent = opponentFighter.getFighter(opponentIndex);

        this.playerFighter = playerFighter;
        this.opponentFighter = opponentFighter;

        this.isTrainerBattle = isTrainerBattle;

        this.stateMachineObserver = stateMachineObserver;
        this.battleObserver = battleObserver;

        battleObserver.setFighter(player);
        battleObserver.setFighter(opponent);
    }

    public void round(String action) {
        this.round(action, null);
    }

    /**
     * This method is the battle loop. It starts when a new BattleSystem is created and ends if the player flees
     * or one fighter's HP drop to 0.
     */
    public void round(String action, Integer switchIndex) {
        if (isEnded) return;
        if (action.equals("run") && isTrainerBattle) {
            System.out.println("You can't run away from a trainer battle!");
            return;
        }
        if (switchIndex != null && playerFighter.getFighterInventory().indexOf(player) == switchIndex) {
            System.out.println("You can't switch that fighter in! It's already fighting!");
            return; // if you try to switch in an already fighting Fighter: do nothing
        }
        System.out.println("Round " + round);  // just for testing purposes

        // decides whether the player or the opponent is faster with their action
        if (player.getInitStat() >= opponent.getInitStat() || !(action.equals("fight"))) {
            if (this.playerAction(action, switchIndex) || isEnded) return;
            this.attacks(this.opponent, this.player);

        } else {
            if (this.attacks(this.opponent, this.player) || isEnded) return;
            this.playerAction(action, switchIndex);
        }
        round++;  // just for testing purposes
    }

    /**
     * This method executes the actionInput that was either "attack" or "flee".
     */
    private boolean playerAction(String chosenAction, Integer switchIndex) {
        switch (chosenAction) {
            case "fight":
                return this.attacks(this.player, this.opponent);
            case "switch":
                player = playerFighter.getFighter(switchIndex);
                battleObserver.setFighter(player);
                System.out.println(player.getName());
                return false;
            case "run":
                if (player.flee()) endBattle();
                return false;
            default:
                throw new IllegalStateException("Chosen action was not attack or flee!");
        }
    }

    /**
     * This method takes in an attacking and a defending fighter.
     * The attacking fighter then uses its attack method to inflict damage to the defender.
     * If the defender is defeated the battle ends.
     */
    private boolean attacks(Fighter attacker, Fighter defender) {
        DamageCalculation.calculateDamage(attacker, defender);
        System.out.println("Defender HP: " + defender.getHitpoints());
        System.out.println();
        battleObserver.updateHitpoints(defender.getBattleParty(), defender.getHitpoints());  // just for testing purposes

        if (defender.isDefeated()) {
            switch (defender.getBattleParty()) {
                case PLAYER -> {
                    playerIndex++;
                    if (!playerFighter.hasNext()) {
                        winner = BattleParticipant.OPPONENT;
                        this.endBattle();
                    } else {
                        isCurrentFighterDefeated = true;
                        battleObserver.showFighterinventoryUI();
                        return true;
                    }
                }
                case OPPONENT -> {
                    opponentIndex++;
                    if (!opponentFighter.hasNext()) {
                        winner = BattleParticipant.PLAYER;
                        this.endBattle();
                    } else {
                        opponent = opponentFighter.getFighter(opponentIndex);
                        battleObserver.setFighter(opponent);
                        return true;
                    }
                }
            }
            System.out.printf("%s is defeated!\n", defender.getName());  // just for testing purposes
        }
        return false;
    }

    public void switchAfterDefeated(int switchIndex) {
        if (!isCurrentFighterDefeated) return;
        isCurrentFighterDefeated = false;
        player = playerFighter.getFighter(switchIndex);
        battleObserver.setFighter(player);
    }


    /**
     * Ends the battle. The battle is currently ended by ending the program.
     */
    private void endBattle() {
        stateMachineObserver.update(ObserveType.BATTLE_END, new Object[]{isTrainerBattle, winner});
        isEnded = true;
    }
}

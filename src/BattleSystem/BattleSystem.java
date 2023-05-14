package BattleSystem;

@SuppressWarnings("FieldMayBeFinal")
public class BattleSystem {

    private Fighter player;
    private Fighter opponent;
    private int round = 1;  // just for testing purposes

    public BattleSystem(){
        this.player = new Fighter("Player",10, 6, 2, 5);
        this.opponent = new Fighter("Opponent",12, 5, 2, 7);
    }

    /**
    This method is the battle loop. It starts when a new BattleSystem is created and ends if the player flees
     or one fighter's HP drop to 0.
    */
    public void round(String action){
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
        System.out.println(defender.getHitpoints());  // just for testing purposes

        if(defender.isDefeated()){
            System.out.printf("%s is defeated! Battle ends now!", defender.getName());  // just for testing purposes
            this.endBattle();
        }
    }

    /**
    Ends the battle. The battle is currently ended by ending the program.
    */
    private void endBattle(){
        System.exit(0);
    }

}

package battle;

import java.util.Scanner;

@SuppressWarnings("FieldMayBeFinal")
public class BattleSystem {

    private Fighter player;
    private Fighter opponent;
    private Scanner scanner;

    public BattleSystem(){
        this.player = new Fighter("Player",10, 6, 2, 5);
        this.opponent = new Fighter("Opponent",12, 5, 2, 7);
        this.scanner = new Scanner(System.in);
        this.battle();
    }

    /**
    This method is the battle loop. It starts when a new BattleSystem is created and ends if the player flees
     or one fighter's HP drop to 0.
    */
    @SuppressWarnings("InfiniteLoopStatement")
    private void battle(){
        String chosenAction;
        int round = 1;  // just for testing purposes

        while(true){
            System.out.println("Round " + round);  // just for testing purposes
            chosenAction = actionInput();

            // decides whether the player or the opponent is faster with their action
            if(this.player.getInitStat() >= this.opponent.getInitStat()){
                this.playerAction(chosenAction);
                this.attacks(this.opponent, this.player);

            }else{
                this.attacks(this.opponent, this.player);
                this.playerAction(chosenAction);
            }
            round++;  // just for testing purposes
        }
    }

    /**
    This method reads in a user input. As long as there is no gui where the player can simply press a button,
     the action is read in via the console as a string. Accepted inputs are "attack" and "flee" at the moment.
    */
    private String actionInput(){
        String input;
        while(true) {
            System.out.println("Please input a valid battle action: ");
            input = this.scanner.next();
            if (input.equals("attack") || input.equals("flee")) return input;
        }
    }

    /**
    This method executes the actionInput that was either "attack" or "flee".
    */
    private void playerAction(String chosenAction){
        switch (chosenAction){
            case "attack": this.attacks(this.player, this.opponent); break;
            case "flee": this.player.flee();
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
        scanner.close();
        System.exit(0);
    }

}

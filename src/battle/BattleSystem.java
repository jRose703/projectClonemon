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

    @SuppressWarnings("InfiniteLoopStatement")
    private void battle(){
        String chosenAction;
        int round = 1;

        while(true){
            System.out.println("Round " + round);
            chosenAction = actionInput();

            if(this.player.getInitStat() >= this.opponent.getInitStat()){
                this.playerAction(chosenAction);
                this.attacks(this.opponent, this.player);

            }else{
                this.attacks(this.opponent, this.player);
                this.playerAction(chosenAction);
            }
            round++;
        }
    }

    private String actionInput(){
        String input;
        while(true) {
            System.out.println("Please input a valid battle action: ");
            input = this.scanner.next();
            if (input.equals("attack") || input.equals("flee")) return input;
        }
    }

    private void playerAction(String chosenAction){
        switch (chosenAction){
            case "attack": this.attacks(this.player, this.opponent); break;
            case "flee": this.player.flee();
            default: throw new IllegalStateException("Chosen action was not attack or flee!");
        }
    }

    private void attacks(Fighter attacker, Fighter defender) {
        attacker.attack(defender);
        System.out.println(defender.getHitpoints());

        if(defender.isDefeated()){
            System.out.printf("%s is defeated! Battle ends now!", defender.getName());
            this.endBattle();
        }
    }

    private void endBattle(){
        System.exit(0);
    }

}

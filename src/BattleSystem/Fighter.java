package BattleSystem;

import Frames.BattleUI.BattleParticipant;

import java.util.Random;

@SuppressWarnings("FieldMayBeFinal")
public class Fighter {

    private String name;
    private final int ID;
    private BattleParticipant battleParty;  // On which side is the fighter on
    private int hitpoints;
    private final int maxHitpoints;
    private int attackStat;
    private int defenseStat;
    private int initStat;
    private boolean isDefeated;

    public Fighter(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat) {
        this.name = name;
        this.battleParty = battleParty;
        this.ID = ID;
        this.hitpoints = maxHP;
        this.maxHitpoints = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.initStat = initStat;
        this.isDefeated = false;
    }

    /** This method lets the fighter attack.
     * The damage is the difference between the attackers attack and the defenders defense, but at least 1.
     * If the defenders HP drop to 0, it's marked as defeated.*/
    public void attack(Fighter defender){
        int damage = Math.max((this.attackStat - defender.defenseStat), 1);
        defender.hitpoints = Math.max(defender.hitpoints - damage, 0);
        if(defender.hitpoints == 0) defender.isDefeated = true;
    }

    /**
     * This method lets the fighter flee. It returns whether fleeing was successful or not.
     */
    public boolean flee() {
        Random random = new Random();
        int chance = random.nextInt(1, 100);
        System.out.println("The chance to flee was: " + chance + "%");
        return chance > 10;
    }

    public String getName() {
        return this.name;
    }

    public BattleParticipant getBattleParty() {
        return battleParty;
    }

    public int getID() {
        return ID;
    }

    public int getHitpoints() {
        return this.hitpoints;
    }

    public int getMaxHitpoints() {
        return this.maxHitpoints;
    }

    public int getInitStat() {
        return this.initStat;
    }

    public boolean isDefeated() {
        return this.isDefeated;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }
}

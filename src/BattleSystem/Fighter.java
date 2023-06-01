package BattleSystem;

import Frames.BattleUI.BattleParticipant;

@SuppressWarnings("FieldMayBeFinal")
public class Fighter {

    private String name;
    private final int ID;
    private BattleParticipant battleParty;
    private int hitpoints;
    private final int maxHitpoints;
    private int attackStat;
    private int defenseStat;
    private int initStat;
    private boolean defeated;

    public Fighter(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat) {
        this.name = name;
        this.battleParty = battleParty;
        this.ID = ID;
        this.hitpoints = maxHP;
        this.maxHitpoints = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.initStat = initStat;
        this.defeated = false;
    }

    /** This method lets the fighter attack.
     * The damage is the difference between the attackers attack and the defenders defense, but at least 1.
     * If the defenders HP drop to 0, it's marked as defeated.*/
    public void attack(Fighter defender){
        int damage = Math.max((this.attackStat - defender.defenseStat), 1);
        defender.hitpoints = Math.max(defender.hitpoints - damage, 0);
        if(defender.hitpoints == 0) defender.defeated = true;
    }

    /**
     * This method lets the fighter flee. Currently, it just ends the program.
     */
    public void flee() {
        System.exit(0);
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
        return this.defeated;
    }

}

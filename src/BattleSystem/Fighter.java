package BattleSystem;

import Frames.BattleUI.BattleParticipant;
import Entity.Item;
@SuppressWarnings("FieldMayBeFinal")
public class Fighter {

    private String name;
    private final int ID;
    private BattleParticipant battleParty;
    private int hitpoints;
    private final int maxHitpoints;
    private int attackStat;
    private int defenseStat;
    private String Type;
    private Item item;
    private int lvl;
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
    public Fighter(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat, String Type, Item item, int lvl) {
        this.name = name;
        this.battleParty = battleParty;
        this.ID = ID;
        this.hitpoints = maxHP;
        this.maxHitpoints = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.initStat = initStat;
        this.defeated = false;
        this.Type = Type;
        if (lvl<=100 && lvl >0){
            this.lvl = lvl;
        }
        else{
            this.lvl = 1;
        }
        this.item = item;
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

    public String getType() {
        return Type;
    }

    public Entity.Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public void heal(){
        this.hitpoints = maxHitpoints;
    }
    public void heal(int amount){
        if (amount<0){
            throw new IllegalArgumentException("Cannot heal negative amount");
        }
        if (this.hitpoints + amount<=maxHitpoints){
            this.hitpoints += amount;
        }
        else{
            this.hitpoints = maxHitpoints;
        }
    }
    public void increaseLvl(){
        if (this.lvl<100){
            this.lvl++;
            //TODO: increase hp, max_hp, reset xp (or not ( if we check for certain xp borders))
        }
        else{
            throw new IllegalArgumentException("Clonemon already has maximum lvl");
        }
    }

}

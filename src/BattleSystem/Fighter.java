package BattleSystem;

import Frames.BattleUI.BattleParticipant;
import Entity.Item;
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
    private String Type;
    private Item item;
    private int lvl;
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
    public Fighter(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat, String Type, Item item, int lvl) {
        this.name = name;
        this.battleParty = battleParty;
        this.ID = ID;
        this.hitpoints = maxHP;
        this.maxHitpoints = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.initStat = initStat;
        this.isDefeated = false;
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
            //TODO: increase hp, max_hp, attack, defense
        }
        else{
            throw new IllegalArgumentException("Clonemon already has maximum lvl");
        }
    }

}

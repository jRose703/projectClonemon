package BattleSystem;

import Frames.BattleUI.BattleParticipant;

import javax.swing.*;
import java.util.Random;

@SuppressWarnings("FieldMayBeFinal")
public abstract class Fighter {

    private String name;
    // Sprites
    protected ImageIcon backSprite;
    private FightingType type;
    private BattleParticipant battleParty;  // On which side is the fighter on
    private int ID;
    private boolean isDefeated;
    private final int maxHitpoints;
    private int attackStat;
    private int defenseStat;
    private int initStat;
    // Stats
    private int hitpoints;
    private ImageIcon frontSprite;

    public Fighter(String name, FightingType type, int ID, BattleParticipant battleParty,
                   int maxHP, int attackStat, int defenseStat, int initStat) {
        this.name = name;
        this.type = type;
        this.ID = ID;
        this.battleParty = battleParty;
        this.isDefeated = false;

        this.hitpoints = maxHP;
        this.maxHitpoints = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.initStat = initStat;
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

    protected void setSprites(ImageIcon backSprite, ImageIcon frontSprite) {
        this.backSprite = backSprite;
        this.frontSprite = frontSprite;
    }

    public ImageIcon getBackSprite() {
        return backSprite;
    }

    public ImageIcon getFrontSprite() {
        return frontSprite;
    }

    public String getName() {
        return this.name;
    }

    public FightingType getType() {
        return type;
    }

    public int getID() {
        return this.ID;
    }

    public BattleParticipant getBattleParty() {
        return battleParty;
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

}

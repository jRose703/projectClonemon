package BattleSystem;

import Frames.BattleUI.BattleParticipant;

import javax.swing.*;
import java.util.Random;

@SuppressWarnings("FieldMayBeFinal")
public abstract class Fighter {

    private String name;

    // Sprites
    private String backSprite;
    private FightingType type;
    private BattleParticipant battleParty;  // On which side is the fighter on
    private final int ID;
    private boolean isDefeated;
    private final int maxHitpoints;
    private int attackStat;
    private int defenseStat;
    private int initStat;

    // Stats
    private int hitpoints;
    private String frontSprite;

    public Fighter(String name, int ID, BattleParticipant battleParty,
                   int maxHP, int attackStat, int defenseStat, int initStat) {
        this.name = name;
        this.ID = ID;
        this.battleParty = battleParty;
        this.isDefeated = false;

        this.hitpoints = maxHP;
        this.maxHitpoints = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.initStat = initStat;
    }

    /**
     * This method lets the fighter being attacked.
     * The damage is being calculated in the DamageCalculation class.
     * If the defenders HP drop to 0, it's marked as defeated.
     */
    public void inflictDamage(int damage) {
        this.hitpoints = Math.max(this.hitpoints - damage, 0);
        if (this.hitpoints == 0) this.isDefeated = true;
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

    protected void setSprites(String backSprite, String frontSprite) {
        this.backSprite = backSprite;
        this.frontSprite = frontSprite;
    }

    public ImageIcon getBackSprite() {
        return new ImageIcon(backSprite);
    }

    public ImageIcon getFrontSprite() {
        return new ImageIcon(frontSprite);
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

    public int getAttackStat() {
        return attackStat;
    }

    public int getDefenseStat() {
        return defenseStat;
    }

    public int getInitStat() {
        return this.initStat;
    }

    public boolean isDefeated() {
        return this.isDefeated;
    }

    public void setType(FightingType type) {
        this.type = type;
    }
}

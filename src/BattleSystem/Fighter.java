package BattleSystem;

import BattleSystem.enums.FightingSide;
import BattleSystem.enums.FightingType;

import javax.swing.*;
import java.util.Random;

@SuppressWarnings("FieldMayBeFinal")
public abstract class Fighter {

    private final int maxHitpoints;
    private final int ID;
    // Basic Info
    private String name;
    private FightingType type;
    private boolean isDefeated;

    // Sprites
    private String backSprite;
    private FightingSide fightingSide;  // On which side is the fighter on

    // Stats
    private int hitpoints;
    private String frontSprite;
    private int attackStat;
    private int defenseStat;
    private int initStat;

    public Fighter(String name, int ID, FightingSide fightingSide,
                   int maxHP, int attackStat, int defenseStat, int initStat) {
        this.name = name;
        this.ID = ID;
        this.fightingSide = fightingSide;
        this.isDefeated = false;

        this.hitpoints = maxHP;
        this.maxHitpoints = maxHP;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.initStat = initStat;
    }

    public void heal(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Cannot heal negative amount");
        if (amount >= 1000000) {
            revive(amount);
            return;
        }

        this.hitpoints = Math.min(this.hitpoints + amount, maxHitpoints);
        if (isDefeated && hitpoints == maxHitpoints) isDefeated = false;
    }

    public void revive(int reviveType) {
        if (reviveType == 1000000000) {
            this.hitpoints = maxHitpoints;
            this.isDefeated = false;
        } else if (reviveType == 1000000) {
            this.hitpoints = maxHitpoints / 2;
            this.isDefeated = false;
        } else
            throw new IllegalArgumentException("Wrong reviveType!");
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

    public boolean isDefeated() {
        return this.isDefeated;
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

    public FightingSide getFightingSide() {
        return fightingSide;
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

    public void setSprites(String backSprite, String frontSprite) {
        this.backSprite = backSprite;
        this.frontSprite = frontSprite;
    }

    public void setType(FightingType type) {
        this.type = type;
    }

}

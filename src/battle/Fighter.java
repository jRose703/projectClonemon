package battle;

@SuppressWarnings("FieldMayBeFinal")
public class Fighter {

    private String name;
    private int hitpoints;
    private int attackStat;
    private int defenseStat;
    private int initStat;
    private boolean defeated;

    public Fighter(String name, int hitpoints, int attackStat, int defenseStat, int initStat){
        this.name = name;
        this.hitpoints = hitpoints;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.initStat = initStat;
        this.defeated = false;
    }

    public void attack(Fighter defender){
        int damage = Math.max((this.attackStat - defender.defenseStat), 1);
        defender.hitpoints = Math.max(defender.hitpoints - damage, 0);
        if(defender.hitpoints == 0) defender.defeated = true;
    }

    public void flee(){
        System.exit(0);
    }

    public String getName(){
        return this.name;
    }

    public int getHitpoints(){
        return this.hitpoints;
    }

    public int getInitStat() {
        return this.initStat;
    }

    public boolean isDefeated(){
        return this.defeated;
    }

}

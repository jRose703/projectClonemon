package BattleSystem.Fighters;

import BattleSystem.Fighter;
import BattleSystem.enums.FightingSide;
import BattleSystem.enums.FightingType;

public class Exorcist extends Fighter {
    public Exorcist(String name, int ID, FightingSide fightingSide, int maxHP, int attackStat, int defenseStat, int initStat) {
        super(name, ID, fightingSide, maxHP, attackStat, defenseStat, initStat);

        FightingType firstType = FightingType.EXORCIST;
        super.setType(firstType);

        String backSprite = "assets/fighters/backsprites/exorcist_back.png";
        String frontSprite = "assets/fighters/frontsprites/exorcist_front.png";
        super.setSprites(backSprite, frontSprite);
    }
}

package BattleSystem.Fighters;

import BattleSystem.Fighter;
import BattleSystem.FightingType;
import Frames.BattleUI.BattleParticipant;

public class Undead extends Fighter {
    public Undead(String name, int ID, BattleParticipant battleParty, int maxHP, int attackStat, int defenseStat, int initStat) {
        super(name, ID, battleParty, maxHP, attackStat, defenseStat, initStat);

        FightingType firstType = FightingType.UNDEAD;
        super.setType(firstType);

        String backSprite = "assets/fighters/backsprites/undead_back.png";
        String frontSprite = "assets/fighters/frontsprites/undead_front.png";
        super.setSprites(backSprite, frontSprite);
    }
}

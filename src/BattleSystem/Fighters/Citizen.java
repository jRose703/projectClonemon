package BattleSystem.Fighters;

import BattleSystem.Fighter;
import BattleSystem.FightingType;
import Frames.BattleUI.BattleParticipant;

public class Citizen extends Fighter {
    public Citizen(String name, int ID, BattleParticipant battleParty, int maxHP, int attackStat, int defenseStat, int initStat) {
        super(name, ID, battleParty, maxHP, attackStat, defenseStat, initStat);

        FightingType firstType = FightingType.CITIZEN;
        super.setType(firstType);

        String backSprite = "assets/fighters/backsprites/citizen_back.png";
        String frontSprite = "assets/fighters/frontsprites/citizen_front.png";
        super.setSprites(backSprite, frontSprite);
    }

}

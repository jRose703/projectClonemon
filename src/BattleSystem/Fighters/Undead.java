package BattleSystem.Fighters;

import BattleSystem.Fighter;
import BattleSystem.FightingType;
import Frames.BattleUI.BattleParticipant;

import javax.swing.*;

public class Undead extends Fighter {

    public Undead(String name, FightingType type, int ID, BattleParticipant battleParty, int maxHP, int attackStat, int defenseStat, int initStat) {
        super(name, type, ID, battleParty, maxHP, attackStat, defenseStat, initStat);

        String backSprite = "assets/fighters/backsprites/undead_back.png";
        String frontSprite = "assets/fighters/frontsprites/undead_front.png";
        super.setSprites(backSprite, frontSprite);
    }
}

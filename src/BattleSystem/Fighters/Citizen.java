package BattleSystem.Fighters;

import BattleSystem.Fighter;
import BattleSystem.FightingType;
import Frames.BattleUI.BattleParticipant;

import javax.swing.*;

public class Citizen extends Fighter {
    public Citizen(String name, FightingType type, int ID, BattleParticipant battleParty, int maxHP, int attackStat, int defenseStat, int initStat) {
        super(name, type, ID, battleParty, maxHP, attackStat, defenseStat, initStat);

        ImageIcon backSprite = new ImageIcon("assets/fighters/backsprites/citizen_back.png");
        ImageIcon frontSprite = new ImageIcon("assets/fighters/frontsprites/citizen_front.png");
        super.setSprites(backSprite, frontSprite);
    }

}

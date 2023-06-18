package BattleSystem.Fighters;

import BattleSystem.Fighter;
import Frames.BattleUI.BattleParticipant;

import javax.swing.*;

public class Water extends Fighter {
    private ImageIcon BackSprite;
    private ImageIcon FrontSprite;
    public Water(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat){
        super(name, battleParty, ID, maxHP, attackStat, defenseStat,initStat);
        this.BackSprite = new ImageIcon("assets/Fighter/water_back.png");;
        this.FrontSprite = new ImageIcon("assets/Fighter/water_front.png");;
    }
}

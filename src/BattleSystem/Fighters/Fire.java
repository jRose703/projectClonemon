package BattleSystem.Fighters;

import BattleSystem.Fighter;
import Frames.BattleUI.BattleParticipant;

import javax.swing.*;

public class Fire extends Fighter {
    private ImageIcon BackSprite;
    private ImageIcon FrontSprite;
    public Fire(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat){
        super(name, battleParty, ID, maxHP, attackStat, defenseStat,initStat);
        this.BackSprite = new ImageIcon("assets/Fighter/fire_back.png");
        this.FrontSprite = new ImageIcon("assets/Fighter/fire_front.png");
    }
}

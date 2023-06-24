package BattleSystem.Fighters;

import BattleSystem.Fighter;
import Frames.BattleUI.BattleParticipant;

import javax.swing.*;

public class Grass extends Fighter {
    private ImageIcon BackSprite;
    private ImageIcon FrontSprite;
    public Grass(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat){
        super(name, battleParty, ID, maxHP, attackStat, defenseStat,initStat);
        this.BackSprite = new ImageIcon("assets/Fighter/grass_back.png");
        this.FrontSprite = new ImageIcon("assets/Fighter/grass_front.png");
    }
}

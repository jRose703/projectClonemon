package BattleSystem.Fighters;

import BattleSystem.Fighter;
import Frames.BattleUI.BattleParticipant;

public class Water extends Fighter {
    private imageIcon BackSprite;
    private imageIcon FrontSprite;
    public Water(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat, imageIcon backSprite, imageIcon fronSprite){
        super(name, battleParty, ID, maxHP, attackStat, defenseStat,initStat);
        this.BackSprite = backSprite;
        this.FrontSprite = fronSprite;
    }
}

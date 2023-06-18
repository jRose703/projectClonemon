package BattleSystem.Fighters;

import BattleSystem.Fighter;
import Frames.BattleUI.BattleParticipant;

public class Fire extends Fighter {
    private imageIcon BackSprite;
    private imageIcon FrontSprite;
    public Fire(String name, BattleParticipant battleParty, int ID, int maxHP, int attackStat, int defenseStat, int initStat, imageIcon backSprite, imageIcon fronSprite){
        super(name, battleParty, ID, maxHP, attackStat, defenseStat,initStat);
        this.BackSprite = backSprite;
        this.FrontSprite = fronSprite;
    }
}

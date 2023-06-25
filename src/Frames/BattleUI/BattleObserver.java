package Frames.BattleUI;

import BattleSystem.Fighter;
import BattleSystem.FightingSide;

public interface BattleObserver {

    public void updateHitpoints(FightingSide defender, int newHitpoints);

    public void setFighter(Fighter fighter);

    public void showFighterInventoryUI();

}

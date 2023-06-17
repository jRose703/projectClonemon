package Frames.BattleUI;

import BattleSystem.Fighter;

public interface BattleObserver {

    public void updateHitpoints(BattleParticipant defender, int newHitpoints);

    public void setFighter(Fighter fighter);

}

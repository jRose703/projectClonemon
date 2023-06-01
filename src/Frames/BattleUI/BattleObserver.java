package Frames.BattleUI;

import BattleSystem.Fighter;

public interface BattleObserver {

    public void updateHitpointBar(BattleParticipant defender, int newHitpoints);

    public void setFighter(Fighter fighter);

}

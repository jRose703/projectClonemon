import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import BattleSystem.Fighters.Exorcist;
import BattleSystem.Fighters.Undead;
import BattleSystem.enums.BattleAction;
import BattleSystem.enums.FightingSide;
import Entity.FighterInventory;
import Frames.BattleUI.BattleObserver;
import Observer.ObserveType;
import Observer.Observer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleSystemTest {

    private final BattleSystem battle;
    private final FighterInventory player;
    private final FighterInventory enemy;
    private ObserveType observeType;

    public BattleSystemTest() {
        Observer observer = (t, o) -> observeType = t;
        BattleObserver battleObserver = new BattleObserver() {
            public void updateHitpoints(FightingSide defender, int newHitpoints) {
            }

            public void setFighter(Fighter fighter) {
            }

            public void showFighterInventoryUI() {
            }
        };

        player = new FighterInventory();
        player.addToFighterInventory(new Exorcist("PlayerOne", 0, FightingSide.PLAYER,
                10, 5, 2, 5));

        enemy = new FighterInventory();
        enemy.addToFighterInventory(new Undead("OpponentOne", 6, FightingSide.OPPONENT,
                20, 2, 2, 7));

        battle = new BattleSystem(observer, battleObserver, player, enemy, false);
    }

    @Test
    public void updatesCorrectObserveType() {
        battle.round(BattleAction.RUN);
        battle.round(BattleAction.RUN);
        battle.round(BattleAction.RUN);
        battle.round(BattleAction.RUN);
        battle.round(BattleAction.RUN);
        assertEquals(ObserveType.BATTLE_END, observeType);
    }

    @Test
    public void damageCalculationIsCorrect() {
        battle.round(BattleAction.FIGHT);
        assertEquals(9, player.getFighter(0).getHitpoints());
        if (enemy.getFighter(0).getHitpoints() < 10)
            assertEquals(2, enemy.getFighter(0).getHitpoints());
        else
            assertEquals(12, enemy.getFighter(0).getHitpoints());
    }

}

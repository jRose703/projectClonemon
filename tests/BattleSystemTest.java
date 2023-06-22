import BattleSystem.BattleSystem;
import BattleSystem.Fighter;
import BattleSystem.Fighters.Exorcist;
import BattleSystem.Fighters.Undead;
import BattleSystem.FightingType;
import Entity.FighterInventory;
import Frames.BattleUI.BattleObserver;
import Frames.BattleUI.BattleParticipant;
import Observer.ObserveType;
import Observer.Observer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BattleSystemTest {

    private final BattleSystem battle;
    private FighterInventory player;
    private FighterInventory enemy;
    private ObserveType observeType;
    public BattleSystemTest(){
        Observer observer = (t, o) -> observeType = t;
        BattleObserver battleObserver = new BattleObserver() {
            public void updateHitpoints(BattleParticipant defender, int newHitpoints) {
            }

            public void setFighter(Fighter fighter) {
            }

            public void showFighterinventoryUI() {
            }
        };

        player = new FighterInventory();
        player.addToFighterInventory(new Exorcist("PlayerOne", FightingType.EXORCIST, 0, BattleParticipant.PLAYER, 10, 5, 2, 5));

        enemy = new FighterInventory();
        enemy.addToFighterInventory(new Undead("OpponentOne", FightingType.UNDEAD, 6, BattleParticipant.OPPONENT, 17, 2, 2, 7));

        battle = new BattleSystem(observer, battleObserver, player, enemy);
    }

    // TODO: This Test can legally fail if the flee action was not successful
    @Test
    public void updatesCorrectObserveType(){
        battle.round("run");
        battle.round("run");
        battle.round("run");
        battle.round("run");
        battle.round("run");
        assertEquals(ObserveType.BATTLE_END, observeType);
    }

    @Test
    public void damageCalculationIsCorrect(){
        battle.round("fight");
        assertEquals(9, player.getFighter(0).getHitpoints());
        assertEquals(14, enemy.getFighter(0).getHitpoints());
    }

    @Test
    public void throwsCorrectErrorInvalidAction(){
        try{
            battle.round("tomato");
        }catch (IllegalStateException e){
            System.out.println(e + "\nCorrect exception was caught!");
        }catch (Exception e){
            fail();
        }
    }

}

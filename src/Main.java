import BattleSystem.Fighter;
import Entity.FighterInventory;
import Entity.PlayerEntity;
import Frames.BattleUI.BattleParticipant;
import ReadAndWrite.ReadWritePlayer.ReadPlayerFromJson;
import ReadAndWrite.ReadWritePlayer.WritePlayerToJson;

public class Main {

    public static void main(String[] args) {

        /*
        PlayerEntity player = new PlayerEntity();
        Fighter f1 = new Fighter("Catmon", BattleParticipant.PLAYER, 0, 100, 10,5,0);
        Fighter f2 = new Fighter("Lionmon", BattleParticipant.PLAYER, 0, 100, 10,5,0);
        FighterInventory fighterInventory = new FighterInventory();
        fighterInventory.addToFighterInventory(f1);
        fighterInventory.addToFighterInventory(f2);
        player.setPlayerFighters(fighterInventory);
        WritePlayerToJson.savePlayer(player,"player");
        PlayerEntity savedPlayer = ReadPlayerFromJson.readPlayerFromFile("player");
        */

        StateMachine stateMachine = new StateMachine();
    }
}

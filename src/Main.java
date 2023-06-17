import Entity.PlayerEntity;
import ReadAndWrite.ReadWritePlayer.ReadPlayerFromJson;
import ReadAndWrite.ReadWritePlayer.WritePlayerToJson;

public class Main {

    public static void main(String[] args) {
        PlayerEntity player = new PlayerEntity();
        WritePlayerToJson.savePlayer(player,"player");
        PlayerEntity savedPlayer = ReadPlayerFromJson.readPlayerFromFile("player");

        StateMachine stateMachine = new StateMachine();
    }
}

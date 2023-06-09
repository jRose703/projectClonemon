import Entity.PlayerEntity;
import Entity.ReadWritePlayer.WritePlayerToJson;

public class Main {

    public static void main(String[] args) {
        PlayerEntity player = new PlayerEntity();
        WritePlayerToJson.savePlayer(player,"player");


        StateMachine stateMachine = new StateMachine();
    }
}

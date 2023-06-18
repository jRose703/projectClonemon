package Entity;

public class OpponentEntity extends Entity {
    private String entityType = "OpponentEntity";
    private boolean status = false;
    private FighterInventory fighterInventory;
    public OpponentEntity(){}
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    public void setFighterInventory(FighterInventory fighterInventory) {
        this.fighterInventory = fighterInventory;
    }

    public FighterInventory getFighterInventory() {
        return fighterInventory;
    }

}

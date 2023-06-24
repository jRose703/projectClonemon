package Entity;

public class OpponentEntity extends Entity {
    private InteractionType interactionType = InteractionType.BATTLE;
    private FighterInventory fighterInventory;
    private String message = "Hello World!";
    private String entityType = "OpponentEntity";

    public OpponentEntity() {
    }

    public InteractionType getInteractionType() {
        return this.interactionType;
    }

    public FighterInventory getFighterInventory() {
        return fighterInventory;
    }

    public String getMessage() {
        return message;
    }

    public void setInteractionType(InteractionType interactionType) {
        this.interactionType = interactionType;
    }

    public void setFighterInventory(FighterInventory fighterInventory) {
        this.fighterInventory = fighterInventory;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

}

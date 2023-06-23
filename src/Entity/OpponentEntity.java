package Entity;

import Frames.TextBox.DialogueType;

public class OpponentEntity extends Entity {
    private String message;
    private String entityType = "OpponentEntity";
    private DialogueType dialogueType = DialogueType.BATTLE;
    private FighterInventory fighterInventory;
    public OpponentEntity(DialogueType dialogueType, String message) {
        this.dialogueType = dialogueType;
        this.message = message;
    }
    public void setDialogueType(DialogueType dialogueType) {
        this.dialogueType = dialogueType;
    }
    public DialogueType getDialogueType(){
        return this.dialogueType;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

package Entity;

import Frames.TextBox.DialogueType;

public class OpponentEntity extends Entity {
    private final String entityType = "OpponentEntity";
    private DialogueType dialogueType;
    private String message;

    public OpponentEntity(DialogueType dialogueType, String message) {
        this.dialogueType = dialogueType;
        this.message = message;
    }

    public DialogueType getDialogueType() {
        return dialogueType;
    }

    public void setDialogueType(DialogueType dialogueType) {
        this.dialogueType = dialogueType;
    }

    public String getMessage() {
        return message;
    }

    //TODO delete this method as soon as entities save message and dialogue type
    public void setMessage(String message) {
        this.message = message;
    }
}

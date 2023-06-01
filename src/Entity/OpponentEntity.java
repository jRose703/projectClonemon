package Entity;

public class OpponentEntity extends Entity {
    private String entityType = "OpponentEntity";
    public boolean status = false;
    public OpponentEntity(){}
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
}

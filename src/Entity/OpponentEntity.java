package Entity;

public class OpponentEntity extends Entity {
    public boolean status = false;
    public OpponentEntity(){}

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return this.status;
    }

    public boolean check(){
        //TODO
        return false;
    }


}

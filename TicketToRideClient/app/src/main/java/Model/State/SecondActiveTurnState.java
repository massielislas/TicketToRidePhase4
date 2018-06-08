package Model.State;

import Model.InGameModels.TrainCard;
import Model.TicketToRideProxy;
import Model.UserData;
import Results.Result;

/**
 * Created by Topper on 6/4/2018.
 */

public class SecondActiveTurnState extends TurnState {
    @Override
    public boolean canDrawFaceDownCard() {
        if(UserData.getUserData().getCurrentGame().getFaceDownTrainDeck().size() < 1){
            return false;
        }
        return true;
    }

    @Override
    public boolean canDrawFaceUpCard(TrainCard trainCard) {
        if(trainCard.getColor().equals("gray")){
            return false;
        }
        if(UserData.getUserData().getCurrentGame().getFaceUpTrainDeck().length < 1){
            return false;
        }
        return true;
    }
    @Override
    public Result drawFaceUpCard(TrainCard trainCard) {
        Result toReturn;
        if(!canDrawFaceUpCard(trainCard)){
            return new Result(false, "No cards left! Your turn is over!");
        }
        else {
            toReturn = new TicketToRideProxy().chooseFaceUpCard(UserData.getUserData().getUsername().getNameOrPassword(),
                    UserData.getUserData().getCurrentGame().getID(),Double.valueOf(trainCard.getID()));
            if(toReturn.isSuccess()){
                MyState.getInstance().state = new NonActiveTurnState();
                new TicketToRideProxy().endTurn(UserData.getUserData().getUsername().getNameOrPassword(),
                        UserData.getUserData().getCurrentGame().getID());
            }
            return toReturn;
        }
    }

    @Override
    public Result drawFaceDownCard() {
        if(!canDrawFaceDownCard()){
            MyState.getInstance().state = new NonActiveTurnState();
            new TicketToRideProxy().endTurn(UserData.getUserData().getUsername().getNameOrPassword(),
                    UserData.getUserData().getCurrentGame().getID());
            return new Result(true, "No cards left! Your turn is over!");
        }
        else{
            Result fromProxy = new TicketToRideProxy().drawFromTrainDeck(UserData.getUserData().getUsername().getNameOrPassword(),
                    UserData.getUserData().getCurrentGame().getID());
            if(fromProxy.isSuccess()) {
                MyState.getInstance().state = new NonActiveTurnState();
                new TicketToRideProxy().endTurn(UserData.getUserData().getUsername().getNameOrPassword(),
                        UserData.getUserData().getCurrentGame().getID());
            }
            return fromProxy;
        }
    }
}

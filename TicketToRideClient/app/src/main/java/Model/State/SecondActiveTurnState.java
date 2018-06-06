package Model.State;

import Model.InGameModels.TrainCard;
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
        if(!canDrawFaceUpCard(trainCard)){
            return new Result(false, "CANT DO IT!");
        }
        else {
            UserData.getUserData().getCurrentPlayer().getTrainCards().add(trainCard);
            MyState.getInstance().state = new NonActiveTurnState();
            return new Result(true, "good to go!");
        }
    }

    @Override
    public Result drawFaceDownCard() {
        if(!canDrawFaceDownCard()){
            return new Result(false, "NO CAN DO!");
        }
        else{
            //todo: replace drawnCard from TTRProxy method!
            TrainCard drawnCard = null;
            UserData.getUserData().getCurrentPlayer().getTrainCards().add(drawnCard);
            MyState.getInstance().state = new NonActiveTurnState();
            return new Result(true,drawnCard.getColor());
        }
    }
}

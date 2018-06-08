package Model.State;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;
import Results.Result;

/**
 * Created by Topper on 6/4/2018.
 */

public class TurnState {
    public boolean canClaimRoute(int ID){
        return false;
    }
    public boolean canDrawFaceUpCard(TrainCard trainCard){
        return false;
    }
    public boolean canDrawFaceDownCard(){
        return false;
    }
    public boolean canDrawDestinationCards(){
        return false;
    }
    public Result claimRoute(int ID){
        return new Result(false, "FAIL");
    }
    public Result drawFaceUpCard(TrainCard trainCard){
        return new Result(false, "FAIL");
    }
    public Result drawFaceDownCard(){
        return new Result(false, "FAIL");
    }
    public Result drawDestinationCards(){
        return new Result(false, "FAIL");
    }
}

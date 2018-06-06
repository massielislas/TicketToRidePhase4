package Model.State;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;
import Results.Result;

/**
 * Created by Topper on 6/4/2018.
 */

public class MyState {
    TurnState state;
    public MyState(){
        state= new NonActiveTurnState();
    }

    public boolean canClaimRoute(Route route){
        return  state.canClaimRoute(route);
    }
    public boolean canDrawFaceUpCard(TrainCard trainCard){
        return state.canDrawFaceUpCard(trainCard);
    }
    public boolean canDrawFaceDownCard(){
        return state.canDrawFaceDownCard();
    }
    public boolean canDrawDestinationCards(){
        return state.canDrawDestinationCards();
    }
    public Result ClaimRoute(Route route){
        return state.claimRoute(route);
    }
    public Result DrawFaceUpCard(TrainCard trainCard){
        return state.drawFaceUpCard(trainCard);
    }
    public Result drawFaceDownCard(){
        return state.drawFaceDownCard();
    }
    public DestinationCard[] drawDestinationCards(){
        return state.drawDestinationCards();
    }
    public void activateTurn(){
        state = new FirstActiveTurnState();
    }
}

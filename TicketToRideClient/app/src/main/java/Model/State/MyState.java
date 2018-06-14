package Model.State;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;
import Results.Result;

/**
 * Created by Topper on 6/4/2018.
 */

public class MyState {
    public TurnState state;
    public static MyState getInstance(){
        return instance;
    }
    private static MyState instance = new MyState();
    private MyState(){
        state= new NonActiveTurnState();
    }

    public boolean canClaimRoute(int ID){
        return  state.canClaimRoute(ID);
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
    public Result ClaimRoute(int ID){
        return state.claimRoute(ID);
    }
    public Result DrawFaceUpCard(TrainCard trainCard){
        return state.drawFaceUpCard(trainCard);
    }
    public Result drawFaceDownCard(){
        return state.drawFaceDownCard();
    }
    public Result drawDestinationCards(){
        return state.drawDestinationCards();
    }
    public void activateTurn(){
        state = new FirstActiveTurnState();
    }
}

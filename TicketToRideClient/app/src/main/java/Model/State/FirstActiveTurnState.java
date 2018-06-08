package Model.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;
import Model.TicketToRideProxy;
import Model.UserData;
import Results.Result;

/**
 * Created by Topper on 6/4/2018.
 */

public class FirstActiveTurnState extends TurnState {
    @Override
    public boolean canClaimRoute(Route route) {
        if(route.isClaimed()){
            return false;
        }
        List<TrainCard> trainCards = UserData.getUserData().getCurrentPlayer().getTrainCards();
        String routeColor = route.getColor();
        int numberOfRightColor = 0;
        for(TrainCard t:trainCards){
            if(t.getColor() == routeColor || t.getColor() == "gray"){
                numberOfRightColor++;
            }
        }
        if(numberOfRightColor < route.getLength()){
            return false;
        }
        return true;
    }

    @Override
    public boolean canDrawDestinationCards() {
        if(UserData.getUserData().getCurrentGame().getDestDeckSize() == 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean canDrawFaceDownCard() {
        if(UserData.getUserData().getCurrentGame().getFaceDownTrainDeck().size() < 1){
            return false;
        }
        return true;
    }

    @Override
    public boolean canDrawFaceUpCard(TrainCard trainCard) {
        if(UserData.getUserData().getCurrentGame().getFaceUpTrainDeck().length < 1){
            return false;
        }
        return true;
    }

    @Override
    public Result claimRoute(Route route) {
        if(!canClaimRoute(route)){
            return new Result(false, "Cant claim route!");
        }
        Result toReturn = new TicketToRideProxy().claimRoute(UserData.getUserData().getUsername().getNameOrPassword(),
                UserData.getUserData().getCurrentGame().getID(),Double.valueOf(route.getID()));
        if(toReturn.isSuccess()) {
            MyState.getInstance().state = new NonActiveTurnState();
            Result endTurnResult = new TicketToRideProxy().endTurn(UserData.getUserData().getUsername().getNameOrPassword(),
                    UserData.getUserData().getCurrentGame().getID());
            if(!endTurnResult.isSuccess()){
                System.out.println("YOU DONE MESSED UP A-ARON!");
            }
        }
        return toReturn;
    }

    @Override
    public Result drawDestinationCards() {
        if(!canDrawDestinationCards()){
            return new Result(false, "cant do it!");
        }
        Result toReturn = new TicketToRideProxy().drawDestCards(UserData.getUserData().getUsername().getNameOrPassword(),
                UserData.getUserData().getCurrentGame().getID());
        if(toReturn.isSuccess()){
            MyState.getInstance().state = new NonActiveTurnState();
            //todo: We need to make sure that the person's turn ends when they discard
        }
        return toReturn;
    }

    @Override
    public Result drawFaceUpCard(TrainCard trainCard) {
        Result toReturn = null;
        if(!canDrawFaceUpCard(trainCard)){
            return new Result(false, "CANT DO IT!");
        }
        else if(!trainCard.getColor().equals("gray")){
            toReturn = new TicketToRideProxy().chooseFaceUpCard(UserData.getUserData().getUsername().getNameOrPassword(),
                    UserData.getUserData().getCurrentGame().getID(),Double.valueOf(trainCard.getID()));
            if(toReturn.isSuccess()){
                MyState.getInstance().state = new SecondActiveTurnState();
            }
            return toReturn;
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
            return new Result(false, "NO CAN DO!");
        }
        else{
            Result fromProxy = new TicketToRideProxy().drawFromTrainDeck(UserData.getUserData().getUsername().getNameOrPassword(),
                    UserData.getUserData().getCurrentGame().getID());
            if(fromProxy.isSuccess()) {
                MyState.getInstance().state = new SecondActiveTurnState();
            }
            return fromProxy;
        }
    }
}

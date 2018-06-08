package Model.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Route;
import Model.InGameModels.Routes;
import Model.InGameModels.TrainCard;
import Model.TicketToRideProxy;
import Model.UserData;
import Results.Result;

/**
 * Created by Topper on 6/4/2018.
 */

public class FirstActiveTurnState extends TurnState {
    @Override
    public boolean canClaimRoute(int ID) {
        Route route = new Routes().getRoute(ID);
        if(ID > 0) {
            //Is it claimed?
            if (route.isClaimed()) {
                return false;
            }
            List<TrainCard> trainCards = UserData.getUserData().getCurrentPlayer().getTrainCards();
            String routeColor = route.getColor();
            //Do you have enough pieces left?
            if (UserData.getUserData().getCurrentPlayer().getTrainPiecesLeft() < route.getLength()) {
                return false;
            }
            //Do you have the right color for it?
            if(routeColor.equals("gray")){
                if(getNumberOfColor("red", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("blue", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("yellow", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("green", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("black", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("orange", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("white", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("pink", trainCards) >= route.getLength()){
                    return true;
                }
            }
            if (getNumberOfColor(routeColor, trainCards) >= route.getLength()) {
                return true;
            }
        }
        else{
            //Its a double route!
            if (route.isDoubleClaimed()) {
                return false;
            }
            List<TrainCard> trainCards = UserData.getUserData().getCurrentPlayer().getTrainCards();
            String routeColor = route.getDoubleColor();
            if (UserData.getUserData().getCurrentPlayer().getTrainPiecesLeft() < route.getLength()) {
                return false;
            }
            if(routeColor.equals("gray")){
                if(getNumberOfColor("red", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("blue", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("yellow", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("green", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("black", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("orange", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("white", trainCards) >= route.getLength()){
                    return true;
                }
                if(getNumberOfColor("pink", trainCards) >= route.getLength()){
                    return true;
                }
            }
            if (getNumberOfColor(routeColor, trainCards) >= route.getLength()) {
                return true;
            }
        }
        return false;
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
    public Result claimRoute(int ID) {
        if(!canClaimRoute(ID)){
            return new Result(false, "Cant claim route!");
        }
        Result toReturn = new TicketToRideProxy().claimRoute(UserData.getUserData().getUsername().getNameOrPassword(),
                UserData.getUserData().getCurrentGame().getID(),Double.valueOf(ID));
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
    public int getNumberOfColor(String color, List<TrainCard> hand){
        int numberFound = 0;
        if(color.equals("gray")){

        }
        for (TrainCard t : hand) {
            if (t.getColor().equals(color) || t.getColor().equals("gray")) {
                numberFound = numberFound + 1;
            }
        }
        return numberFound;
    }
}

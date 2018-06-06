package Model.InGameModels.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;
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
        //Make sure that the Server returned that it gor recorded on the server side.
        //TicketToRideProxy.getInstance.claimRoute(...)
        String color = route.getColor();
        int numberNeeded = route.getLength();
        while(numberNeeded > 0) {
            boolean cardFound = false;
            for (TrainCard t : UserData.getUserData().getCurrentPlayer().getTrainCards()){
                //First Remove those with the right color
                if(t.getColor().equals(color)){
                    UserData.getUserData().getCurrentPlayer().getTrainCards().remove(t);
                    numberNeeded = numberNeeded - 1;
                    cardFound = true;
                    break;
                }
            }

            if(!cardFound){
                for (TrainCard t : UserData.getUserData().getCurrentPlayer().getTrainCards()) {
                    //Then, if needed, get the wilds!
                    if(t.getColor().equals("gray")){
                        UserData.getUserData().getCurrentPlayer().getTrainCards().remove(t);
                        numberNeeded = numberNeeded - 1;
                        break;
                    }
                }
            }
        }
        MyState.getInstance().state = new NonActiveTurnState();
        return new Result(true, "Route Claimed");
    }

    @Override
    public DestinationCard[] drawDestinationCards() {
        if(!canDrawDestinationCards()){
            return null;
        }
        //toDo: get the 3 cards from the TTRProxy
        DestinationCard[] toPickFrom = null;
        UserData.getUserData().getCurrentPlayer().addToDestinationHand(new ArrayList<>(Arrays.asList(toPickFrom)));
        MyState.getInstance().state = new NonActiveTurnState();
        return toPickFrom;
        //Note, the user will have to discard some of them, the same way as they did before.
    }

    @Override
    public Result drawFaceUpCard(TrainCard trainCard) {
        if(!canDrawFaceUpCard(trainCard)){
            return new Result(false, "CANT DO IT!");
        }
        else if(!trainCard.getColor().equals("gray")){
            UserData.getUserData().getCurrentPlayer().getTrainCards().add(trainCard);
            MyState.getInstance().state = new SecondActiveTurnState();
            return new Result(true, "good to go!");
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
            MyState.getInstance().state = new SecondActiveTurnState();
            return new Result(true,drawnCard.getColor());
        }
    }
}

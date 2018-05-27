package Model;

import java.util.ArrayList;
import java.util.Observer;

import Model.InGameModels.Chat;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.Player;
import Results.Result;

public class PlayFacade {

    TicketToRideProxy proxy = new TicketToRideProxy();
    Chat chat = Chat.getInstance();
    UserData userData = UserData.getUserData();
    SinglePlayerStartInfo info;

    public void addObserver(Observer o)
    {
        chat.addAnObserver(o);
        userData.getCurrentPlayer().addAnObserver(o);
    }

    public Result sendChat(String message)
    {
        return proxy.sendChat(userData.getUsername().getNameOrPassword(), message, userData.getCurrentGame().getID());
    }

    public Result selectCards(ArrayList<Integer> cards)
    {
        return proxy.selectCards(userData.getUsername().getNameOrPassword(), userData.getCurrentGame().getID(), cards);
    }

    public void addCards(ArrayList<Double> cards)
    {
        Game currentGame = userData.getCurrentGame();
        ArrayList<DestinationCard> toAdd = currentGame.getSelectedDestinationCards(cards);
        userData.getCurrentPlayer().addToDestinationHand(toAdd);
    }

    public void addChat(String message)
    {
        chat.addChatMessage(message);
    }

    public void setStartInfo(SinglePlayerStartInfo info)
    {
        this.info = info;
        String color = "-1"; //it will get changed
        switch(info.getTurnNumber()) //set color
        {
            case 1:{
                color = "Blue";
                break;
            }
            case 2:{
                color = "Yellow";
                break;
            }
            case 3:{
                color = "Green";
                break;
            }
            case 4:{
                color = "Red";
                break;
            }
            case 5:{
                color = "Purple";
                break;
            }
        }

        Player player = new Player(userData.getUsername(), info.getTurnNumber(), color);
        player.setTrainCards(info.getStartingTrainCards());
        player.setToChoose(info.getStartingDestCards());
        userData.setCurrentPlayer(player);
    }

    public SetUpData getSetUpData ()
    {
        SetUpData data = new SetUpData();
        data.setColor(userData.getCurrentPlayer().getColor());
        data.setTurnNumber(userData.getCurrentPlayer().getTurnNumber());
        data.setStartingTrainCards(userData.getCurrentPlayer().getTrainCards());
        data.setStartingDestCards(userData.getCurrentPlayer().getToChoose());
        return data;
    }

    public BoardData getBoardData()
    {
        BoardData data = new BoardData();
        data.setDestDeckSize(userData.getCurrentGame().getDestinationDeck().size());
        data.setTrainDeckSize(userData.getCurrentGame().getFaceDownTrainDeck().size());
        data.setOtherPlayerInfo();
    }

}

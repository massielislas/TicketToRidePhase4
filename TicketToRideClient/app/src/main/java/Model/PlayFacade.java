package Model;

import java.util.ArrayList;

import Results.Result;

public class PlayFacade {

    TicketToRideProxy proxy = new TicketToRideProxy();
    Chat chat = Chat.getInstance();
    UserData userData = UserData.getUserData();

    public void addObserver(Object o)
    {
        chat.addObserver(o);
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
}

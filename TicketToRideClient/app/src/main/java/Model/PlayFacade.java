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
        switch(info.getTurnNumber()) //set color
        {
            String color;
            case 1:{

            }
            case 2:{

            }
            case 3:{

            }
            case 4:{

            }
            case 5:{

            }
        }

        Player player = new Player(userData.getUsername(), info.getTurnNumber(), color);
        player.setTrainCards(info.getStartingTrainCards());
        userData.setCurrentPlayer(player);
    }

    public SinglePlayerStartInfo getStartInfo ()
    {
        return info;
    }

}

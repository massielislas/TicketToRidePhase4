package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import Communication.Encoder;
import Model.InGameModels.Chat;
import Model.InGameModels.Cities;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.DestinationCardDeck;
import Model.InGameModels.Player;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Routes;
import Model.InGameModels.TrainCard;
import Model.InGameModels.TrainCardDeck;
import Results.Result;

public class PlayFacade {

    private static final PlayFacade instance = new PlayFacade();

    public static PlayFacade getInstance() {return instance;}

    TicketToRideProxy proxy;
    Chat chat;
    UserData userData;
    Cities cities;
    Routes routes;
    DestinationCardDeck destCardDeck;
    SinglePlayerStartInfo info;
    BoardData boardData;
    SetUpData setUpData;
    TrainCardDeck trainCardDeck;

    private PlayFacade()
    {
        trainCardDeck = new TrainCardDeck();
        setUpData = new SetUpData();
        boardData = new BoardData();
        destCardDeck = new DestinationCardDeck();
        routes = new Routes();
        cities = Cities.getInstance();
        userData = UserData.getUserData();
        chat = Chat.getInstance();
        proxy = new TicketToRideProxy();
    }

    public void addSetUpObserver(Observer o)
    {
        //chat.addAnObserver(o);
        setUpData.addAnObserver(o);
    }

    public void addBoardObserver(Observer o)
    {
        chat.addAnObserver(o);
        boardData.addObserver(o);
        setBoardData();
    }

    public void deRegisterSetUpObserver(Observer o)
    {
        //chat.removeAnObserver(o);
        setUpData.removeAnObserver(o);
    }

    public void deRegisterBoardObserver(Observer o)
    {
        chat.removeAnObserver(o);
        boardData.removeAnObserver(o);
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

    public void updateFaceUpCards(ArrayList<TrainCard> cards)
    {
        userData.getCurrentGame().setFaceUpTrainDeck(cards);
    }

    public void addChat(String message)
    {
        chat.addChatMessage(message);
    }

    public void setStartInfo(String jsonString){
        SinglePlayerStartInfo fromGson = (SinglePlayerStartInfo) Encoder.Decode(jsonString,SinglePlayerStartInfo.class);
        setStartInfo(fromGson);
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
        userData.getCurrentGame().setCities(cities.getCityList());
        userData.getCurrentGame().setRoutes(routes.getRouteList());
        userData.getCurrentGame().setDestinationDeck(destCardDeck.getDestinationCards());
        userData.getCurrentGame().setFaceDownTrainDeck(trainCardDeck.getTrainCards());
        setSetUpData();
    }

    public void setSetUpData ()
    {
        setUpData.setColor(userData.getCurrentPlayer().getColor());
        setUpData.setTurnNumber(userData.getCurrentPlayer().getTurnNumber());
        setUpData.setStartingTrainCards(userData.getCurrentPlayer().getTrainCards());
        setUpData.setStartingDestCards(userData.getCurrentPlayer().getToChoose());
        setUpData.setChange();
    }

    public void setBoardData()
    {
        boardData.setDestDeckSize(userData.getCurrentGame().getDestinationDeck().size());
        boardData.setTrainDeckSize(userData.getCurrentGame().getFaceDownTrainDeck().size());
        boardData.setOtherPlayerInfo(info.getPlayerInfo());
        boardData.setFaceUpCards(userData.getCurrentGame().getFaceUpTrainDeck());
        boardData.setRoutes(userData.getCurrentGame().getRoutes());
        boardData.setCities(userData.getCurrentGame().getCities());
        boardData.setChange();
    }

    public void mockUpdate()
    {
        List<PlayerShallow> otherPlayerInfo = boardData.getOtherPlayerInfo();
        for (PlayerShallow player: otherPlayerInfo)
        {
            int scoreToAdd = player.getTurnNumber() * 50;
            int trainCardToSub = player.getTurnNumber() * 2;
            player.setCurrentScore(player.getCurrentScore()+scoreToAdd);
            player.setTrainCardHand(player.getTrainCardHand() - trainCardToSub);
            player.setDestCardHand(player.getDestCardHand() - 1);
        }
        boardData.setChange();
    }
}

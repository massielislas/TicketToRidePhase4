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
        if (setUpData!=null) setUpData.setChange();
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


    public Result discardCards(ArrayList<Integer> discard)
    {
        Double cardOne = new Double(discard.get(0));
        Double cardTwo = new Double(discard.get(1));

        List<DestinationCard> currentHand = userData.getCurrentPlayer().getDestCards();
        for (int i = 0; i < currentHand.size(); i ++)
        {
            if ((currentHand.get(i).getID() == cardOne)
                    || (currentHand.get(i).getID() == cardTwo))
                currentHand.remove(i);
        }
        return proxy.discardCards(userData.getUsername().getNameOrPassword(), userData.getCurrentGame().getID(), cardOne, cardTwo);
    }

    public void addCards(Double one, Double two, Double three)
    {
        ArrayList<Double> cards = new ArrayList<Double>() {
        };
        cards.add(0, one);
        cards.add(1, two);
        cards.add(2, three);
        Game currentGame = userData.getCurrentGame();
        ArrayList<DestinationCard> toAdd = currentGame.getSelectedDestinationCards(cards);
        userData.getCurrentPlayer().addToDestinationHand(toAdd);
    }

    public void updateFaceUpCards(Double cardOne, Double cardTwo, Double cardThree, Double cardFour, Double cardFive)
    {
        TrainCard[] cards = {};
        cards[0] = (userData.getCurrentGame().findSelectedTrainCard(cardOne));
        cards[1] = (userData.getCurrentGame().findSelectedTrainCard(cardTwo));
        cards[2] = (userData.getCurrentGame().findSelectedTrainCard(cardThree));
        cards[3] = (userData.getCurrentGame().findSelectedTrainCard(cardFour));
        cards[4] = (userData.getCurrentGame().findSelectedTrainCard(cardFive));
        userData.getCurrentGame().setFaceUpTrainDeck(cards);
    }

    public void addChat(String message)
    {
        chat.addChatMessage(message);
    }

    /*public void updateDeckSize(Double trainDeckSize, Double destDeckSize)
    {
        boardData.setTrainDeckSize(trainDeckSize.intValue());
        boardData.setDestDeckSize(destDeckSize.intValue());
        boardData.setChange();
    }*/

    public void updateBoardData(String jsonString)
    {
        UpdateInfo update = (UpdateInfo) Encoder.Decode(jsonString, UpdateInfo.class);
        boardData.setFaceUpCards(update.getCurrentFaceUpCards());
        boardData.setDestDeckSize(update.getDestDeckSize());
        userData.getCurrentGame().setDestDeckSize(update.getDestDeckSize());
        boardData.setOtherPlayerInfo(update.getPlayerInfo());
        userData.getCurrentGame().setOtherPlayers(update.getPlayerInfo());
        boardData.setTrainDeckSize(update.getTrainDeckSize());
        userData.getCurrentGame().setTrainDeckSize(update.getTrainDeckSize());
        boardData.setChange();
    }

    //public void updateOtherPlayer()

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
        player.setDestCards(info.getStartingDestCards());
        userData.setCurrentPlayer(player);
        userData.getCurrentGame().setFaceUpTrainDeck(info.getStartingFaceUpCards());
        userData.getCurrentGame().setCities(cities.getCityList());
        userData.getCurrentGame().setRoutes(routes.getRouteList());
        userData.getCurrentGame().setDestinationDeck(destCardDeck.getDestinationCards());
        userData.getCurrentGame().setOtherPlayers(info.getPlayerInfo());
        userData.getCurrentGame().setFaceDownTrainDeck(trainCardDeck.getTrainCards());
        setSetUpData();
    }

    public void setSetUpData ()
    {
        setUpData.setColor(userData.getCurrentPlayer().getColor());
        setUpData.setTurnNumber(userData.getCurrentPlayer().getTurnNumber());
        setUpData.setStartingTrainCards(userData.getCurrentPlayer().getTrainCards());
        setUpData.setStartingDestCards(userData.getCurrentPlayer().getDestCards());
        setUpData.setChange();
    }

    public void setBoardData()
    {
        boardData.setDestDeckSize(userData.getCurrentGame().getDestDeckSize());
        boardData.setTrainDeckSize(userData.getCurrentGame().getTrainDeckSize());
        boardData.setOtherPlayerInfo(userData.getCurrentGame().getOtherPlayers());
        boardData.setFaceUpCards(userData.getCurrentGame().getFaceUpTrainDeck());
        boardData.setRoutes(userData.getCurrentGame().getRoutes());
        boardData.setCities(userData.getCurrentGame().getCities());
        boardData.setCurrentPlayer(userData.getCurrentPlayer());
        if (userData.getCurrentPlayer().getTurnNumber() == 1)
            boardData.setUserPlaying(userData.getCurrentPlayer().getUserName().getNameOrPassword());
        else {
            List<PlayerShallow> otherPlayerInfo = boardData.getOtherPlayerInfo();
            for (PlayerShallow player : otherPlayerInfo) {
                if (player.getTurnNumber() == 1) boardData.setUserPlaying(player.getuName());
            }
        }
        boardData.setChange();
    }

    public void mockUpdate()
    {
        List<PlayerShallow> otherPlayerInfo = boardData.getOtherPlayerInfo();
        for (PlayerShallow player: otherPlayerInfo)
        {
            int scoreToAdd = player.getTurnNumber() * 50;
            int trainCardToSub = player.getTurnNumber() * 2;
            int piecesToRemove = player.getTurnNumber() *2;
            player.setPiecesLeft(player.getPiecesLeft() - piecesToRemove);
            player.setCurrentScore(player.getCurrentScore()+scoreToAdd);
            player.setTrainCardHand(player.getTrainCardHand() - trainCardToSub);
            player.setDestCardHand(player.getDestCardHand() - 1);

        }
        boardData.setChange();
    }
}

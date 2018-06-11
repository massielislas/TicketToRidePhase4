package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;
import Communication.Encoder;
import Model.InGameModels.Chat;
import Model.InGameModels.Cities;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.DestinationCardDeck;
import Model.InGameModels.Player;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Route;
import Model.InGameModels.Routes;
import Model.InGameModels.TrainCard;
import Model.InGameModels.TrainCardDeck;
import Model.State.MyState;
import Model.State.NonActiveTurnState;
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
    DrawDestCardData drawDestCardData;
    EndGameData endGameData;
    TrainCardDeck trainCardDeck;

    private PlayFacade()
    {
        trainCardDeck = new TrainCardDeck();
        setUpData = new SetUpData();
        boardData = new BoardData();
        drawDestCardData = new DrawDestCardData();
        endGameData = new EndGameData();
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
        boardData.addObserver(o);
        //if (boardData!=null) boardData.setChange();
        //setBoardData();
    }

    public void addDestCardObserver(Observer o)
    {
        drawDestCardData.addAnObserver(o);
        if (drawDestCardData.getToChoose() != null) drawDestCardData.setChange();
    }

    public void deRegisterDrawDestCardObserver(Observer o)
    {
        drawDestCardData.removeAnObserver(o);
    }

    public void addEndGameObserver(Observer o)
    {
        endGameData.addAnObserver(o);
        if (endGameData!=null) endGameData.setChange();
    }

    public void deRegisterEndGameObserver(Observer o)
    {
        endGameData.removeAnObserver(o);
    }

    public void addChatObserver(Observer o)
    {
        chat.addAnObserver(o);
    }

    public void desRegisterChatObserver(Observer o)
    {
        chat.removeAnObserver(o);
    }

    public void deRegisterSetUpObserver(Observer o)
    {
        //chat.removeAnObserver(o);
        setUpData.removeAnObserver(o);
    }

    public void deRegisterBoardObserver(Observer o)
    {
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

        Result result = proxy.discardCards(userData.getUsername().getNameOrPassword(), userData.getCurrentGame().getID(), cardOne, cardTwo);
        if (result.isSuccess())
        {
            List<DestinationCard> currentHand = userData.getCurrentPlayer().getDestCards();
            for (int i = 0; i < currentHand.size(); i ++)
            {
                if ((currentHand.get(i).getID() == cardOne)
                        || (currentHand.get(i).getID() == cardTwo))
                    currentHand.remove(i);
            }
        }
        return result;
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

    public Result claimRoute(int routeID)
    {
        return userData.getCurrentPlayer().getMyState().ClaimRoute(routeID);
    }

    public Result chooseFaceUpCard(TrainCard card)
    {
        return userData.getCurrentPlayer().getMyState().DrawFaceUpCard(card);
    }

    public Result drawFromTrainDeck()
    {
        return userData.getCurrentPlayer().getMyState().drawFaceDownCard();
    }

    public Result drawDestCards()
    {
        return userData.getCurrentPlayer().getMyState().drawDestinationCards();
    }

    public void getDestCards(Double cardOne, Double cardTwo, Double cardThree)
    {
        ArrayList<DestinationCard> destCardsToAdd = new ArrayList<DestinationCard>();
        destCardsToAdd.add(destCardDeck.getDestinationCard(cardOne.intValue()));
        destCardsToAdd.add(destCardDeck.getDestinationCard(cardTwo.intValue()));
        destCardsToAdd.add(destCardDeck.getDestinationCard(cardThree.intValue()));
        userData.getCurrentPlayer().addToDestinationHand(destCardsToAdd);
        drawDestCardData.setToChoose(destCardsToAdd);
        drawDestCardData.setChange();
        /*if (userData.getCurrentPlayer().getUserName().getNameOrPassword().equals(boardData.getUserPlaying()))
            userData.getCurrentPlayer().getMyState().getInstance().state = new NonActiveTurnState();*/
        proxy.endTurn(UserData.getUserData().getUsername().getNameOrPassword(),
                UserData.getUserData().getCurrentGame().getID());
    }

    public void updateBoardData(String jsonString)
    {

        UpdateInfo update = (UpdateInfo) Encoder.Decode(jsonString, UpdateInfo.class);
        updatePlayerInfo(update);
        boardData.setFaceUpCards(update.getCurrentFaceUpCards());
        userData.getCurrentGame().setFaceUpTrainDeck(update.getCurrentFaceUpCards());
        boardData.setDestDeckSize(update.getDestDeckSize());
        userData.getCurrentGame().setDestDeckSize(update.getDestDeckSize());
        boardData.setOtherPlayerInfo(update.getPlayerInfo());
        userData.getCurrentGame().setOtherPlayers(update.getPlayerInfo());
        boardData.setTrainDeckSize(update.getTrainDeckSize());
        userData.getCurrentGame().setTrainDeckSize(update.getTrainDeckSize());
        boardData.setGameComplete(update.isGameComplete());
        userData.getCurrentGame().setGameComplete(update.isGameComplete());
        boardData.getCurrentPlayer().setTrainPiecesLeft(update.getPiecesLeft());
        userData.getCurrentPlayer().setTrainPiecesLeft(update.getPiecesLeft());
        boardData.getCurrentPlayer().setCurrentScore(update.getPoints());
        userData.getCurrentPlayer().setCurrentScore(update.getPoints());
        if (update.getGameRoutes() != null) {
            checkForRouteColorChange(Arrays.asList(update.getGameRoutes()));
            boardData.setRoutes(Arrays.asList(update.getGameRoutes()));
            userData.getCurrentGame().setRoutes(Arrays.asList(update.getGameRoutes()));
        }

        if (update.getPlayerRoutes() != null) {
            checkForRouteColorChange(Arrays.asList(update.getPlayerRoutes()));
            userData.getCurrentPlayer().setRoutesClaimed(Arrays.asList(update.getPlayerRoutes()));
        }
        //checkDestCompleted();
        boardData.setChange();
    }

    void checkForRouteColorChange(List<Route> routes)
    {
        for (Route route: routes)
        {
            if (route.isDouble()) {
                if (route.isDoubleClaimed()) {
                    String playerName = route.getDoubleClaimant();
                    String playerColor = userData.getCurrentGame().getPlayerColorByUsername(playerName);
                    route.setDoubleColor(playerColor);
                }
            }
            if (route.isClaimed())
            {
                String playerName = route.getClaimant();
                String playerColor = userData.getCurrentGame().getPlayerColorByUsername(playerName);
                route.setColor(playerColor);
            }
        }
    }

//    public void pollerUpdate()
//    {
//        boardData.setChange();
//    }

    //Functionality added to server side instead

    /*public void checkDestCompleted()
    {
        for (DestinationCard destCard: userData.getCurrentPlayer().getDestCards())
        {
            if (RouteProcessor.DestinationComplete(destCard.getCity1(), destCard.getCity2(),
                    userData.getCurrentPlayer().getRoutesClaimed()))
            {
                destCard.setComplete(true);
            }
        }
    }*/

    private void updatePlayerInfo(UpdateInfo update)
    {
        //if (update.getToChoose() != null) userData.getCurrentPlayer().setToChoose(update.getToChoose());
        if (update.getHand() != null) {
            userData.getCurrentPlayer().setTrainCards(update.getHand());
        }
    }

    //method that gets called by command sent by server
    public void changeTurn(Double turnNumber)
    {
        if (turnNumber.intValue() == userData.getCurrentPlayer().getTurnNumber()) {
            userData.getCurrentPlayer().getMyState().activateTurn();
            boardData.setUserPlaying(userData.getCurrentPlayer().getUserName().getNameOrPassword());
            userData.getCurrentGame().setUserPlaying(userData.getCurrentPlayer().getUserName().getNameOrPassword());
        }
        else{
            List<PlayerShallow> otherPlayers = userData.getCurrentGame().getOtherPlayers();
            for (PlayerShallow player: otherPlayers)
            {
                if (player.getTurnNumber() == turnNumber.intValue()) {
                    boardData.setUserPlaying(player.getuName());
                    userData.getCurrentGame().setUserPlaying(player.getuName());
                }
            }
        }
        boardData.setChange();
    }

    public void endGame(String jsonString)
    {
        EndGameInfo endGame = (EndGameInfo) Encoder.Decode(jsonString, EndGameInfo.class);
        endGameData.setPlayerInfo(endGame.getPlayerInfo());
        endGameData.setPlayerWithLongestRoute(endGame.getPlayerWithLongestRoute());
        endGameData.setPointsFromLongestRoute(endGame.getPointsFromLongestRoute());
        endGameData.setWinner(endGame.getWinner());
        endGameData.setChange();
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
        if ((userData.getCurrentGame().getUserPlaying() == null) &&
                (userData.getCurrentPlayer().getTurnNumber() == 1)) {
            boardData.setUserPlaying(userData.getCurrentPlayer().getUserName().getNameOrPassword());
            userData.getCurrentPlayer().getMyState().activateTurn();
        }
        else if ((userData.getCurrentGame().getUserPlaying() == null) &&
                (userData.getCurrentPlayer().getTurnNumber() != 1))
        {
            List<PlayerShallow> otherPlayerInfo = boardData.getOtherPlayerInfo();
            for (PlayerShallow player : otherPlayerInfo) {
                if (player.getTurnNumber() == 1) boardData.setUserPlaying(player.getuName());
            }
        }
        else boardData.setUserPlaying(userData.getCurrentGame().getUserPlaying());
        boardData.setChange();
        /*if ((userData.getCurrentGame().getUserPlaying() == null) &&
                (userData.getCurrentPlayer().getTurnNumber() == 1))
        {
            boardData.setUserPlaying(userData.getCurrentPlayer().getUserName().getNameOrPassword());
            userData.getCurrentGame().setUserPlaying(userData.getCurrentPlayer().getUserName().getNameOrPassword());
            userData.getCurrentPlayer().getMyState().activateTurn();
        }
        else boardData.setUserPlaying(userData.getCurrentGame().getUserPlaying());*/

        /**/
    }

    public void mockUpdate()
    {
        List<PlayerShallow> otherPlayerInfo = boardData.getOtherPlayerInfo();
        List<Route> routes = boardData.getRoutes();
        Player cPlayer = boardData.getCurrentPlayer();
        cPlayer.setTrainPiecesLeft(cPlayer.getTrainPiecesLeft() - (cPlayer.getTurnNumber() * 2));
        cPlayer.setCurrentScore(cPlayer.getCurrentScore() + (cPlayer.getTurnNumber() * 60));
        routes.get(cPlayer.getTurnNumber()).setClaimed(true);
        routes.get(cPlayer.getTurnNumber()).setClaimant(cPlayer.getUserName().getNameOrPassword());
        if (cPlayer.getTurnNumber() == 2) boardData.setUserPlaying(cPlayer.getUserName().getNameOrPassword());
        for (PlayerShallow player: otherPlayerInfo)
        {
            if (player.getTurnNumber() == 2) boardData.setUserPlaying(player.getuName());
            int scoreToAdd = player.getTurnNumber() * 50;
            int trainCardToSub = player.getTurnNumber() * 2;
            int piecesToRemove = player.getTurnNumber() *2;
            player.setPiecesLeft(player.getPiecesLeft() - piecesToRemove);
            player.setCurrentScore(player.getCurrentScore()+scoreToAdd);
            player.setTrainCardHand(player.getTrainCardHand() - trainCardToSub);
            player.setDestCardHand(player.getDestCardHand() - 1);
            routes.get(player.getTurnNumber()).setClaimed(true);
            routes.get(player.getTurnNumber()).setClaimant(player.getuName());
        }
        TrainCard[] newFaceUpCards = {trainCardDeck.getCardByID(1), trainCardDeck.getCardByID(40),
                trainCardDeck.getCardByID(31), trainCardDeck.getCardByID(100), trainCardDeck.getCardByID(69)};
        boardData.setFaceUpCards(newFaceUpCards);
        boardData.setChange();
    }

     /* //method that gets called by command sent by server
    public void getDestCards(Double cardOne, Double cardTwo, Double cardThree)
    {
        ArrayList<DestinationCard> destCardsToAdd = new ArrayList<DestinationCard>();
        destCardsToAdd.add(destCardDeck.getDestinationCard(cardOne.intValue()));
        destCardsToAdd.add(destCardDeck.getDestinationCard(cardTwo.intValue()));
        destCardsToAdd.add(destCardDeck.getDestinationCard(cardThree.intValue()));
        userData.getCurrentPlayer().addToDestinationHand(destCardsToAdd);
        userData.getCurrentPlayer().setToChoose(destCardsToAdd);
        boardData.setChange();
    }*/

    /*public void updateDeckSize(Double trainDeckSize, Double destDeckSize)
    {
        boardData.setTrainDeckSize(trainDeckSize.intValue());
        boardData.setDestDeckSize(destDeckSize.intValue());
        boardData.setChange();
    }*/

    /*//method that gets called by command sent by server
    public void getTrainCard(Double cardID)
    {
        TrainCard trainCardToAdd = trainCardDeck.getCardByID(cardID.intValue());
        userData.getCurrentPlayer().addToTrainCardHand(trainCardToAdd);
        boardData.setChange();
    }*/

    /*public void addCards(Double one, Double two, Double three)
    {
        ArrayList<Double> cards = new ArrayList<Double>() {
        };
        cards.add(0, one);
        cards.add(1, two);
        cards.add(2, three);
        Game currentGame = userData.getCurrentGame();
        ArrayList<DestinationCard> toAdd = currentGame.getSelectedDestinationCards(cards);
        userData.getCurrentPlayer().addToDestinationHand(toAdd);
    }*/

    /*//Do we need this method for server to make a command for?
    //Or can we just check result object of claimRoute?
    public void getRouteChange(Double routeID){
        //if needed eventually we'll...
        boardData.setChange();
    }*/

    //public void updateOtherPlayer()

}

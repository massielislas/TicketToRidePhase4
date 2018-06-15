package Model;


import Communication.Encoder;
import Model.InGameModels.*;
import Model.InGameModels.DestinationCard;
import Results.LoginRegisterResult;
import Results.Result;

/**
 * Created by Lance on 5/15/2018.
 */

public class TicketToRideFacade implements ITicketToRide {

    private static final TicketToRideFacade instance = new TicketToRideFacade();
    private TicketToRideServer Server = TicketToRideServer.getInstance();

    public static TicketToRideFacade getInstance(){ return instance; }

    public LoginRegisterResult registerUser(String username, String password, String host, String port){
        //Check if the user already exists in the system
        UserPass uName = new UserPass(username);
        UserPass pWord = new UserPass(password);
        if (Server.doesUserExist(uName)) {
            return new LoginRegisterResult(false, "That User already exists!");
        }
        //If they don't, add them to the Map of players that currently exist
        else {
            Server.addUserPass(uName, pWord);
            CommandManager.getInstance().addAllCommandsNewUser(username);
            return new LoginRegisterResult(true);
        }
    }

    public LoginRegisterResult loginUser(String username, String password, String host, String port) {

        UserPass uName = new UserPass(username);
        UserPass pWord = new UserPass(password);
        //Check if the user exists, if they don't, throw an error
        if (!Server.doesUserExist(uName)) {
            return new LoginRegisterResult(false, "That User doesn't exist!");
        }
        //If The username or password don't match, throw an error
        if (!Server.verifyUserPass(uName, pWord)) {
            return new LoginRegisterResult(false, "UserName or Password are incorrect");
        }
        //Otherwise return true and log them into the App
        else {
            CommandManager.getInstance().addAllCommandsNewUser(username);
            return new LoginRegisterResult(true);
        }
    }

    public Result addPlayerToGame(String userPass, Double playerCount, Double currentPlayers, Double gameNumber, String ID) {
        return addPlayerToGame(userPass, (Integer) playerCount.intValue(), (Integer) currentPlayers.intValue(), (Integer) gameNumber.intValue(), ID);
    }

    public Result addPlayerToGame(String userPass, Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID) {
        //Check if the game with the corresponding ID exists
        Game game = Server.getInactiveGame(ID);
        UserPass uName = new UserPass(userPass);

        if (game == null) {
            return new Result(false, "Invalid Game ID");
        }
        //Check if the user with the corresponding name exists
        if (!Server.doesUserExist(uName)) {
            return new Result(false, "Invalid User");
        }

        if (game.userAlreadyInGame(uName)) {
            return new Result(false, "You're already in this game!");
        }
        //Then attempt to add the player to the game, if the game is full, respond accordingly
        else {
            Result check = Server.addPlayerToGame(game, uName);
            //If the attempt to add a player to the game was successful,send a command to update the
            //number of players in the corresponding game on the Client side.
            if(check.isSuccess())
            {
                String[] instanceParamTypeNames = new String[0];
                Object[] instanceMethodArgs = new Object[0];
                String[] methodParamTypeNames = {"java.lang.String"};
                Object[] methodArguments = {ID};
                Command command = new Command("Model.GameFacade", "getInstance",
                        "addPlayer", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                        methodArguments);
                CommandManager.getInstance().addCommandAllUsers(command);
                if (game.getCurrentPlayers() == game.getPlayerCount()) {
                    Server.activateGame(game);
                    startGame(game.getID());
                }
            }
            return check;
        }
    }

    //do we need this?!?!
    public Result createNewGame(Double playerCount, Double currentPlayers, Double gameNumber, String ID) {
        return createNewGame((Integer) playerCount.intValue(), (Integer) currentPlayers.intValue(), (Integer) gameNumber.intValue(), ID);
    }

    public Result createNewGame(Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID) {

        Game newGame = new Game(playerCount, currentPlayers, gameNumber, ID);

        if (Server.doesGameExist(newGame)) {
            return new Result(false, "That game already exists!");
        }
        else {
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.Double", "java.lang.String"};
            Object[] methodArguments = {playerCount, ID};
            Command command = new Command("Model.GameFacade", "getInstance",
                    "addGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommandAllUsers(command);
            Server.addGameToQueue(newGame);
            return new Result(true, "successfully created new Game");
        }
    }

//    public GameStartResult startGame (Double playerCount, Double currentPlayers, Double gameNumber, String ID) {
//        return startGame((Integer) playerCount.intValue(), (Integer) currentPlayers.intValue(), (Integer) gameNumber.intValue(), ID);
//    }

    @Override
    public Result startGame(String ID) {

        Game game = Server.getSpecificActiveGame(ID);

        if (game == null) {
            return new Result(false, "That game doesn't exist!");
        }
        else {
            if (!Server.startGame(game)) {
                return new Result(false, "That game can't be started without" +
                        " enough players");
            }
            else {
                initializeHands(game);
                return new Result(true, "Starting Game number " + game.getGameNumber());
            }
        }
    }

    private void initializeHands(Game game) {
        for (Player p : game.getPlayerList()) {
            SinglePlayerStartInfo initPack = game.dealStartingHand(p);
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.String"};
            Object[] methodArguments = {new Encoder().Encode(initPack)};
            Command command = new Command("Model.PlayFacade", "getInstance",
                    "setStartInfo", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommand(p.getUserName(),command);
            addGameHistory(game,"<< Added: " + p.getUserName().getNameOrPassword() + " to the game>>");
        }
        updatePlayers(game);
    }

// KEEP!!!
//    public void updateFaceUpCards(Double card1, Double card2, Double card3, Double card4, Double card5)
//    {
//        String[] instanceParamTypeNames = new String[0];
//        Object[] instanceMethodArgs = new Object[0];
//        String[] methodParamTypeNames = {"java.util.ArrayList<Integer>"};
//        TrainCard[] faceUpDeck = game.getTrainCardFaceupDeck();
//        ArrayList<Integer> faceUpIDs = new ArrayList<Integer>();
//        for (int i = 0; i < faceUpIDs.size(); i++)
//        {
//            faceUpIDs.add(i, faceUpDeck[i].getID());
//        }
//        Object[] methodArguments = {faceUpIDs};
//        Command command = new Command("Model.PlayFacade", "getInstance",
//                "updateFaceUpCards", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
//                methodArguments);
//        CommandManager.getInstance().addCommandAllUsers(command);
//    }

    public Result sendChat(String userName, String msg, String gameID) {
        Game toChat = Server.getSpecificActiveGame(gameID);
        if (toChat != null) {
            toChat.addChat(msg, userName);
            //Command for Chat
            String totalMessage = userName + ": " + msg;
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.String"};
            Object[] methodArguments = {totalMessage};
            Command command = new Command("Model.PlayFacade", "getInstance",
                    "addChat", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommandMultipleUsers(toChat.getUserList(),command);
            //
            return new Result(true, "");
        }
        else {
            return new Result(false, "Invalid GameID in sendChat, ServerSide");
        }
    }

    public void addGameHistory(Game game, String message){
        //ToDo: add these commands to the game chat history
        String totalMessage = message;
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String"};
        Object[] methodArguments = {totalMessage};
        Command command = new Command("Model.PlayFacade", "getInstance",
                "addChat", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        CommandManager.getInstance().addCommandMultipleUsers(game.getUserList(),command);

    }

    public Result discardDestCards(String username, String gameID, Double card1, Double card2) {
        //Get the game with the corresponding ID
        Game game = Server.getSpecificActiveGame(gameID);
        //Get the player whose hands we are modifying
        UserPass name = new UserPass(username);
        Player player = game.getPlayer(name);
        int numberdiscarded = 0;
        if (card1 != -1) {
            numberdiscarded++;
            DestinationCard toDiscard = player.removeDestCard(card1.intValue());
            if (toDiscard == null) {
                return new Result(false, "That card isn't in that players hand!");
            }
            game.addDestCardBackIn(toDiscard);
        }
        if (card2 != -1) {
            numberdiscarded++;
            DestinationCard toDiscard = player.removeDestCard(card2.intValue());
            if (toDiscard == null) {
                return new Result(false, "That card isn't in that players hand!");
            }
            game.addDestCardBackIn(toDiscard);
        }
        addGameHistory(game,"<<"+username+" kept " + (3 - numberdiscarded) + " Destination Cards>>");
        updatePlayers(game);
        return new Result(true, "");
    }

    public void updatePlayers(Game game){
        for(Player p:game.getPlayerList()){
            UpdateInfo info = game.getUpdateInfo(p);
            String gsonString = new Encoder().Encode(info);
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.String"};
            Object[] methodArguments = {gsonString};
            Command command = new Command("Model.PlayFacade", "getInstance",
                    "updateBoardData", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommand(p.getUserName(),command);
        }
    }

    //TODO Possibly implement the sending of commands from here, depending on what info is needed
    public Result claimRoute(String username, String gameID, Double routeID) {
        Result toReturn;
        Game game = Server.getSpecificActiveGame(gameID);
        toReturn = game.claimRoute(username, routeID);
        addGameHistory(game, "<<"+ username + " claimed a route>>");
        game.checkDestCompleted(username);
        updatePlayers(game);
        return toReturn;
    }
    public Result chooseFaceUpCard(String username, String gameID, Double cardID) {
        Game game = Server.getSpecificActiveGame(gameID);
        Result toReturn = game.chooseFaceUpCard(username,cardID);
        addGameHistory(game,"<<" + username + " picked up a " + toReturn.getMessage()
                + " from the face up pile>>");
        updatePlayers(game);
        return toReturn;
    }
    public Result drawFromTrainDeck(String username, String gameID) {
        Game game = Server.getSpecificActiveGame(gameID);
        Result toReturn = game.drawFromTrainDeck(username);
        addGameHistory(game, "<<" + username + " drew a card from the face down deck>>");
        updatePlayers(game);
        return toReturn;
    }
    public Result drawDestCards(String username, String gameID) {
        Game game = Server.getSpecificActiveGame(gameID);
        Player player = game.getPlayer(new UserPass(username));
        Result toReturn = game.drawDestCards(username);
        if (toReturn.isSuccess()) {
            addGameHistory(game, "<<" + username + " drew new destination cards>>");
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.Double", "java.lang.Double", "java.lang.Double"};
            Double one = new Double (player.getDestCards().get(player.getDestCards().size()-1).getID());
            Double two = new Double(player.getDestCards().get(player.getDestCards().size()-2).getID());
            Double three = new Double(player.getDestCards().get(player.getDestCards().size()-3).getID());
            Object[] methodArguments = {one, two, three};
            Command command = new Command("Model.PlayFacade", "getInstance",
                    "getDestCards", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            UserPass user = new UserPass(username);
            CommandManager.getInstance().addCommand(user,command);
        }
        updatePlayers(game);
        return toReturn;
    }

    public Result endTurn(String username, String gameID){
        Game game = Server.getSpecificActiveGame(gameID);
        int turn = game.updateTurn(); //or whatever the name of the game Method will be
        if (turn == 0) endGame(game);
        else
        {
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.Double"};
            Object[] methodArguments = {Double.valueOf(turn)};
            Command command = new Command("Model.PlayFacade", "getInstance",
                    "changeTurn", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommandMultipleUsers(game.getUserList(), command);
            updatePlayers(game);
        }
        return new Result(true,"its player " + turn +"s turn");
    }

    private void endGame(Game game)
    {
        game.calculateScores();
        EndGameInfo endInfo = game.getEndGameInfo();
        String gsonString = new Encoder().Encode(endInfo);
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String"};
        Object[] methodArguments = {gsonString};
        Command command = new Command("Model.PlayFacade", "getInstance",
                "endGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        CommandManager.getInstance().addCommandMultipleUsers(game.getUserList(), command);
        updatePlayers(game);
    }

    public Result rejoinGame(String userName) {
        UserPass user = new UserPass(userName);
        Game toRet = Server.findActiveGameByUser(user);
        Player p = toRet.getPlayer(user);
        UpdateInfo updateInfo = toRet.getUpdateInfo(p);
        Encoder encoder = new Encoder();
        String jsonUpdate = encoder.Encode(updateInfo);
        if (toRet != null) {
            CommandManager manager = CommandManager.getInstance();
            manager.reAddUser(user);
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.String", "java.lang.Double", "java.lang.String"};
            Object[] methodArguments = {toRet.getID(), Double.valueOf(toRet.getPlayerCount()), jsonUpdate};
            Command command = new Command("Model.GameFacade", "getInstance",
                    "restoreClientGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            manager.addCommand(user, command);
            updatePlayers(toRet);
            return new Result(true, "rejoined Game successfully");
        }
        else {
            return new Result(false, "That user is not currently in an active game");
        }
    }
}

package Model;

import java.util.ArrayList;

import Communication.Encoder;
import Model.InGameModels.DestinationCardDeck;
import Model.InGameModels.Player;
import Model.InGameModels.TrainCard;
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
        }
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

//    public Result discardDestCards(String username, String gameID, Double card1, Double card3){
//
//    }

    public Result selectCards(String username, String gameID, Double card1, Double card2, Double card3) {
        Game game = Server.getSpecificActiveGame(gameID);
        Player player = game.getPlayer(new UserPass(username));
        player.getDestCards().add(new DestinationCardDeck().getDestinationCard(card1.intValue()));
        if(card2.intValue() != -1) {
            player.getDestCards().add(new DestinationCardDeck().getDestinationCard(card2.intValue()));
        }
        if(card3.intValue() != -1) {
            player.getDestCards().add(new DestinationCardDeck().getDestinationCard(card3.intValue()));
        }

        if (game != null) {
            String[] instanceParamTypeNames = new String[0];
            Object[] instanceMethodArgs = new Object[0];
            String[] methodParamTypeNames = {"java.lang.Double","java.lang.Double","java.lang.Double"};
            Object[] methodArguments = {card1, card2, card3};
            Command command = new Command("Model.PlayFacade", "getInstance",
                    "addCards", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                    methodArguments);
            CommandManager.getInstance().addCommand(new UserPass(username), command);
            //

            return new Result(true, "");
        }
        //
        else {
            return new Result(false, "Something failed in discardDestCards in TicketToRideFacade");
        }
    }
}

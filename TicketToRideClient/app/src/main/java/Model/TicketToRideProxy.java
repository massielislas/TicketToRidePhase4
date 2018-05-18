package Model;

import java.net.MalformedURLException;
import java.net.URL;

import Results.GameResult;
import Results.GameStartResult;
import Results.LoginRegisterResult;
import Results.Result;
import Communication.ClientCommunicator;
import Communication.Encoder;

public class TicketToRideProxy implements ITicketToRide {

    ClientCommunicator client = ClientCommunicator.getClient();
    UserData userData = UserData.getUserData();

    @Override
    public LoginRegisterResult registerUser(String username, String password, String host, String port) {
        System.out.println("in registerUser of proxy");//TODO: REMOVE ME
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"};
        Object[] methodArguments = {username, password, host, port};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "loginUser", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + host + ":" + port + "/command");

            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);

            if (json == null) return null;
            Object result = Encoder.Decode(json, LoginRegisterResult.class);
            return (LoginRegisterResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }

    @Override
    public LoginRegisterResult loginUser(String username, String password, String host, String port) {

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"};
        Object[] methodArguments = {username, password, host, port};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "registerUser", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + host + ":" + port + "/command");
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);
            if (json == null) return null;
            Object result = Encoder.Decode(json, LoginRegisterResult.class);
            return (LoginRegisterResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }

    @Override
    public Result addPlayerToGame(String username, int playerCount, int currentPlayers, int gameNumber, String ID) {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", Integer.class.toString(), Integer.class.toString(), Integer.class.toString(), "java.lang.String"};
        Object[] methodArguments = {username, playerCount, currentPlayers, gameNumber, ID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "addPlayerToGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost().data + ":" + userData.getPort().data + "/command");
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);
            if (json == null) return null;
            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }

    @Override
    public GameResult createNewGame(int playerCount, int currentPlayers, int gameNumber, String ID) {

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {Integer.class.toString(), Integer.class.toString(), Integer.class.toString(), "java.lang.String"};
        Object[] methodArguments = {playerCount, currentPlayers, gameNumber, ID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "createNewGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost().data + ":" + userData.getPort().data + "/command");
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);
            if (json == null) return null;
            Object result = Encoder.Decode(json, GameResult.class);
            return (GameResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }

    @Override
    public GameStartResult startGame(int playerCount, int currentPlayers, int gameNumber, String ID) {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {Integer.class.toString(), Integer.class.toString(), Integer.class.toString(), "java.lang.String"};
        Object[] methodArguments = {playerCount, currentPlayers, gameNumber, ID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "startGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);

        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost().data + ":" + userData.getPort().data + "/command");
            Object[] objects = new Object[3];
            objects[0] = url;
            objects[1] = jsonStr;
            objects[2] = "";
            String json = client.post(objects);
            if (json == null) return null;
            Object result = Encoder.Decode(json, GameStartResult.class);
            return (GameStartResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
    }
}

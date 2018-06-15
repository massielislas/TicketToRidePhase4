package Model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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

            if (json == null) {
                System.out.println("Json is null in proxy");
                return new LoginRegisterResult(false, "Null result from server in TTRProxy RegisterUser. " +
                        "" + "JSON string was null");
            }

            else if (json.equals("null")) {
                System.out.println("Json is null in proxy");
                return new LoginRegisterResult(false, "Null result from server in TTRProxy RegisterUser. " +
                        "" + "JSON string was == \"null\"");
            }

            Object result = Encoder.Decode(json, LoginRegisterResult.class);
            return (LoginRegisterResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new LoginRegisterResult(false, "Malformed URL Exception");
        }
    }

    @Override
    public LoginRegisterResult loginUser(String username, String password, String host, String port) {

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
            if (json == null) {
                System.out.println("json is null");
                return new LoginRegisterResult(false, "Null result from server in loginUser" +
                        ". JSON String was null");
            }

            else if (json.equals("null")) {
                System.out.println("json is null");
                return new LoginRegisterResult(false, "Null result from server in TTRProxy loginUser. " +
                        "" + "JSON string was == \"null\"");
            }
            Object result = Encoder.Decode(json, LoginRegisterResult.class);
            return (LoginRegisterResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new LoginRegisterResult(false, "Malformed URL Exception");
        }
    }

    @Override
    public Result addPlayerToGame(String username, Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID) {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.Double", "java.lang.Double", "java.lang.Double", "java.lang.String"};
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in addPlayerToGame");
            }

            else if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in addPlayerToGame");
            }

            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }

    @Override
    public Result createNewGame(Integer playerCount, Integer currentPlayers, Integer gameNumber, String ID) {

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.Double", "java.lang.Double", "java.lang.Double", "java.lang.String"};
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in createNewGame");
            }

            else if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in createNewGame");
            }

            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }

    @Override
    public Result startGame(String ID) {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String"};
        Object[] methodArguments = {ID};

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
            if (json == null) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in startGame");
            }

            else if (json.equals("null")) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in startGame");
            }

            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }

    public Result sendChat(String username, String message, String gameID)
    {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String", "java.lang.String"};
        Object[] methodArguments = {username, message, gameID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "sendChat", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in sendChat");
            }

            else if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in sendChat");
            }

            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }

    public Result discardCards(String username, String gameID, Double cardOne, Double cardTwo)
    {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String", "java.lang.Double", "java.lang.Double"};
        Object[] methodArguments = {username, gameID, cardOne, cardTwo};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "discardDestCards", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in discardCards");
            }
            else if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in discardCards");
            }
            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }
    public Result claimRoute(String username, String gameID, Double routeID)
    {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String", "java.lang.Double"};
        Object[] methodArguments = {username, gameID, routeID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "claimRoute", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in claimRoute. " +
                        "JSON string was null");
            }

            else if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in claimRoute. " +
                        "JSON string == \"null\"");
            }

            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }
    public Result chooseFaceUpCard(String username, String gameID, Double cardID)
    {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String", "java.lang.Double"};
        Object[] methodArguments = {username, gameID, cardID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "chooseFaceUpCard", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in chooseFaceUpCard");
            }

            if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in chooseFaceUpCard");
            }
            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }
    public Result drawFromTrainDeck(String username, String gameID)
    {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String"};
        Object[] methodArguments = {username, gameID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "drawFromTrainDeck", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in drawFromTrainDeck");
            }

            if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in drawFromTrainDeck");
            }

            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }
    public Result drawDestCards(String username, String gameID)
    {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String"};
        Object[] methodArguments = {username, gameID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "drawDestCards", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in drawDestCards");
            }

            if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in drawDestCards");
            }

            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }
    public Result endTurn(String username, String gameID)
    {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String", "java.lang.String"};
        Object[] methodArguments = {username, gameID};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "endTurn", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in endTurn");
            }
            if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in endTurn");
            }
            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }
    public Result rejoinGame(String username)
    {
        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"java.lang.String"};
        Object[] methodArguments = {username};

        Command command = new Command("Model.TicketToRideFacade", "getInstance",
                "rejoinGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
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
            if (json == null) {
                System.out.println("json is null");
                return new Result(false, "Null result from server in endTurn");
            }
            if (json.equals("null")) {
                System.out.println("json == null");
                return new Result(false, "Null result from server in endTurn");
            }
            Object result = Encoder.Decode(json, Result.class);
            return (Result)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return new Result(false, "Malformed URL Exception");
        }
    }
}

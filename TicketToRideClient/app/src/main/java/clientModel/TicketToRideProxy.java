package clientModel;

import java.net.MalformedURLException;
import java.net.URL;

import clientResult.GameResult;
import clientResult.GameStartResult;
import clientResult.LoginRegisterResult;
import clientResult.Result;
import communication.ClientCommunicator;
<<<<<<< HEAD
=======
import communication.Encoder;
>>>>>>> a8039c440cefc5a524a1c6b0c92ffbd4f50b4668

public class TicketToRideProxy implements ITicketToRide {

    ClientCommunicator client = ClientCommunicator.getClient();
    UserData userData = UserData.getUserData();

    @Override
    public LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port) {
<<<<<<< HEAD
        ClientCommunicator.getInstance().setHost(host.data);
        ClientCommunicator.getInstance().setPort(port.data);
        String[] paramTypes = {"UserPass.class","UserPass.class","Host.class","Port.class"};
        Object[] params = {username, password, null,null};
        Command loginCommand = new Command("TicketToRideFacade.class",
                "getInstance",
                "registerUser",null,null,
                paramTypes,
                params);
        LoginRegisterResult result = (LoginRegisterResult) ClientCommunicator.getInstance().send("POST", loginCommand);
        return null;
=======

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"UserPass", "UserPass", "Host", "Port"};
        Object[] methodArguments = {username, password, host, port};

        Command command = new Command("TicketToRideFacade", "getInstance",
                "loginUser", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + host + ":" + port + "/command");
            String json = client.post(url, jsonStr);
            if (json == null) return null;
            Object result = Encoder.Decode(json, LoginRegisterResult.class);
            return (LoginRegisterResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
>>>>>>> a8039c440cefc5a524a1c6b0c92ffbd4f50b4668
    }

    @Override
    public LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port) {
<<<<<<< HEAD
        ClientCommunicator.getInstance().setHost(host.data);
        ClientCommunicator.getInstance().setPort(port.data);
        return null;
=======

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"UserPass", "UserPass", "Host", "Port"};
        Object[] methodArguments = {username, password, host, port};

        Command command = new Command("TicketToRideFacade", "getInstance",
                "registerUser", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + host + ":" + port + "/command");
            String json = client.post(url, jsonStr);
            if (json == null) return null;
            Object result = Encoder.Decode(json, LoginRegisterResult.class);
            return (LoginRegisterResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
>>>>>>> a8039c440cefc5a524a1c6b0c92ffbd4f50b4668
    }

    @Override
    public Result addPlayerToGame(UserPass username, Game game) {
        return null;

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"UserPass", "Game"};
        Object[] methodArguments = {username, game};

        Command command = new Command("TicketToRideFacade", "getInstance",
                "addPlayerToGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost() + ":" + userData.getPort() + "/command");
            String json = client.post(url, jsonStr);
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
<<<<<<< HEAD
    public GameResult createNewGame(Game newGame) {
        return null;
=======
    public GameResult createNewGame(Game game) {

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"Game"};
        Object[] methodArguments = {game};

        Command command = new Command("TicketToRideFacade", "getInstance",
                "createNewGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);
        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost() + ":" + userData.getPort() + "/command");
            String json = client.post(url, jsonStr);
            if (json == null) return null;
            Object result = Encoder.Decode(json, GameResult.class);
            return (GameResult)result;
        }
        catch (MalformedURLException exception)
        {
            System.out.println("Invalid URL!");
            return null;
        }
>>>>>>> a8039c440cefc5a524a1c6b0c92ffbd4f50b4668
    }

    @Override
    public GameStartResult startGame(Game game) {
        return null;

        String[] instanceParamTypeNames = new String[0];
        Object[] instanceMethodArgs = new Object[0];
        String[] methodParamTypeNames = {"Game"};
        Object[] methodArguments = {game};

        Command command = new Command("TicketToRideFacade", "getInstance",
                "startGame", instanceParamTypeNames, instanceMethodArgs, methodParamTypeNames,
                methodArguments);

        String jsonStr = Encoder.Encode(command);
        try
        {
            URL url = new URL("http://" + userData.getHost() + ":" + userData.getPort() + "/command");
            String json = client.post(url, jsonStr);
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

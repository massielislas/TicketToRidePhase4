package clientModel;

import clientResult.GameResult;
import clientResult.GameStartResult;
import clientResult.LoginRegisterResult;
import clientResult.Result;
import communication.ClientCommunicator;

public class TicketToRideProxy implements ITicketToRide {

    @Override
    public LoginRegisterResult registerUser(UserPass username, UserPass password, Host host, Port port) {
        ClientCommunicator.getInstance().setHost(host.data);
        ClientCommunicator.getInstance().setPort(port.data);
        String[] paramTypes = {"UserPass.class","UserPass.class","Host.class","Port.class"};
        Object[] params = {username, password, null,null};
//        Command loginCommand = new Command("TicketToRideFacade.class",
//                ".getInstance",
//                "registerUser",
//                paramTypes,
//                params,
//                )
//        ClientCommunicator.getInstance().send("POST", loginCommand);
        return null;
    }

    @Override
    public LoginRegisterResult loginUser(UserPass username, UserPass password, Host host, Port port) {
        ClientCommunicator.getInstance().setHost(host.data);
        ClientCommunicator.getInstance().setPort(port.data);
        return null;
    }

    @Override
    public Result addPlayerToGame(UserPass username, Game game) {
        return null;

    }

    @Override
    public GameResult createNewGame(Game newGame) {
        return null;
    }

    @Override
    public GameStartResult startGame(Game game) {
        return null;

    }
}

package Communication;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import DataPersistence.FileDAOFactory;
import DataPersistence.IDAOFactory;
import DataPersistence.IGameDAO;
import DataPersistence.IUserDAO;
import DataPersistence.SQLiteDAOFactory;

/**
 * Created by Topper on 5/14/2018.
 */

public class ServerCommunicator {

    private static final ServerCommunicator instance = new ServerCommunicator();
    private final int MAX_WAITING = 20;
    private HttpServer server;
    IGameDAO gameDAO;
    IUserDAO userDAO;

    public static ServerCommunicator getInstance(){ return instance; }

    private void run(String portNum, String storageType) {
        try {
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNum)), MAX_WAITING);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error starting server");
            return;
        }

        server.setExecutor(null);

        server.createContext("/command", new CommandHandler());

        createDAOs(storageType);

        server.start();

    }

    private void createDAOs(String storageType)
    {
        IDAOFactory daoFactory = null;
        if (storageType.equals("flat")) {
            daoFactory = new FileDAOFactory();
        }
        if (storageType.equals("sql")){
            daoFactory = new SQLiteDAOFactory();
        }
        gameDAO = daoFactory.createGameDAO();
        userDAO = daoFactory.createUserDAO();
    }

    public IGameDAO getGameDAO() {
        return gameDAO;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public static void main(String[] args) {
        String port = "";
        String storageType = "";
        if (args.length == 1) {
            port = "8080";
            storageType = args[0];
        }
        else {
            port = args[0];
            storageType = args[1];
        }
        System.out.println("Starting Server on port " + port);
        new ServerCommunicator().run(port, storageType);
        System.out.println("Server started successfully on port " + port);
    }
}

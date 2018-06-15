package Communication;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import DataPersistence.FileDAOFactory;
import DataPersistence.IDAOFactory;
import DataPersistence.IGameDAO;
import DataPersistence.IUserDAO;
import DataPersistence.SQLiteDAOFactory;
import Model.TicketToRideFacade;

/**
 * Created by Topper on 5/14/2018.
 */

public class ServerCommunicator {

    private static final ServerCommunicator instance = new ServerCommunicator();
    private final int MAX_WAITING = 20;
    private HttpServer server;

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

        //todo: get sigma and clear from args[]

        TicketToRideFacade.getInstance().setSigma(10);

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
        TicketToRideFacade.getInstance().initializeServer(daoFactory.createGameDAO(),daoFactory.createUserDAO());
    }

    private void clearDAOs(){
        
    }

    public static void main(String[] args) {
        String port = "";
        String storageType = "";
        if (args.length == 0) {
            port = "8080";
            storageType = "sql";
        }
        else if (args.length == 1) {
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

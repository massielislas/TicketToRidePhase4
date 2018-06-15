package root.tickettorideserver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import DataPersistence.FileGameDAO;
import Model.Command;
import Model.Game;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class testFileGameDAO {

    @Test
    public void test1 () {
        FileGameDAO dao = new FileGameDAO("serverlibrary/src/main/java/DataPersistence/testCommandfile.txt",
                "serverlibrary/src/main/java/DataPersistence/testGamefile.txt");

        Game game = new Game( 2, 0, 1, "OnlyTheBestGameEver");

        boolean result = dao.addGame(game);
        assertEquals(result, true);

    }

    @Test
    public void test2 () {
        FileGameDAO dao = new FileGameDAO("serverlibrary/src/main/java/DataPersistence/testCommandfile.txt",
                "serverlibrary/src/main/java/DataPersistence/testGamefile.txt");

        List<Game> users = dao.loadGames();

        for (int i = 0; i < users.size(); ++i) {
            System.out.println(users.get(i).getUserName().getNameOrPassword());
        }

        assertEquals(users.size(), 1);
    }

    /*
    @Test
    public void test1 () {
        FileGameDAO dao = new FileGameDAO("serverlibrary/src/main/java/DataPersistence/testCommandfile.txt",
                "serverlibrary/src/main/java/DataPersistence/testGamefile.txt");

        Game game = new Game( 2, 0, 1, "OnlyTheBestGameEver");
        Command command = new Command(null, null, null, null, null, null, null);
        ArrayList<Command> commands = new ArrayList<Command>();

        boolean result = dao.addGame(game);
        assertEquals(result, true);

    }

    @Test
    public void test2 () {
        FileGameDAO dao = new FileGameDAO("serverlibrary/src/main/java/DataPersistence/testCommandfile.txt",
                "serverlibrary/src/main/java/DataPersistence/testGamefile.txt");

        List<Game> users = dao.loadGames();

        for (int i = 0; i < users.size(); ++i) {
            System.out.println(users.get(i).getUserName().getNameOrPassword());
        }

        assertEquals(users.size(), 1);
    }
    */

    @Test
    public void test5 () {
        FileGameDAO dao = new FileGameDAO("serverlibrary/src/main/java/DataPersistence/testCommandfile.txt",
                "serverlibrary/src/main/java/DataPersistence/testGamefile.txt");

        boolean result = dao.clearGames();
        assertEquals(result, true);
    }

    @Test
    public void test6 () {
        FileGameDAO dao = new FileGameDAO("serverlibrary/src/main/java/DataPersistence/testUserfile.txt");

        List<Game> users = dao.loadGames();

        assertEquals(users.size(), 0);
    }

    public void test7 () {
        FileGameDAO dao = new FileGameDAO("serverlibrary/src/main/java/DataPersistence/testUserfile.txt");

        Game game = new Game( 2, 0, 1, "OnlyTheBestGameEver");
        List<Command> commands = dao.loadCommands(game);

        assertEquals(commands.size(), 0);
    }

}

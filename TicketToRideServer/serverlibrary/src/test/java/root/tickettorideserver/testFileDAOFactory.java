package root.tickettorideserver;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import static org.junit.Assert.*;

import DataPersistence.FileDAOFactory;
import DataPersistence.IGameDAO;
import DataPersistence.IUserDAO;
import Model.Command;
import Model.CommandExecuter;
import Model.Game;
import Model.User;
import Model.UserPass;

/**
 * Created by madeleineaydelotte on 6/15/18.
 */

public class testFileDAOFactory {

    public static List getClassNames(String jarName) {
        ArrayList classes = new ArrayList();

        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(
                    jarName));
            JarEntry jarEntry;

            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                if (jarEntry.getName().endsWith(".class")) {
                        System.out.println("Found "
                                + jarEntry.getName().replaceAll("/", "\\."));
                    classes.add(jarEntry.getName().replaceAll("/", "\\."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("We done");
        return classes;
    }


    @Test
    public void test1 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();

       // getClassNames("serverlibrary/libs/FlatFilePlugin.jar");

        IUserDAO userDAO = fileDAOFactory.createUserDAO();

        assertNotNull(userDAO);
    }

    @Test
    public void test2 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO gameDAO = fileDAOFactory.createGameDAO();
        assertNotNull(gameDAO);
    }

    @Test
    public void testUser1 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IUserDAO dao = fileDAOFactory.createUserDAO();

        UserPass username = new UserPass("fakeUser");
        UserPass password = new UserPass( "faker");
        User user = new User(username, password);

        boolean result = dao.addUser(user);
        assertEquals(result, true);

    }

    @Test
    public void testUser2 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IUserDAO dao = fileDAOFactory.createUserDAO();
        List<User> users = dao.loadUsers();

        for (int i = 0; i < users.size(); ++i) {
            System.out.println(users.get(i).getUserName().getNameOrPassword());
        }

        assertEquals(users.size(), 1);
    }

    @Test
    public void testUser3 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IUserDAO dao = fileDAOFactory.createUserDAO();

        boolean result = dao.clearUsers();
        assertEquals(result, true);
    }

    @Test
    public void testUser4 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IUserDAO dao = fileDAOFactory.createUserDAO();

        List<User> users = dao.loadUsers();

        assertEquals(users.size(), 0);
    }

    @Test
    public void testGame1 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        Game game = new Game( 2, 0, 1, "OnlyTheBestGameEver");

        boolean result = dao.addGame(game);
        assertEquals(result, true);

    }


    @Test
    public void testGame2 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        List<Game> games = dao.loadGames();

        for (int i = 0; i < games.size(); ++i) {
            System.out.println(games.get(i).getID());
        }

        assertEquals(games.size(), 1);
    }


    @Test
    public void testGame3 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        Game game = new Game( 2, 0, 1, "OnlyTheBestGameEver");
        Command command = new Command(null, null, null, null, null, null, null);
        ArrayList<Command> commands = new ArrayList<Command>();
        commands.add(command);

        boolean result = dao.updateCommandsForGame(game, commands);
        assertEquals(result, true);

    }

    @Test
    public void testGame4 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        Game game = new Game( 2, 0, 1, "OnlyTheBestGameEver");
        List<Command> commands = dao.loadCommands(game);

        assertEquals(commands.size(), 1);
    }

    @Test
    public void testGame5 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        Game game = new Game( 2, 1, 1, "OnlyTheBestGameEver");

        boolean result = dao.updateGameState(game);

        assertEquals(result, true);
    }

    @Test
    public void testGame6 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        List<Game> games = dao.loadGames();

        for (int i = 0; i < games.size(); ++i) {
            System.out.println(games.get(i).getID());
        }

        assertEquals(games.size(), 1);
    }

    @Test
    public void testGame7 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        boolean result = dao.clearGames();
        assertEquals(result, true);
    }

    @Test
    public void testGame8 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        List<Game> users = dao.loadGames();

        assertEquals(users.size(), 0);
    }

    @Test
    public void testGame9 () {
        FileDAOFactory fileDAOFactory = new FileDAOFactory();
        IGameDAO dao = fileDAOFactory.createGameDAO();

        Game game = new Game( 2, 0, 1, "OnlyTheBestGameEver");
        List<Command> commands = dao.loadCommands(game);

        assertEquals(commands.size(), 0);
    }
}

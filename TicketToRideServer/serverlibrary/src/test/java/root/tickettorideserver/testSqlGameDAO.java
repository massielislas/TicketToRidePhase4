package root.tickettorideserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import DataPersistence.SQLiteGameDAO;
import Model.Command;
import Model.Game;

import static org.junit.Assert.assertTrue;

/**
 * Created by Massiel on 6/15/2018.
 */

public class testSqlGameDAO {
    SQLiteGameDAO dao = new SQLiteGameDAO();

    @Before
    public void clearGames(){
        dao.clearGames();
    }

    @Test
    public void addGame(){
        Game game = new Game(3, 5, 1, "abc-cde");
        assertTrue(dao.addGame(game));
    }

    @Test
    public void getGames(){
        Game game = new Game(3, 5, 1, "abc-cde");
        assertTrue(dao.addGame(game));

        List<Game>games  = dao.loadGames();
        assertTrue(games.contains(game));
    }

    @Test
    public void updateGames(){
        Game game = new Game(3, 5, 1, "1");
        assertTrue(dao.addGame(game));

        List<Game>games  = dao.loadGames();
        assertTrue(games.contains(game));

        game.setCurrentPlayers(0);

        dao.updateGameState(game);

        List<Game>gamesUpdated  = dao.loadGames();
        assertTrue(gamesUpdated.get(0).getCurrentPlayers() == 0);
    }

    @Test
    public void updateCommands(){
        Game game = new Game(3, 5, 1, "1");
        assertTrue(dao.addGame(game));

        Command command1 = new Command("a", "b", "c", null, null, null, null);
        Command command2 = new Command("1", "2", "3", null, null, null, null);

        List<Command>commands = new ArrayList<>();
        commands.add(command1);
        commands.add(command2);

        assertTrue(dao.updateCommandsForGame(game, commands));
    }

    @Test
    public void getCommands(){
        Game game = new Game(3, 5, 1, "1");
        assertTrue(dao.addGame(game));

        Command command1 = new Command("a", "b", "c", null, null, null, null);
        Command command2 = new Command("1", "2", "3", null, null, null, null);

        List<Command>commands = new ArrayList<>();
        commands.add(command1);
        commands.add(command2);

        assertTrue(dao.updateCommandsForGame(game, commands));

        List<Command>commandsFromDb = dao.loadCommands(game);
        for(int i = 0; i < commands.size(); i++){
            assertTrue(commandsFromDb.contains(commands.get(i)));
        }
    }

    @After
    public void afterClear(){
        dao.clearGames();
    }
}

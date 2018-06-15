package root.tickettorideserver;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import DataPersistence.SQLiteGameDAO;
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
        Game game = new Game(3, 5, 1, "1");
        assertTrue(dao.addGame(game));
    }

    @Test
    public void getGames(){
        Game game = new Game(3, 5, 1, "1");
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
}

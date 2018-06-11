package Model;

import org.junit.Test;

import Results.Result;

import static org.junit.Assert.*;

/**
 * Created by Topper on 6/11/2018.
 */
public class GameFacadeTest {
    @Test
    public void createGame() throws Exception {
        GameFacade instance = GameFacade.getInstance();
        Result r = instance.createGame(3);
        assert (r.isSuccess());
    }
    @Test
    public void createGame2() throws Exception {
        GameFacade instance = GameFacade.getInstance();
        Result r = instance.createGame(6);
        assert (!r.isSuccess());
    }
    @Test
    public void addGame() throws Exception {
    }

    @Test
    public void addPlayer() throws Exception {
    }

    @Test
    public void joinGame() throws Exception {
    }

}
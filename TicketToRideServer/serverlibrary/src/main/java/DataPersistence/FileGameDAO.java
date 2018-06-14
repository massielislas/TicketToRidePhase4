package DataPersistence;

import java.util.List;

import Model.Command;
import Model.Game;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class FileGameDAO implements IGameDAO {
    @Override
    public List<Game> loadGames() {
        return null;
    }

    @Override
    public boolean addGame(Game game) {
        return false;
    }

    @Override
    public boolean updateGameState(Game game) {
        return false;
    }

    @Override
    public boolean updateCommandsForGame(Game game, List<Command> commands) {
        return false;
    }
}

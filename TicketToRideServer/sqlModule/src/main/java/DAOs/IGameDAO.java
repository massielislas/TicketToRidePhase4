package DAOs;

import java.util.List;

import Model.Command;
import Model.Game;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public interface IGameDAO {
    public List<Game> loadGames();
    public List<Command> loadCommands(Game game);
    public boolean addGame(Game game);
    public boolean updateGameState(Game game);
    public boolean updateCommandsForGame(Game game, List<Command> commands);

    public boolean clearGames();
}

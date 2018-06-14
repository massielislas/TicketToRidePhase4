package root.tickettorideclient.Views;

import java.util.ArrayList;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface IGamesPresenter {
    public void createGame(Integer numPlayers);
    public void joinGame(String gameID);
    public ArrayList<GameListItem> getGames();
    public void rejoinGame();
}

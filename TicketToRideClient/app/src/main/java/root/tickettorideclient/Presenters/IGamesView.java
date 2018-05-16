package root.tickettorideclient.Presenters;

import java.util.List;

import clientModel.Game;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface IGamesView {
    public boolean updateGamesList(List<Game> gameList);
    public boolean switchToWaitingView();
    public void popErrorToast(String message);
}

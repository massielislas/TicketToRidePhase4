package root.tickettorideclient.Presenters;

import java.util.List;

import clientModel.Game;
import root.tickettorideclient.Views.GameListItem;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface IGamesView {
    public boolean updateGamesList(List<GameListItem> gameList);
    public boolean switchToWaitingView();
    public void popErrorToast(String message);
}

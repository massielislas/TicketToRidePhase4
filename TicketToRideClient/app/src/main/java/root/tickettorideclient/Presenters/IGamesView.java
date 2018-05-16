package root.tickettorideclient.Presenters;

import java.util.ArrayList;
import java.util.List;

import clientModel.Game;
import root.tickettorideclient.Views.GameListItem;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface IGamesView {
<<<<<<< HEAD
    public boolean updateGamesList(List<GameListItem> gameList);
    public boolean switchToWaitingView();
=======
    public void updateGamesList(ArrayList<GameListItem> gameList);
    public void switchToWaitingView();
>>>>>>> integration
    public void popErrorToast(String message);
}

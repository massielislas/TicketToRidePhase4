package root.tickettorideclient.Presenters;

import android.os.Bundle;

import java.util.ArrayList;

import root.tickettorideclient.Views.GameListItem;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface IGamesView {
    public void updateGamesList(ArrayList<GameListItem> gameList);
    public void switchToWaitingView();
    public void popToast(String message);
}

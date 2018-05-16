package root.tickettorideclient.Presenters;

import java.util.Observable;
import java.util.Observer;

import clientModel.Game;
import root.tickettorideclient.Views.IWaitingPresenter;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class WaitingPresenter implements IWaitingPresenter, Observer {

    private IWaitingView view = null;

    public WaitingPresenter (IWaitingView view) {

        this.view = view;

        // waiting face . regsiter self (this)
    }

    @Override
    public void update(Observable observable, Object o) {
        //update player count
        int numPlayers = (int) o;
        view.updatePlayerCount(numPlayers);
    }
}

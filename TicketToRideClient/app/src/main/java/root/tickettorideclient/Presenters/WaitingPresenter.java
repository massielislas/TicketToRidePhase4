package root.tickettorideclient.Presenters;

import java.util.Observable;
import java.util.Observer;

import root.tickettorideclient.Views.IWaitingPresenter;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class WaitingPresenter implements IWaitingPresenter, Observer {

    private IWaitingView view = null;

    public WaitingPresenter (IWaitingView view) {
        this.view = view;
    }

    @Override
    public void update(Observable observable, Object o) {
        //update player count
        Integer numPlayers = 0; //FIXME: grab playerCount from Object
        view.updatePlayerCount(numPlayers);
    }
}

package root.tickettorideclient.Presenters;

import java.util.Observable;
import java.util.Observer;

import clientModel.Game;
import clientModel.WaitingFacade;
import root.tickettorideclient.Views.IWaitingPresenter;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class WaitingPresenter implements IWaitingPresenter, Observer {

    private IWaitingView view = null;
    private WaitingFacade facade;

    public WaitingPresenter (IWaitingView view) {

        this.view = view;
        this.facade = new WaitingFacade();
        this.facade.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        //update player count
        int numPlayers = (int) o;
        view.updatePlayerCount(numPlayers);
    }
}

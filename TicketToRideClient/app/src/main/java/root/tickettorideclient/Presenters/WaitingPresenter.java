package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.Observable;
import java.util.Observer;

import Model.WaitingFacade;
import root.tickettorideclient.Views.IWaitingPresenter;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class WaitingPresenter implements IWaitingPresenter, Observer {

    private IWaitingView view = null;
    private WaitingFacade facade;
    private FragmentActivity mn;

    public WaitingPresenter (IWaitingView view, FragmentActivity mn) {

        this.view = view;
        this.facade = new WaitingFacade();
        this.facade.addObserver(this);
        this.mn = mn;
    }

    @Override
    public void update(Observable observable, Object o) {
        //update player count
        final int numPlayers = (int) o;
        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                view.updatePlayerCount(numPlayers);
            }
        });
    }

    @Override
    public void deregister () {
        //facade.deregister(this);
    }
}

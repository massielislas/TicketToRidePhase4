package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.Observable;
import java.util.Observer;

import Model.EndGameData;
import Model.PlayFacade;
import root.tickettorideclient.Views.IEndGamePresenter;

/**
 * Created by madeleineaydelotte on 6/4/18.
 */

public class EndGamePresenter implements IEndGamePresenter, Observer {

    private IEndGameView view;
    private PlayFacade facade;
    private FragmentActivity mn;

    public EndGamePresenter (IEndGameView view, FragmentActivity mn) {
        this.view = view;
        this.mn = mn;
        facade = PlayFacade.getInstance();
        facade.addEndGameObserver(this);
    }


    @Override
    public void update(Observable observable, Object o) {

        EndGameData data = (EndGameData) o;

        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
               // view.updatePlayerScoresView();
            }
        });
    }
}

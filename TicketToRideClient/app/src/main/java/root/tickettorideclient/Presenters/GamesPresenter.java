package root.tickettorideclient.Presenters;

import java.util.Observable;
import java.util.Observer;

import root.tickettorideclient.Views.IGamesPresenter;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class GamesPresenter implements IGamesPresenter, Observer {

    private IGamesView view = null;

    public GamesPresenter(IGamesView view) {
        this.view = view;
    }

    public void createGame(Integer numPlayers) {
        //TODO: write me


        //if successful,
        //joinGame


        return;
    }

    public void joinGame(Integer gameID) {
        //TODO: write me

        //if successful,
        //switchViews

        view.switchToWaitingView();

        return;
    }

    @Override
    public void update(Observable observable, Object o) {
        //update Game List
        view.updateGamesList();
    }
}

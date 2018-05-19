package root.tickettorideclient.Presenters;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Model.GameFacade;
import Results.GameResult;
import Results.Result;
import root.tickettorideclient.Views.GameListItem;
import root.tickettorideclient.Views.IGamesPresenter;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class GamesPresenter implements IGamesPresenter, Observer {

    private IGamesView view = null;
    private GameFacade facade = null;

    public GamesPresenter(IGamesView view) {
        this.view = view;
        this.facade = new GameFacade();
        this.facade.addObserver(this);
    }

    public void createGame(Integer numPlayers) {

        GameResult result = facade.createGame(numPlayers);

        //if unsucessful,
        //pop error toast
        if (!result.isSuccess()) {
            view.popErrorToast(result.getMessage());
        }

        //if successful,
        //switch views
        if (result.isSuccess()) {
            view.switchToWaitingView();
        }


        return;
    }

    public void joinGame(String gameID) {

        Result result = facade.joinGame(gameID);

        //if unsuccessful,
        //pop error toast
        if (!result.isSuccess()) {
            view.popErrorToast(result.getMessage());
        }

        //if successful,
        //switchViews
        if (result.isSuccess()) {
            view.switchToWaitingView();
        }

        return;
    }

    @Override
    public void update(Observable observable, Object o) {
        //update Game List

        ArrayList<GameListItem> gameListItems = (ArrayList<GameListItem>) o;
        view.updateGamesList(gameListItems);
    }

    public ArrayList<GameListItem> getGames () {
        return facade.getGames();
    }
}

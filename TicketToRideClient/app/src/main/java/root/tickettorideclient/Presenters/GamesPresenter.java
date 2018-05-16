package root.tickettorideclient.Presenters;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import clientModel.Game;
import clientModel.GameFacade;
import clientResult.GameResult;
import clientResult.Result;
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
        if (result.isSuccess()) {
            view.popErrorToast(result.getMessage());
        }

        //if successful,
        //switch views
        if (result.isSuccess()) {
            view.switchToWaitingView();
        }


        return;
    }

    public void joinGame(Integer gameID) {
        //TODO: write me

        Result result = facade.joinGame(gameID);

        //if unsuccessful,
        //pop error toast
        if (result.isSuccess()) {
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
}

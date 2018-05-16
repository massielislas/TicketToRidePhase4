package root.tickettorideclient.Presenters;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import clientModel.Game;
import clientModel.GameFacade;
import clientResult.GameResult;
import root.tickettorideclient.Views.GameListItem;
import root.tickettorideclient.Views.IGamesPresenter;

import clientModel.Games;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class GamesPresenter implements IGamesPresenter, Observer {

    private IGamesView view = null;
    private GameFacade facade = null;

    public GamesPresenter(IGamesView view) {
        this.view = view;
        this.facade = new GameFacade();
    }

    public void createGame(Integer numPlayers) {
        //TODO: write me

        GameResult result = facade.createGame(); //FIXME: should take in numPlayers

        //if unsucessful,
        //pop error toast
        if (result.isSuccess()) {
            view.popErrorToast(result.getMessage());
        }

        //if successful,
        //joinGame (already done??)
        //switch views
        if (result.isSuccess()) {
            view.switchToWaitingView();
        }


        return;
    }

    public void joinGame(Integer gameID) {
        //TODO: write me

        GameResult result = facade.joinGame(/*gameID*/); //FIXME: should take gameID???

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
        Games games = (Games) o; //FIXME - need Game to be public

        TreeMap<Integer, Game> gameMap = games.getGameList();

        ArrayList<GameListItem> gameListItems = new ArrayList<>();

        for (Map.Entry<Integer, Game> entry : gameMap.entrySet()) {

        }

        view.updateGamesList(); //FIXME: take list??????
    }
}

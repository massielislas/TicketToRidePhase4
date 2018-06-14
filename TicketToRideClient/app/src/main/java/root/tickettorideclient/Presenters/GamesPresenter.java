package root.tickettorideclient.Presenters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Model.GameFacade;
import Model.WaitingFacade;
import Results.GameResult;
import Results.Result;
import root.tickettorideclient.MainActivity;
import root.tickettorideclient.Views.GameListItem;
import root.tickettorideclient.Views.IGamesPresenter;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class GamesPresenter implements IGamesPresenter, Observer {

    private IGamesView view = null;
    private GameFacade facade = null;
    private Activity mn = null;

    public GamesPresenter(IGamesView view, FragmentActivity mn) {
        this.view = view;
        this.facade = new GameFacade();
        this.mn = mn;
        this.facade.addObserver(this);
    }

    public void createGame(Integer numPlayers) {

        Result result = facade.createGame(numPlayers);

        //if unsucessful,
        //pop error toast
        if (!result.isSuccess()) {
            view.popToast(result.getMessage());
        }

        //if successful,
        //pop toast
        if (result.isSuccess()) {
            view.popToast("Successfully created game");
        }


        return;
    }

    public void joinGame(String gameID) {

        Result result = facade.joinGame(gameID);

        //if unsuccessful,
        //pop error toast
        if (!result.isSuccess()) {
            view.popToast(result.getMessage());
        }

        //if successful,
        //switchViews
        if (result.isSuccess()) {
            facade.deRegisterObserver(this);
            view.switchToWaitingView();
        }

        return;
    }

    public void rejoinGame() {
        Result result = facade.rejoinGame();
        if (!result.isSuccess()){
            view.popToast(result.getMessage());
        }

        if (result.isSuccess()){
            facade.deRegisterObserver(this);
            view.switchToBoardView();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        //update Game List

        final ArrayList<GameListItem> gameListItems = (ArrayList<GameListItem>) o;
        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                view.updateGamesList(gameListItems);
            }
        });
    }

    public ArrayList<GameListItem> getGames () {
        return facade.getGames();
    }
}

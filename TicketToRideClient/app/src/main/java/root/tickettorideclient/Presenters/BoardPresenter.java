package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.Observable;
import java.util.Observer;

import Model.PlayFacade;
import Results.Result;
import root.tickettorideclient.Views.IBoardPresenter;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public class BoardPresenter implements IBoardPresenter, Observer {

    private IBoardView view;
    private PlayFacade facade;
    private FragmentActivity mn;

    public BoardPresenter (IBoardView view, FragmentActivity mn) {
        this.view = view;
        facade = new PlayFacade();
        this.facade.addObserver(this);
        this.mn = mn;
    }

    public void sendChat (String message) {
        Result result = facade.sendChat(message);

        //if result unsucessful,
        //pop toast with error
        if (!result.isSuccess()) {
            view.popToast("Error sending chat message: " + result.getMessage());
        }

        //if result successful,
        //do nothing
    }


    @Override
    public void update(Observable observable, Object o) {

        /*    public void addHistory(String[] messages);
    public void addToHand(TrainCard card);
    public void updatePlayerPoints(String playerID, Integer points);
    public void updateTrainPieces(String playerID, Integer pieces);
    public void updateFaceUp(TrainCard[] cards);
    public void updateDestinationDeck(Integer cardCount);
    public void updateTrainDeck(Integer cardCount); */

        //update whatever
        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
              //  view.addHistory();
              //  view.addToHand();
              //  view.updatePlayerPoints();
              //  view.updateTrainDeck();
                // view.updateFaceUp();
              //  view.updateDestinationDeck();
              //  view.updateTrainDeck();
            }
        });
    }

}

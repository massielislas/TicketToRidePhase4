package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import Model.InGameModels.Chat;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.TrainCard;
import Model.PlayFacade;
import Model.SetUpData;
import Model.SinglePlayerStartInfo;
import Model.WaitingFacade;
import Results.Result;
import root.tickettorideclient.Views.ISetUpPresenter;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public class SetUpPresenter implements ISetUpPresenter, Observer {

    private ISetUpView view;
    private PlayFacade facade;
    private FragmentActivity mn;

    public SetUpPresenter (ISetUpView view, FragmentActivity mn) {
        this.view = view;
        this.facade = new PlayFacade();
//        this.facade.addObserver(this);
        this.mn = mn;
    }

    public ArrayList<DestinationCard> getDestinationCards () {
        //return facade.getDestinationCards();
        return null;
    }

    public void keepDestinationCards(ArrayList<Integer> cardIDs) {

        Result result = facade.selectCards(cardIDs);

        //if result is unsuccessful
        //pop error toast
        if (!result.isSuccess()) {
            view.popToast("Cannot commit choices: " + result.getMessage());
        }

        //if result is successful
        //switch to board
        if (result.isSuccess()) {
            //unregister??
            view.switchToBoardView();
        }
    }

    @Override
    public void update(Observable observable, Object o) {

        //if observable is Chat
        //don't do anything
        if (observable.getClass().equals(Chat.class)) {
            return;
        }

        //if observable is [other]
        //set up
        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                SetUpData data = facade.getSetUpData();

                view.setPlayerNumber(data.getTurnNumber());
                view.setPlayerColor(data.getColor());
                view.setDestCards(new ArrayList<DestinationCard>(data.getStartingDestCards()));
                view.setHand(new ArrayList<TrainCard>(data.getStartingTrainCards()));
            }
        });
    }
}

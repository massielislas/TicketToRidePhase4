package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

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
        this.facade = PlayFacade.getInstance();
//        this.facade.addObserver(this);
        this.facade.addSetUpObserver(this);
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
            facade.deRegisterSetUpObserver(this);
            view.switchToBoardView();
        }
    }

    @Override
    public void update(Observable observable, Object o) {

        final SetUpData data = (SetUpData) o;

        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                view.setPlayerNumber(data.getTurnNumber());
                view.setPlayerColor(data.getColor());
                view.setDestCards(new ArrayList<DestinationCard>(data.getStartingDestCards()));
                view.setHand(new ArrayList<TrainCard>(data.getStartingTrainCards()));
            }
        });
    }
}

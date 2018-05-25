package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import Model.DestinationCard;
import Model.PlayFacade;
import Model.WaitingFacade;
import Results.Result;
import root.tickettorideclient.Views.ISetUpPresenter;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public class SetUpPresenter implements ISetUpPresenter {

    private ISetUpView view;
    private PlayFacade facade;
    private FragmentActivity mn;

    public SetUpPresenter (ISetUpView view, FragmentActivity mn) {
        this.view = view;
        this.facade = new PlayFacade();
        this.facade.addObserver(this);
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
            view.switchToBoardView();
        }
    }
}

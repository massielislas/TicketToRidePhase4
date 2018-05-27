package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Model.InGameModels.DestinationCard;
import Model.PlayFacade;
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
            view.switchToBoardView();
        }
    }

    @Override
    public void update(Observable observable, Object o) {

      // final SinglePlayerStartInfo startInfo = facade.getStartInfo();

        //update whatever
        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {


                //view.setPlayerNumber(startInfo.getTurnNumber());
               // view.setPlayerColor();
               // view.setDestCards();
               // view.setHand();

            }
        });
    }
}

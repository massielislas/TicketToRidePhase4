package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import Model.BoardData;
import Model.InGameModels.DestinationCard;
import Model.PlayFacade;
import root.tickettorideclient.Views.IDrawDestinationPresenter;

/**
 * Created by madeleineaydelotte on 5/28/18.
 */

public class DrawDestinationCardsPresenter implements IDrawDestinationPresenter, Observer {

    private IDrawDestinationCardsView view;
    private PlayFacade facade;
    private FragmentActivity mn;

    public DrawDestinationCardsPresenter (IDrawDestinationCardsView view, FragmentActivity mn) {
        this.view = view;
        this.mn = mn;
        facade = PlayFacade.getInstance();
        facade.addBoardObserver(this);
    }

    @Override
    public void setChoices () {
        //TODO
    }

    @Override
    public void returnDestCards (DestinationCard[] cards) {
        //TODO
    }

    @Override
    public void update(Observable observable, Object o) {

        final BoardData data = (BoardData) o;

        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                view.updateDestCards(data.getCurrentPlayer().getToChoose().toArray(new DestinationCard[data.getCurrentPlayer().getToChoose().size()]));
            }
        });
    }
}

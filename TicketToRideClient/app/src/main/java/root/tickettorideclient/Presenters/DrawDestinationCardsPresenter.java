package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import Model.BoardData;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.Player;
import Model.PlayFacade;
import Model.UserData;
import Results.Result;
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
    public void returnDestCards (ArrayList<DestinationCard> cards) {

        if ((cards != null) && (cards.size() != 0)) {
            ArrayList<Integer> cardIDs = new ArrayList<>(cards.size());

            for (int i = 0; i < cardIDs.size(); ++i) {
                cardIDs.add(i, cards.get(i).getID());
            }

            Result result = facade.discardCards(cardIDs);
        }
    }

    @Override
    public void update(Observable observable, Object o) {

        final BoardData data = (BoardData) o;

        UserData userData = UserData.getUserData();
        Player currentPlayer = userData.getCurrentPlayer();
        final ArrayList<DestinationCard> cards = new ArrayList<>(currentPlayer.getToChoose());

        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
               view.updateDestCards(cards);
            }
        });
    }
}

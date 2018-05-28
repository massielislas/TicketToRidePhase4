package root.tickettorideclient.Presenters;

import java.util.ArrayList;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.Player;
import Model.PlayFacade;
import Model.UserData;
import root.tickettorideclient.Views.IDestinationCardsPresenter;

/**
 * Created by madeleineaydelotte on 5/28/18.
 */

public class DestinationCardsPresenter implements IDestinationCardsPresenter {

    private PlayFacade facade;

    public DestinationCardsPresenter () {
        facade = new PlayFacade();
    }

    public ArrayList<DestinationCard> getDestCards () {
        UserData thisUser = UserData.getUserData();
        Player currentPlayer = thisUser.getCurrentPlayer();

        if (currentPlayer == null) {
            return null;
        }


        return new ArrayList<>(currentPlayer.getDestCards());
    }

}

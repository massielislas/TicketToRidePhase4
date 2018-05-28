package root.tickettorideclient.Presenters;

import java.util.ArrayList;

import Model.InGameModels.DestinationCard;
import Model.PlayFacade;
import root.tickettorideclient.Views.IDrawDestinationPresenter;

/**
 * Created by madeleineaydelotte on 5/28/18.
 */

public class DrawDestinationCardsPresenter implements IDrawDestinationPresenter {

    private PlayFacade facade;

    public DrawDestinationCardsPresenter () {
        facade = new PlayFacade();
    }

    @Override
    public ArrayList<DestinationCard> getChoices() {
       // facade.getChoices();
        return null;
    }
}

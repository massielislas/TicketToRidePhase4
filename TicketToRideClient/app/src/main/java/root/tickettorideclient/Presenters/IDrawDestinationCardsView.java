package root.tickettorideclient.Presenters;

import java.util.ArrayList;

import Model.InGameModels.DestinationCard;

/**
 * Created by madeleineaydelotte on 5/28/18.
 */

public interface IDrawDestinationCardsView {
    public void popToast(String message);
    public void updateDestCards(ArrayList<DestinationCard> cards);
}

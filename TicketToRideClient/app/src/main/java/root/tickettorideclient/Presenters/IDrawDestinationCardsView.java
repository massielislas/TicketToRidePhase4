package root.tickettorideclient.Presenters;

import Model.InGameModels.DestinationCard;

/**
 * Created by madeleineaydelotte on 5/28/18.
 */

public interface IDrawDestinationCardsView {
    public void popToast(String message);
    public void updateDestCards(DestinationCard[] cards);
}

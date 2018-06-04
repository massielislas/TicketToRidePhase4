package root.tickettorideclient.Views;

import java.util.ArrayList;

import Model.InGameModels.DestinationCard;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public interface ISetUpPresenter {
    public ArrayList<DestinationCard> getDestinationCards();
    public void rejectDestCards(ArrayList<Integer> cardIDs);
}

package root.tickettorideclient.Presenters;

import android.graphics.Color;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.TrainCard;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public interface ISetUpView {
    public void setPlayerNumber(Integer num);
    public void setPlayerColor(Integer color);
    public void setHand(TrainCard[] cards);
    public void setDestCards(DestinationCard[] cards);

    public void popToast(String message);
    public void switchToBoardView();
}

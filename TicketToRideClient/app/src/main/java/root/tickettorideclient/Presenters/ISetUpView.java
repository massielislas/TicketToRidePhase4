package root.tickettorideclient.Presenters;

import android.graphics.Color;

import java.util.ArrayList;

import Model.InGameModels.DestinationCard;
import Model.InGameModels.TrainCard;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public interface ISetUpView {
    public void setPlayerNumber(Integer num);
    public void setPlayerColor(String color);
    public void setHand(ArrayList<TrainCard> cards);
    public void setDestCards(ArrayList<DestinationCard> cards);

    public void popToast(String message);
    public void switchToBoardView();
}

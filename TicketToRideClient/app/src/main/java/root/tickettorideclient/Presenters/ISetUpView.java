package root.tickettorideclient.Presenters;

import android.graphics.Color;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public interface ISetUpView {
    public void setPlayerNumber(Integer num);
    public void setPlayerColor(Color color);
  //  public void setHand(TrainCard[] cards);

    public void popToast(String message);
    public void switchToBoardView();
}

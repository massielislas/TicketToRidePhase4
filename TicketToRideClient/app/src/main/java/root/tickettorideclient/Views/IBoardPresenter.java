package root.tickettorideclient.Views;

import Model.InGameModels.Route;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public interface IBoardPresenter {
    public void sendChat(String message);
    public void claimRoute(Route route);
    public void test();
}

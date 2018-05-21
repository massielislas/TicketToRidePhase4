package root.tickettorideclient.Presenters;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public interface IBoardView {
    public void setHistory(String[] messages);
    public void setPlayer();
    public void setOtherPlayers();
    public void setDeck();

    public void popToast(String message);
    public void switchToEndView();
}

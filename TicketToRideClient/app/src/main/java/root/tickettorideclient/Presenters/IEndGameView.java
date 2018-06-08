package root.tickettorideclient.Presenters;

/**
 * Created by madeleineaydelotte on 6/4/18.
 */

public interface IEndGameView {
    public void updatePlayerScoresView(String playerID, int score);
    public void popErrorToast(String message);
}

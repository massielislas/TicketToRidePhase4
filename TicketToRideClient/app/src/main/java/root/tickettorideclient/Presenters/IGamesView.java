package root.tickettorideclient.Presenters;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface IGamesView {
    public boolean updateGamesList(/*List<Game> gameList*/); //FIXME: need Game class
    public boolean switchToWaitingView();
}

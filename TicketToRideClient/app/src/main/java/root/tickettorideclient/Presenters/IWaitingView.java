package root.tickettorideclient.Presenters;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface IWaitingView {
    public void updatePlayerCount(Integer numPlayers);
    public void switchView(); //FIXME: rename? next Phase changes; Currently just pops a toast
  //  public Integer getCurrentPlayerCount();
}

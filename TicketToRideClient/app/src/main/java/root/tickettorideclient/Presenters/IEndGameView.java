package root.tickettorideclient.Presenters;

import java.util.ArrayList;

import root.tickettorideclient.Views.PlayerFinalStats;

/**
 * Created by madeleineaydelotte on 6/4/18.
 */

public interface IEndGameView {
    public void updatePlayers(ArrayList<PlayerFinalStats> players);
    public void updateWinner(PlayerFinalStats winner);
    public void popErrorToast(String message);
}

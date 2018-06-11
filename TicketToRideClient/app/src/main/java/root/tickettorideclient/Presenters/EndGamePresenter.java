package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Model.EndGameData;
import Model.InGameModels.PlayerShallow;
import Model.PlayFacade;
import root.tickettorideclient.Views.IEndGamePresenter;
import root.tickettorideclient.Views.PlayerFinalStats;
import root.tickettorideclient.Views.PlayerStats;

/**
 * Created by madeleineaydelotte on 6/4/18.
 */

public class EndGamePresenter implements IEndGamePresenter, Observer {

    private IEndGameView view;
    private PlayFacade facade;
    private FragmentActivity mn;

    public EndGamePresenter (IEndGameView view, FragmentActivity mn) {
        this.view = view;
        this.mn = mn;
        facade = PlayFacade.getInstance();
        facade.addEndGameObserver(this);
    }


    @Override
    public void update(Observable observable, Object o) {

        final EndGameData data = (EndGameData) o;

        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                String winnerName = data.getWinner();
                String longestRoutePlayer = data.getPlayerWithLongestRoute();
                Integer longestRoutePoints = data.getPointsFromLongestRoute();

                ArrayList<PlayerFinalStats> players = new ArrayList<>();
                for (int i = 0; i < data.getPlayerInfo().size(); ++i) {
                    PlayerShallow playerShallow = data.getPlayerInfo().get(i);
                    PlayerFinalStats player = new PlayerFinalStats();

                    player.setName(playerShallow.getuName());
                    player.setTotalPoints(playerShallow.getCurrentScore());
                    player.setClaimedRoutesPoints(playerShallow.getPointsFromRoutes());
                    player.setReachedDestinationsPoints(playerShallow.getPointsFromDest());
                    player.setLostDestinations(playerShallow.getNegativePoints());

                    if (player.getName().equals(longestRoutePlayer)) {
                        player.setLongestRoutePoints(longestRoutePoints);
                    }
                    else {
                        player.setLongestRoutePoints(0);
                    }


                    if (player.getName().equals(winnerName)) {
                        view.updateWinner(player);
                    }
                    else {
                        players.add(i, player);
                    }
                }

                view.updatePlayers(players);
            }
        });
    }
}

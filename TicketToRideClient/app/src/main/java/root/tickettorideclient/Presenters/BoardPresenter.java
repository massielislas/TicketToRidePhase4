package root.tickettorideclient.Presenters;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import Model.BoardData;
import Model.InGameModels.Chat;
import Model.InGameModels.City;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.Player;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;
import Model.PlayFacade;
import Model.SetUpData;
import Results.Result;
import root.tickettorideclient.Views.IBoardPresenter;
import root.tickettorideclient.Views.PlayerStats;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public class BoardPresenter implements IBoardPresenter, Observer {

    private IBoardView view;
    private PlayFacade facade;
    private FragmentActivity mn;

    public BoardPresenter (IBoardView view, FragmentActivity mn) {
        this.view = view;
        facade = PlayFacade.getInstance();
        this.mn = mn;
        facade.addBoardObserver(this);
     //   facade.addBoardObserver(this);
    }

    public void sendChat (String message) {
        Result result = facade.sendChat(message);

        //if result unsucessful,
        //pop toast with error
        if (!result.isSuccess()) {
            view.popToast("Error sending chat message: " + result.getMessage());
        }

        //if result successful,
        //do nothing
    }


    @Override
    public void update(Observable observable, Object o) {

        if (o.getClass().equals(Chat.class)) {
            final Chat chat = (Chat) o;
            mn.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.addAllHistory(chat.getChat());
                }
            });
            return;
        }

        final BoardData data = (BoardData) o;

        mn.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (view.getNumCities() != data.getCities().size()) {
                    view.addAllCities(new ArrayList<City>(data.getCities()));
                }

                view.addAllRoutes(new ArrayList<Route>(data.getRoutes()));

                if (view.getNumPlayers() != data.getOtherPlayerInfo().size()) {

                    ArrayList<PlayerStats> playerStats = new ArrayList<>();
                    for (int i = 0; i < data.getOtherPlayerInfo().size(); ++i) {
                        PlayerShallow player = data.getOtherPlayerInfo().get(i);
                        PlayerStats newPlayer = new PlayerStats();

                        newPlayer.setDestinationCards(player.getDestCardHand());
                        newPlayer.setTrainCards(player.getTrainCardHand());
                        newPlayer.setTrainPieces(player.getPiecesLeft());
                        newPlayer.setUsername(player.getuName());

                        playerStats.add(newPlayer);
                    }

                    view.addAllPlayers(playerStats);
                }

                view.addAllHistory(data.getChat().getChat());

                view.updateTrainDeck(data.getTrainDeckSize());
                view.updateDestinationDeck(data.getDestDeckSize());
                view.updateFaceUp(new ArrayList<TrainCard>(Arrays.asList(data.getFaceUpCards())));

                Player thisPlayer = data.getCurrentPlayer();
                view.updatePlayerPoints(thisPlayer.getUserName().getNameOrPassword(), thisPlayer.getCurrentScore());
                view.updateHand(new ArrayList<TrainCard>(thisPlayer.getTrainCards()));
                view.updatePlayerPieces(thisPlayer.getUserName().getNameOrPassword(), thisPlayer.getTrainPiecesLeft());
              //  view.updateDestCards(new ArrayList<DestinationCard>(thisPlayer.getDestCards()));

                for (int i = 0; i < data.getOtherPlayerInfo().size(); ++i) {
                    PlayerShallow player = data.getOtherPlayerInfo().get(i);
                    view.updatePlayerPoints(player.getuName(), player.getCurrentScore());
                    view.updatePlayerPieces(player.getuName(), player.getPiecesLeft());
                    view.updatePlayerDestCards(player.getuName(), player.getDestCardHand());
                    view.updatePlayerTrainCards(player.getuName(), player.getTrainCardHand());
                }

                view.updateTurn("DUMMY_VALUE");
                //view.updateTurn(data.get);

            }
        });
    }

    @Override
    public void claimRoute (Route route) {
        //if route is claimed
        //pop toast
        if (route.isClaimed()) {
            view.popToast("Route has already been claimed, by " + route.getClaimant());
            return;
        }

        //else claim route
        //facade.claimRoute(route);
        view.popToast("Claiming route underway!");

    }

    @Override
    public void test () {
        view.popToast("Starting Test");
        facade.mockUpdate();
    }

}

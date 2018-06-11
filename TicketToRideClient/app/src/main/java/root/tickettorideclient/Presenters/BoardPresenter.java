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
        facade.addChatObserver(this);
        facade.setBoardData();
    }

    public void sendChat (String message) {
        Result result = facade.sendChat(message);

        //if result unsuccessful,
        //pop toast with error
        if (!result.isSuccess()) {
            view.popToast("Error sending chat message: " + result.getMessage());
        }

        //if result successful,
        //do nothing
    }

    public void claimRoute(Integer routeID) {

        Result result = facade.claimRoute(routeID);

        //if result unsuccessful,
        //pop toast with error
        if (!result.isSuccess()) {
            view.popToast("Error claiming route: " + result.getMessage());
            return;
        }

        //if result successful,
        //pop toast with success
        view.popToast("Route successfully claimed.");
    }

    public void chooseFaceUpCard(TrainCard card) {
        Result result = facade.chooseFaceUpCard(card);

        //if result unsuccessful,
        //pop toast with error
        if (!result.isSuccess()) {
            view.popToast("Error drawing face-up card: " + result.getMessage());
            return;
        }

        //if result successful,
        //do nothing
    }

    public void drawFromTrainDeck() {
        Result result = facade.drawFromTrainDeck();

        //if result unsuccessful,
        //pop toast with error
        if (!result.isSuccess()) {
            view.popToast("Error drawing from deck: " + result.getMessage());
            return;
        }

        //if result successful,
        //do nothing
    }

    @Override
    public boolean drawFromDestDeck(){
        Result result = facade.drawDestCards();
        if (!result.isSuccess()) {
            view.popToast("Error drawing from deck: " + result.getMessage());
        }
        return result.isSuccess();
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

                if (data.isGameComplete()) view.switchToEndView();

                else {
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

                    view.updateTurn(data.getUserPlaying());

                }
            }
        });
    }



}

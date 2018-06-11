package root.tickettorideclient.Presenters;

import java.util.ArrayList;

import Model.InGameModels.City;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Route;
import Model.InGameModels.TrainCard;
import root.tickettorideclient.Views.PlayerStats;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public interface IBoardView {
    public void addAllHistory(ArrayList<String> messages);
    public void addOneHistory(String message);

    public void updateHand(ArrayList<TrainCard> cards);

    public void updatePlayerPoints(String playerID, Integer points);
    public void updatePlayerPieces(String playerID, Integer pieces);

    public void updatePlayerTrainCards(String playerID, Integer pieces);
    public void updatePlayerDestCards(String playerID, Integer pieces);

    public void updateFaceUp(ArrayList<TrainCard> cards);
    public void updateDestinationDeck(Integer cardCount);
    public void updateTrainDeck(Integer cardCount);
    public void addAllCities(ArrayList<City> cities);
    public void addAllRoutes(ArrayList<Route> routes);
    public void addAllPlayers(ArrayList<PlayerStats> players);

    public void updateTurn(String playerID);

    public void popToast(String message);
    public void switchToEndView();

    public Integer getNumCities();
    public Integer getNumPlayers();
    public Integer getNumRoutes();
}

package root.tickettorideclient.Presenters;

import Model.InGameModels.TrainCard;

/**
 * Created by madeleineaydelotte on 5/21/18.
 */

public interface IBoardView {
    public void addHistory(String[] messages);
    public void addToHand(TrainCard card);
    public void updatePlayerPoints(String playerID, Integer points);
    public void updateTrainPieces(String playerID, Integer pieces);
    public void updateFaceUp(TrainCard[] cards);
    public void updateDestinationDeck(Integer cardCount);
    public void updateTrainDeck(Integer cardCount);

    public void popToast(String message);
    public void switchToEndView();
}

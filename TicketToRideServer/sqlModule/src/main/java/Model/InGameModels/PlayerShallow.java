package Model.InGameModels;

/**
 * Created by Lance on 5/25/2018.
 */

public class PlayerShallow {
    private String uName;
    private int trainCardHand;
    private int destCardHand;
    private int piecesLeft;
    private int turnNumber;
    private int currentScore;
    private int pointsFromRoutes;
    private int pointsFromDest;
    private int negativePoints;

    public PlayerShallow(String uName, int trainCardHand, int destCardHand, int piecesLeft, int turnNumber, int currentScore)
    {
        this.uName = uName;
        this.trainCardHand = trainCardHand;
        this.destCardHand = destCardHand;
        this.piecesLeft = piecesLeft;
        this.turnNumber = turnNumber;
        this.currentScore = currentScore;
    }

    public int getPointsFromRoutes() {
        return pointsFromRoutes;
    }

    public void setPointsFromRoutes(int pointsFromRoutes) {
        this.pointsFromRoutes = pointsFromRoutes;
    }

    public int getPointsFromDest() {
        return pointsFromDest;
    }

    public void setPointsFromDest(int pointsFromDest) {
        this.pointsFromDest = pointsFromDest;
    }

    public int getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(int negativePoints) {
        this.negativePoints = negativePoints;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public String getuName()
    {
        return uName;
    }

    public void setuName(String uName)
    {
        this.uName = uName;
    }

    public int getTrainCardHand()
    {
        return trainCardHand;
    }

    public void setTrainCardHand(int trainCardHand)
    {
        this.trainCardHand = trainCardHand;
    }

    public int getDestCardHand()
    {
        return destCardHand;
    }

    public void setDestCardHand(int destCardHand)
    {
        this.destCardHand = destCardHand;
    }

    public int getPiecesLeft()
    {
        return piecesLeft;
    }

    public void setPiecesLeft(int piecesLeft)
    {
        this.piecesLeft = piecesLeft;
    }

    public int getTurnNumber()
    {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber)
    {
        this.turnNumber = turnNumber;
    }
}

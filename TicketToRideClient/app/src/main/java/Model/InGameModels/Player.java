package Model.InGameModels;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.TreeSet;

public class Player extends Observable {

    ArrayList<DestinationCard> destinationHand;
    ArrayList<TrainCard> traincardHand;
    TreeSet<Route> routes;
    Color color;
    int piecesCount;
    int score;

    public Player(ArrayList<TrainCard> traincardHand, TreeSet<Route> routes, Color color) {
        this.traincardHand = traincardHand;
        this.routes = routes;
        this.color = color;
        piecesCount = 240;
        score = 0;
    }

    public void addToDestinationHand(ArrayList<DestinationCard> toAdd)
    {
        for (DestinationCard card: toAdd)
        {
            destinationHand.add(card);
        }

    }

    public ArrayList<DestinationCard> getDestinationHand() {
        return destinationHand;
    }

    public ArrayList<TrainCard> getTraincardHand() {
        return traincardHand;
    }

    public TreeSet<Route> getRoutes() {
        return routes;
    }

    public Color getColor() {
        return color;
    }

    public int getPiecesCount() {
        return piecesCount;
    }

    public int getScore() {
        return score;
    }
}

package Model.InGameModels;

import android.graphics.Color;

public class TrainCard {
    Color color;
    String name;
    int cardNo;

    public TrainCard(Color color, String name, int cardNo) {
        this.color = color;
        this.name = name;
        this.cardNo = cardNo;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getCardNo() {
        return cardNo;
    }
}

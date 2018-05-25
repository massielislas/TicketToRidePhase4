package Model.InGameModels;

import android.graphics.Color;

public class TrainCard {
    private Color color;
    private String type;
    private int ID;

    public TrainCard(int ID, Color color, String type) {
        this.ID = ID;
        this.color = color;
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

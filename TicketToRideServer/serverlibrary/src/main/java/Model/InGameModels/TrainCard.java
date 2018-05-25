package Model.InGameModels;

import java.awt.Color;

/**
 * Created by Master_Chief on 5/23/2018.
 */

public class TrainCard {
    private int ID;
    private String color;
    private String type;

    public TrainCard(int ID, String color, String type) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

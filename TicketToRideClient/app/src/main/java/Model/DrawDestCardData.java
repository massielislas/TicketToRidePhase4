package Model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Model.InGameModels.DestinationCard;

public class DrawDestCardData extends Observable {

    private List<DestinationCard> toChoose;

    public void setChange()
    {
        setChanged(); //set change has occurred
        notifyObservers(this); //notify observers we have a change and give them new playercount
        clearChanged(); //no longer have a change!
    }

    public void addAnObserver(Observer o)
    {
        addObserver(o);
    }

    public void removeAnObserver(Observer o)
    {
        removeAnObserver(o);
    }

    public List<DestinationCard> getToChoose() {
        return toChoose;
    }

    public void setToChoose(List<DestinationCard> toChoose) {
        this.toChoose = toChoose;
    }
}

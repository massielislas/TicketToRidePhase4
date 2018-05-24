package Model;

import java.util.ArrayList;
import java.util.Observable;

public class Chat extends Observable
{

    private static final Chat instance = new Chat();

    ArrayList<String> chat = new ArrayList<String>();

    public ArrayList<String> getChat() {
        return chat;
    }

    public void addChatMessage(String message)
    {
        setChanged(); //set change has occurred
        notifyObservers(chat); //TODO:
                                // replace chat with necessary view list
        clearChanged(); //no longer have a change!
    }
    public void addObserver(Object o)
    {
        addObserver(o);
    }

    public static Chat getInstance() {
        return instance;
    }
}

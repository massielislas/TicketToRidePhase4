package root.tickettorideclient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import root.tickettorideclient.Callbacks.IDestinationCardsCallback;
import root.tickettorideclient.Callbacks.IDrawDestinationsCallback;
import root.tickettorideclient.Callbacks.IEndGameCallback;
import root.tickettorideclient.Callbacks.IGoToBoardCallback;
import root.tickettorideclient.Views.BoardView;
import root.tickettorideclient.Views.DestinationCardsView;
import root.tickettorideclient.Views.DrawDestinationCardsView;
import root.tickettorideclient.Views.EndGameView;
import root.tickettorideclient.Views.SetupView;

public class GameActivity extends AppCompatActivity implements IGoToBoardCallback, IDestinationCardsCallback, IDrawDestinationsCallback, IEndGameCallback{

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.game_activity_container);

        if(fragment == null){
            fragment = new SetupView();
            fragmentManager.beginTransaction().add(R.id.game_activity_container, fragment).commit();
        }
    }

    @Override
    public void goToBoard() {
        Fragment boardFragment = new BoardView();
        fragmentManager.beginTransaction().replace(R.id.game_activity_container, boardFragment).addToBackStack(null).commit();
    }

    @Override
    public void goToDestinationCards() {
        Fragment destinationCardsFragment = new DestinationCardsView();
        fragmentManager.beginTransaction().replace(R.id.game_activity_container, destinationCardsFragment).addToBackStack(null).commit();
    }

    @Override
    public void goToDrawDestinations() {
        Fragment destinationCardsFragment = new DrawDestinationCardsView();
        fragmentManager.beginTransaction().replace(R.id.game_activity_container, destinationCardsFragment).addToBackStack(null).commit();
    }

    @Override
    public void goToEndGame() {
        Fragment endGameFragment = new EndGameView();
        fragmentManager.beginTransaction().replace(R.id.game_activity_container, endGameFragment).commit();
    }
}

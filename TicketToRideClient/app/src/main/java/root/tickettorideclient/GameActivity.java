package root.tickettorideclient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import root.tickettorideclient.Views.SetupView;

public class GameActivity extends AppCompatActivity {

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
}

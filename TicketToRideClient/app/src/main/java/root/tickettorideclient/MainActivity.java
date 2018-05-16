package root.tickettorideclient;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import root.tickettorideclient.Views.GamesView;
import root.tickettorideclient.Views.LoginView;
import root.tickettorideclient.Views.WaitingView;

public class MainActivity extends AppCompatActivity implements ICallBack {
    FragmentManager fragmentManager;

    @Override
    public void onLoginSuccess() {
        Fragment gamesFragment = new GamesView();
        fragmentManager.beginTransaction().replace(R.id.main_acttivity_container, gamesFragment).addToBackStack(null).commit();
    }

    @Override
    public void onGameCreated() {
        Fragment waitingFragment = new WaitingView();
        fragmentManager.beginTransaction().replace(R.id.main_acttivity_container, waitingFragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.main_acttivity_container);

        if(fragment == null){
            fragment = new LoginView();
            fragmentManager.beginTransaction().add(R.id.main_acttivity_container, fragment).commit();
        }
    }
}

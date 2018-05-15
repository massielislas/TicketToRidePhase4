package root.tickettorideclient;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import root.tickettorideclient.Views.GamesView;
import root.tickettorideclient.Views.LoginView;

public class MainActivity extends AppCompatActivity implements LoginCallback{
    FragmentManager fragmentManager;

    @Override
    public void onLoginSuccess() {
        Fragment gamesFragment = new GamesView();
        fragmentManager.beginTransaction().replace(R.id.main_acttivity_container, gamesFragment).addToBackStack(null).commit();
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

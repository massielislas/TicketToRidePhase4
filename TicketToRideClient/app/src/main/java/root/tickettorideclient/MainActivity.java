package root.tickettorideclient;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import root.tickettorideclient.Views.LoginView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;

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

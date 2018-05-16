package root.tickettorideclient.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/15/2018.
 */

public class WaitingView extends Fragment{
    TextView playerNumber;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_waiting, container, false);
        setUpTextViews(v);
        return v;
    }

    public void setUpTextViews(View v){
        playerNumber = (TextView) getView().findViewById(R.id.playerCounter);
        playerNumber.setText("1000");
    }
}


package root.tickettorideclient.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import root.tickettorideclient.GameActivity;
import root.tickettorideclient.Presenters.IWaitingView;
import root.tickettorideclient.Presenters.WaitingPresenter;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/15/2018.
 */

public class WaitingView extends Fragment implements IWaitingView {

    TextView playerNumber;
    IWaitingPresenter presenter;

    Integer numPlayersInGame;
    Integer maxNumPlayers;
    final String MAX_PLAYERS_KEY = "MaxPlayers";
    final String PLAYERS_JOINED_KEY = "PlayersJoined";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new WaitingPresenter(this, getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        numPlayersInGame = getArguments().getInt(PLAYERS_JOINED_KEY);
        maxNumPlayers = getArguments().getInt(MAX_PLAYERS_KEY);
        View v = inflater.inflate(R.layout.fragment_waiting, container, false);
        setUpTextViews(v);

        if (numPlayersInGame == maxNumPlayers) {
            switchView();
        }

        return v;
    }

    public void setUpTextViews(View v){
        playerNumber = (TextView) v.findViewById(R.id.playerCounter);
        playerNumber.setText(numPlayersInGame.toString() + "/" + maxNumPlayers.toString());
    }

    @Override
    public void updatePlayerCount(Integer numPlayers) {
        numPlayersInGame = numPlayers;
        playerNumber.setText(numPlayersInGame.toString() + "/" + maxNumPlayers.toString());
        if (numPlayersInGame == maxNumPlayers) {
            switchView();
        }
    }

    @Override
    public void switchView() {
        presenter.deregister();
        Toast.makeText(getContext(), "Game is now full- switch to game setup", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), GameActivity.class);
        intent.putExtra("rejoining", false);
        startActivity(intent);
    }



}


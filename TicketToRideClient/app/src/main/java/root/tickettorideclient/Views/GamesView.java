package root.tickettorideclient.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import root.tickettorideclient.IGameJoinedCallback;
import root.tickettorideclient.Presenters.GamesPresenter;
import root.tickettorideclient.Presenters.IGamesView;
import root.tickettorideclient.R;


/**
 * Created by Massiel on 5/14/2018.
 */

public class GamesView extends Fragment implements IGamesView {
    private Spinner playerNumberSpinner;
    private Button createGameButton;
    private RecyclerView gamesRecyclerView;
    private ArrayList<GameListItem>gameListItems = new ArrayList<>();
    private GamesListAdapter gamesListAdapter;
    View view;
    private int numberOfPlayersSelected = 2;
    final String MAX_PLAYERS_KEY = "MaxPlayers";
    final String PLAYERS_JOINED_KEY = "PlayersJoined";

    int joinedGameMaxPlayers = 0;
    int joinedGameJoinedPlayers = 0;

    public ArrayList<GameListItem> getGameListItems() {
        return gameListItems;
    }

    private IGamesPresenter presenter;

    public void setGameListItems(ArrayList<GameListItem> gameListItems) {
        this.gameListItems = gameListItems;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new GamesPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_games, container, false);
        setUpInputs();
        createList();
        return view;
    }

    public void createList(){
        gamesRecyclerView = (RecyclerView) view.findViewById(R.id.gamesRecyclerView);
        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    public void setUpInputs(){
        playerNumberSpinner = (Spinner) view.findViewById(R.id.maxPlayersSpinner);
        playerNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        numberOfPlayersSelected = 2;
                        break;
                    case 1:
                        numberOfPlayersSelected = 3;
                        break;
                    case 2:
                        numberOfPlayersSelected = 4;
                        break;
                    case 3:
                        numberOfPlayersSelected = 5;
                        break;
                    default:
                        numberOfPlayersSelected = 5;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                numberOfPlayersSelected = 5;
            }
        });

        createGameButton = (Button) view.findViewById(R.id.createGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.createGame(numberOfPlayersSelected);
                joinedGameMaxPlayers = numberOfPlayersSelected;
                joinedGameJoinedPlayers = 0;
            }
        });
    }

    public void updateUI(){
        addFakeGames();
        gamesListAdapter = new GamesListAdapter(gameListItems);
        gamesRecyclerView.setAdapter(gamesListAdapter);
    }

    //Test
    public void addFakeGames(){
        for(int i = 0; i < 5; i++){
            GameListItem gameListItem = new GameListItem();
            gameListItem.setGameId(i + "");
            gameListItem.setPlayersJoined((5 - i) + "");
            gameListItem.setMaxPlayers(4+"");
            gameListItems.add(gameListItem);
        }

    }

    @Override
    public void updateGamesList(ArrayList<GameListItem> gameList) {
        this.gameListItems = gameList;
        createList();
    }

    @Override
    public void switchToWaitingView() {
        Bundle bundle = new Bundle();
        bundle.putInt(MAX_PLAYERS_KEY, joinedGameMaxPlayers);
        bundle.putInt(PLAYERS_JOINED_KEY, joinedGameJoinedPlayers);
        ((IGameJoinedCallback) getActivity()).onGameCreated(bundle);
    }


    @Override
    public void popErrorToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public class GameHolder extends RecyclerView.ViewHolder{
        TextView gameDescription;

        public GameHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.game_list_item, parent, false));
            gameDescription = (TextView) itemView.findViewById(R.id.gameListItemText);
        }

        public void bind(final GameListItem gameListItem){
            final String textToSet = "Game " + gameListItem.getGameId() + "\n" +
                    "Players joined" + gameListItem.getPlayersJoined() + "/" + gameListItem.getMaxPlayers();
            gameDescription.setText(textToSet);
            gameDescription.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), textToSet, Toast.LENGTH_LONG).show();
                    joinedGameJoinedPlayers = Integer.valueOf(gameListItem.getPlayersJoined());
                    joinedGameMaxPlayers = Integer.valueOf(gameListItem.getMaxPlayers());
                }
            });
        }
    }

    public class GamesListAdapter extends RecyclerView.Adapter<GameHolder>{
        private ArrayList<GameListItem>games;

        public GamesListAdapter(ArrayList<GameListItem>gameListItems){
            games = gameListItems;
        }

        @Override
        public GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new GameHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(GameHolder holder, int position) {
            GameListItem gameListItem = games.get(position);
            holder.bind(gameListItem);
        }

        @Override
        public int getItemCount() {
            return games.size();
        }
    }
}

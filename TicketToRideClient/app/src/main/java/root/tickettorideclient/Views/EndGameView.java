package root.tickettorideclient.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import root.tickettorideclient.Presenters.EndGamePresenter;
import root.tickettorideclient.Presenters.IEndGameView;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 6/4/2018.
 */

public class EndGameView extends Fragment implements IEndGameView {
    final String POINTS_FROM_CLAIMED_ROUTES = "Points from claimed routes: ";
    final String POINTS_FROM_REACHED_DESTINATIONS = "Points from reached destinations: ";
    final String POINTS_FROM_UNREACHED_DESTINATIONS = "Points from unreached destinations: ";
    final String TOTAL_POINTS = "TOTAL POINTS: ";

    TextView nameWinner;
    TextView longestRouteWinner;
    TextView pointsFromClaimedRoutesWinner;
    TextView pointsFromReachedDestinationsWinner;
    TextView pointsLostFromDestinationsWinner;
    TextView totalPointsWinner;
    View v;
    RecyclerView otherFinalStatsRecyclerView;
    ArrayList<PlayerFinalStats> playerFinalStats = new ArrayList<>();

    PlayerFinalStatsAdapter finalStatsAdapter;

    IEndGamePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_end_game, container, false);
        setUpInputs();
        createList();

        presenter = new EndGamePresenter(this, getActivity());

        return v;
    }

    private void setUpInputs(){
        nameWinner = (TextView) v.findViewById(R.id.winnerName);
        longestRouteWinner = (TextView) v.findViewById(R.id.longestRouteWinner);
        pointsFromClaimedRoutesWinner = (TextView) v.findViewById(R.id.pointsFromClaimedRoutesWinner);
        pointsFromReachedDestinationsWinner = (TextView) v.findViewById(R.id.pointsFromReachedDestinationsWinner);
        pointsLostFromDestinationsWinner = (TextView) v.findViewById(R.id.pointsLostFromUnreachedDestinationsWinner);
        totalPointsWinner = (TextView) v.findViewById(R.id.totalPointsWinner);
    }

    private void createList(){
        otherFinalStatsRecyclerView = (RecyclerView) v.findViewById(R.id.playerFinalStatsRecyclerView);
        otherFinalStatsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void addFakeData(){
        for(int i = 0; i < 4; i++){
            PlayerFinalStats finalStats = new PlayerFinalStats();
            finalStats.setName("Massiel" + i);
            finalStats.setClaimedRoutesPoints(i);
            finalStats.setLongestRoutePoints(i);
            finalStats.setLostDestinations(i);
            finalStats.setReachedDestinationsPoints(i);
            finalStats.setTotalPoints(i);
            playerFinalStats.add(finalStats);
        }
    }

    private void updateUI(){
       // addFakeData();
        finalStatsAdapter = new PlayerFinalStatsAdapter(playerFinalStats);
        otherFinalStatsRecyclerView.setAdapter(finalStatsAdapter);
    }

    @Override
    public void updatePlayers(ArrayList<PlayerFinalStats> players) {
        playerFinalStats = players;
        updateUI();
    }

    @Override
    public void updateWinner(PlayerFinalStats winner) {
        nameWinner.setText(winner.getName());
        longestRouteWinner.setText( ((Integer) winner.getLongestRoutePoints()).toString());
        pointsFromClaimedRoutesWinner.setText( ((Integer) winner.getClaimedRoutesPoints()).toString());
        pointsFromReachedDestinationsWinner.setText( ((Integer) winner.getReachedDestinationsPoints()).toString());
        pointsLostFromDestinationsWinner.setText( ((Integer) winner.getLostDestinations()).toString());
        totalPointsWinner.setText( ((Integer) winner.getTotalPoints()).toString());

    }

    @Override
    public void popErrorToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public class PlayerFinalStatsHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView longestRoute;
        TextView pointsFromClaimedRoutes;
        TextView pointsFromReachedDestinations;
        TextView pointsLostFromDestinations;
        TextView totalPoints;

        public PlayerFinalStatsHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.final_stats_item, parent, false));
            name = (TextView) itemView.findViewById(R.id.playerName);
            longestRoute = (TextView) itemView.findViewById(R.id.longestRoute);
            pointsFromClaimedRoutes = (TextView) itemView.findViewById(R.id.pointsFromClaimedRoutes);
            pointsFromReachedDestinations = (TextView) itemView.findViewById(R.id.pointsFromReachedDestinations);
            pointsLostFromDestinations = (TextView) itemView.findViewById(R.id.pointsLostFromUnreachedDestinations);
            totalPoints = (TextView) itemView.findViewById(R.id.totalPoints);
        }

        public void bind(final PlayerFinalStats playerFinalStats){
            name.setText(playerFinalStats.getName());
            pointsFromClaimedRoutes.setText(POINTS_FROM_CLAIMED_ROUTES + playerFinalStats.getClaimedRoutesPoints());
            pointsLostFromDestinations.setText(POINTS_FROM_UNREACHED_DESTINATIONS + playerFinalStats.getLostDestinations());
            pointsFromReachedDestinations.setText(POINTS_FROM_REACHED_DESTINATIONS + playerFinalStats.getReachedDestinationsPoints());
            totalPoints.setText(TOTAL_POINTS + playerFinalStats.getTotalPoints());

        }
    }

    public class PlayerFinalStatsAdapter extends RecyclerView.Adapter<PlayerFinalStatsHolder>{
        ArrayList<PlayerFinalStats>finalStats;

        public PlayerFinalStatsAdapter(ArrayList<PlayerFinalStats>finalStats){
            this.finalStats = finalStats;
        }

        @Override
        public PlayerFinalStatsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PlayerFinalStatsHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayerFinalStatsHolder holder, int position) {
            PlayerFinalStats playerFinalStats = finalStats.get(position);
            holder.bind(playerFinalStats);

        }

        @Override
        public int getItemCount() {
            return finalStats.size();
        }
    }
}

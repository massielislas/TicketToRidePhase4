package root.tickettorideclient.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Model.InGameModels.City;
import Model.InGameModels.DestinationCard;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/26/2018.
 */

public class DrawDestinationCardsView extends Fragment{
    View v;
    RecyclerView cardListRecyclerView;
    DestinationsAdapter destinationsAdapter;
    ArrayList<DestinationCard> userDestinationCards = new ArrayList<>();
    int selectedColor;
    int nonSelectedColor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedColor = ContextCompat.getColor(getContext(), R.color.selectedCardColor);
        nonSelectedColor = ContextCompat.getColor(getContext(), R.color.unselectedCardColor);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_destination_cards, container, false);
        createList();
        return v;
    }

    public void createList(){
        cardListRecyclerView = (RecyclerView) v.findViewById(R.id.destinationCardsRecyclerView);
        cardListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    public void updateUI(){
        addFakeDestinations();
        destinationsAdapter = new DestinationsAdapter(userDestinationCards);
        cardListRecyclerView.setAdapter(destinationsAdapter);
    }

    public void addFakeDestinations(){
        for(int i = 0; i < 3; i++){
            City city1 = new City("City" + i, 0.0, 0.0);
            City city2 = new City("City" + (i + 1), 0.0
                    , 0.0);
            DestinationCard destinationCard = new DestinationCard(city1, city2, i, i);
            userDestinationCards.add(destinationCard);
        }
    }

    public class DestinationCardHolder extends RecyclerView.ViewHolder{
        TextView destinationCardTextView;
        TextView completion;

        public DestinationCardHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.destination_card_item, parent, false));
            destinationCardTextView = (TextView) itemView.findViewById(R.id.destinationCardItem);
            completion = (TextView) itemView.findViewById(R.id.completion);
        }

        public void bind(final DestinationCard destinationCard){
            String route = destinationCard.getCity1().getName() + " to " + destinationCard.getCity2().getName() + "\n";
            route += "Points: "  + destinationCard.getPointValue();
            destinationCardTextView.setText(route);
            final int destinationCardColor = destinationCardTextView.getDrawingCacheBackgroundColor();
            destinationCardTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     if(destinationCardTextView.getDrawingCacheBackgroundColor() == selectedColor){
                         destinationCardTextView.setBackgroundColor(nonSelectedColor);
                     }
                     else
                         destinationCardTextView.setBackgroundColor(selectedColor);
                }
            });
            completion.setText("");
        }
    }

    public class DestinationsAdapter extends RecyclerView.Adapter<DestinationCardHolder>{
        private ArrayList<DestinationCard>destinationCards;

        public DestinationsAdapter(ArrayList<DestinationCard>destinationCards){
            this.destinationCards = destinationCards;
        }

        @Override
        public DestinationCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DestinationCardHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(DestinationCardHolder holder, int position) {
            DestinationCard destinationCard = destinationCards.get(position);
            holder.bind(destinationCard);
        }

        @Override
        public int getItemCount() {
            return destinationCards.size();
        }
    }
}

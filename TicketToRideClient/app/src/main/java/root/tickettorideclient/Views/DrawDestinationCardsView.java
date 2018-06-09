package root.tickettorideclient.Views;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Model.InGameModels.City;
import Model.InGameModels.DestinationCard;
import root.tickettorideclient.Presenters.DrawDestinationCardsPresenter;
import root.tickettorideclient.Presenters.IDrawDestinationCardsView;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/26/2018.
 */

public class DrawDestinationCardsView extends Fragment implements IDrawDestinationCardsView {
    View v;
    RecyclerView cardListRecyclerView;
    DestinationsAdapter destinationsAdapter;
    ArrayList<DestinationCard> userDestinationCards = new ArrayList<>();
    int selectedColor;
    int nonSelectedColor;
    Map<DestinationCard, Boolean> destinationCardsSelected = new HashMap<>();

    IDrawDestinationPresenter presenter;

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
        presenter = new DrawDestinationCardsPresenter(this, getActivity());
        createList();
        return v;
    }

    public void createList(){
        cardListRecyclerView = (RecyclerView) v.findViewById(R.id.destinationCardsRecyclerView);
        cardListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    private void createDestinationCardsSelected(){
        destinationCardsSelected = new HashMap<>();
        for(int i = 0; i < userDestinationCards.size(); i++){
            destinationCardsSelected.put(userDestinationCards.get(i), false);
        }
    }

    public void updateUI(){
       // userDestinationCards = presenter.getChoices();
       // addFakeDestinations();
        createDestinationCardsSelected();
        destinationsAdapter = new DestinationsAdapter(userDestinationCards);
        cardListRecyclerView.setAdapter(destinationsAdapter);
    }

    public void addFakeDestinations(){
        if ((userDestinationCards == null) || (userDestinationCards.size() != 3)) {
            userDestinationCards = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                City city1 = new City("City" + i, 0.0, 0.0);
                City city2 = new City("City" + (i + 1), 0.0
                        , 0.0);
                DestinationCard destinationCard = new DestinationCard(city1, city2, i, i);
                userDestinationCards.add(destinationCard);
            }
            popToast("Auto generated choices");
        }
    }

    @Override
    public void popToast (String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateDestCards(ArrayList<DestinationCard> cards) {
        userDestinationCards = cards;
        createList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        
        ArrayList<DestinationCard>destinationCardsDiscarded = new ArrayList<>();

        Iterator it = destinationCardsSelected.keySet().iterator();

        while(it.hasNext()){
            DestinationCard currentKey = (DestinationCard) it.next();
            if(destinationCardsSelected.get(currentKey) == false)
                destinationCardsDiscarded.add(currentKey);
        }
        presenter.returnDestCards(destinationCardsDiscarded);
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
            destinationCardTextView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View view) {
                    ColorDrawable cd = (ColorDrawable) destinationCardTextView.getBackground();
                    int colorCode = cd.getColor();
                     if(colorCode == selectedColor){
                         destinationCardTextView.setBackgroundColor(nonSelectedColor);
                         destinationCardsSelected.put(destinationCard, false);
                     }
                     else{
                         destinationCardsSelected.put(destinationCard, true);
                         destinationCardTextView.setBackgroundColor(selectedColor);
                     }
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

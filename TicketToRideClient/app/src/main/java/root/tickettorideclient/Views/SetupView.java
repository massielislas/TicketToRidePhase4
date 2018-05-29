package root.tickettorideclient.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


import Model.InGameModels.DestinationCard;
import Model.InGameModels.TrainCard;
import root.tickettorideclient.Callbacks.IGoToBoardCallback;
import root.tickettorideclient.Presenters.ISetUpView;
import root.tickettorideclient.Presenters.SetUpPresenter;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/21/2018.
 */

public class SetupView extends Fragment implements ISetUpView {
    View v;

    TextView firstDestinationCard;
    TextView secondDestinationCard;
    TextView thirdDestinationCard;

    View firstTrainCard;
    View secondTrainCard;
    View thirdTrainCard;
    View fourthTrainCard;

    TextView playerNumber;
    View playerColor;

    Button readyButton;

    ArrayList<Boolean>destinationCardsSelected = new ArrayList<>();
    ArrayList<DestinationCard> destinationCards = new ArrayList<>();
    int selectedColor;
    int nonSelectedColor;

    ISetUpPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        selectedColor = ContextCompat.getColor(getContext(), R.color.selectedCardColor);
        nonSelectedColor = ContextCompat.getColor(getContext(), R.color.unselectedCardColor);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_setup, container, false);
        setUpInputs();
        setUpSelections();

        presenter = new SetUpPresenter(this, getActivity());
        return v;
    }

    private void setUpInputs(){
        readyButton = (Button) v.findViewById(R.id.readyButton);
        readyButton.setEnabled(false);
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((destinationCards != null) && (destinationCards.size() == 3)) {
                    ArrayList<Integer> keepCards = new ArrayList<>();
                    if ((destinationCardsSelected.size() == 3) && (destinationCards.size() == 3)) {
                        for (int i = 0; i < 3; ++i) {
                            if (destinationCardsSelected.get(i) == true) {
                                keepCards.add(destinationCards.get(i).getID());
                            }
                        }
                    }

                    presenter.keepDestinationCards(keepCards);
                }
                switchToBoardView();
            }
        });

        playerNumber = (TextView) v.findViewById(R.id.playerNum);

        playerColor = (View) v.findViewById(R.id.playerColor);

        setDestinationCardInputs();
    }


    private void setDestinationCardInputs(){
        firstDestinationCard = (TextView) v.findViewById(R.id.firstDestinationCard);
        firstDestinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationCardsSelected.set(0, !destinationCardsSelected.get(0));

                if(destinationCardsSelected.get(0))
                    firstDestinationCard.setBackgroundColor(selectedColor);

                else
                    firstDestinationCard.setBackgroundColor(nonSelectedColor);

                checkDestinationSelections();
            }
        });

        secondDestinationCard = (TextView) v.findViewById(R.id.secondDestinationCard);
        secondDestinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationCardsSelected.set(1, !destinationCardsSelected.get(1));

                if(destinationCardsSelected.get(1))
                    secondDestinationCard.setBackgroundColor(selectedColor);

                else
                    secondDestinationCard.setBackgroundColor(nonSelectedColor);

                checkDestinationSelections();
            }
        });


        thirdDestinationCard = (TextView) v.findViewById(R.id.thirdDestinationCard);
        thirdDestinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationCardsSelected.set(2, !destinationCardsSelected.get(2));

                if(destinationCardsSelected.get(2))
                    thirdDestinationCard.setBackgroundColor(selectedColor);

                else
                    thirdDestinationCard.setBackgroundColor(nonSelectedColor);

                checkDestinationSelections();
            }
        });

        firstTrainCard = (View) v.findViewById(R.id.firstCard);
        secondTrainCard = (View) v.findViewById(R.id.secondCard);
        thirdTrainCard = (View) v.findViewById(R.id.thirdCard);
        fourthTrainCard = (View) v.findViewById(R.id.fourthCard);
    }

    private void checkDestinationSelections(){
        int count = 0;

        if(destinationCardsSelected.get(0))
            count++;

        if(destinationCardsSelected.get(1))
            count++;

        if(destinationCardsSelected.get(2))
            count++;

        if(count > 1)
            readyButton.setEnabled(true);

        else
            readyButton.setEnabled(false);
    }

    private void setUpSelections(){
        for(int i = 0; i < 3; i++){
            destinationCardsSelected.add(false);
        }
    }

    public boolean isDestinationCardSelected(int i){
        return destinationCardsSelected.get(i);
    }

    @Override
    public void setPlayerNumber(Integer num) {
        playerNumber.setText("You are Player " + num);
    }

    @Override
    public void setPlayerColor(String color) {
        switch(color.toLowerCase()) //set color
        {
            case "blue":{
                playerColor.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.playerBlue));
                break;
            }
            case "yellow":{
                playerColor.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.playerYellow));
                break;
            }
            case "green":{
                playerColor.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.playerGreen));
                break;
            }
            case "red":{
                playerColor.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.playerRed));
                break;
            }
            case "purple":{
                playerColor.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.playerPurple));
                break;
            }
            default:{
                playerColor.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                break;
            }
        }

    }

    @Override
    public void setHand(ArrayList<TrainCard> cards) {

        if ((cards != null) && cards.size() == 4) {

            Integer[] colors = new Integer[4];

            for (int i = 0; i < 4; i++) {
                String cardColor = cards.get(i).getColor().toLowerCase();
                switch (cardColor) {
                    case "black":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainBlack);
                        break;
                    case "blue":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainBlue);
                        break;
                    case "green":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainGreen);
                        break;
                    case "orange":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainOrange);
                        break;
                    case "pink":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainPink);
                        break;
                    case "red":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainRed);
                        break;
                    case "white":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainWhite);
                        break;
                    case "wild":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainWild);
                        break;
                    case "yellow":
                        colors[i] = ContextCompat.getColor(getContext(), R.color.trainYellow);
                        break;
                }
            }

            firstTrainCard.setBackgroundColor(colors[0]);
            secondTrainCard.setBackgroundColor(colors[1]);
            thirdTrainCard.setBackgroundColor(colors[2]);
            fourthTrainCard.setBackgroundColor(colors[3]);
        }
    }

    @Override
    public void setDestCards (ArrayList<DestinationCard> cards) {
        if ((cards != null) && (cards.size() == 3)) {

            destinationCards = new ArrayList<>();
            destinationCards.add(cards.get(0));
            destinationCards.add(cards.get(1));
            destinationCards.add(cards.get(2));

            DestinationCard firstCard = destinationCards.get(0);
            String firstMessage = firstCard.getPointValue() + " points: " + firstCard.getCity1() + " to " + firstCard.getCity2();
            firstDestinationCard.setText(firstMessage);

            DestinationCard secondCard = destinationCards.get(1);
            String secondMessage = secondCard.getPointValue() + " points: " + secondCard.getCity1() + " to " + secondCard.getCity2();
            firstDestinationCard.setText(secondMessage);

            DestinationCard thirdCard = destinationCards.get(2);
            String thirdMessage = thirdCard.getPointValue() + " points: " + thirdCard.getCity1() + " to " + thirdCard.getCity2();
            firstDestinationCard.setText(thirdMessage);
        }
    }

    @Override
    public void popToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchToBoardView() {
        ((IGoToBoardCallback) getActivity()).goToBoard();
    }

}

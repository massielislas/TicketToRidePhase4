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
    View fifthTrainCard;

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
        fifthTrainCard = (View) v.findViewById(R.id.fifthCard);
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
    public void setPlayerColor(Integer color) {
        playerColor.setBackgroundColor(color);
    }

    @Override
    public void setHand(TrainCard[] cards) {
        if ((cards != null) && (cards.length == 3)) {
            firstTrainCard.setBackgroundColor(Color.parseColor(cards[0].getColor()));
            secondTrainCard.setBackgroundColor(Color.parseColor(cards[1].getColor()));
            thirdTrainCard.setBackgroundColor(Color.parseColor(cards[2].getColor()));
            fourthTrainCard.setBackgroundColor(Color.parseColor(cards[3].getColor()));
            fifthTrainCard.setBackgroundColor(Color.parseColor(cards[4].getColor()));
        }
    }

    @Override
    public void setDestCards (DestinationCard[] cards) {
        if ((cards != null) && (cards.length == 3)) {

            destinationCards = new ArrayList<>();
            destinationCards.add(cards[0]);
            destinationCards.add(cards[1]);
            destinationCards.add(cards[2]);

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

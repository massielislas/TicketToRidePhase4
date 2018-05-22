package root.tickettorideclient.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import root.tickettorideclient.Callbacks.IGameJoinedCallback;
import root.tickettorideclient.Callbacks.IGoToBoardCallback;
import root.tickettorideclient.Presenters.ISetUpView;
import root.tickettorideclient.Presenters.SetUpPresenter;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/21/2018.
 */

public class SetupView extends Fragment implements ISetUpView {
    View v;

    View firstDestinationCard;
    View secondDestinationCard;
    View thirdDestinationCard;

    Button readyButton;

    ArrayList<Boolean>destinationCardsSelected = new ArrayList<>();
    ArrayList<Boolean>trainCardsSelected = new ArrayList<>();

    String selectedColor = "#b5b5b5";
    String nonSelectedColor = "#f4d76e";

    ISetUpPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setUpSelections();
        presenter = new SetUpPresenter(this, getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_setup, container, false);
        setUpInputs();
        return v;
    }

    private void setUpSelections(){
        for(int i = 0; i < 3; i++){
            destinationCardsSelected.add(false);
        }

        for(int i = 0; i < 5; i++){
            trainCardsSelected.add(false);
        }
    }

    private void setUpInputs(){
        readyButton = (Button) v.findViewById(R.id.readyButton);
        readyButton.setEnabled(false);
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //presenter.keepDestinationCards();
                switchToBoardView();
            }
        });

        setDestinationCardInputs();
    }


    private void setDestinationCardInputs(){
        firstDestinationCard = (View) v.findViewById(R.id.firstDestinationCard);
        firstDestinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationCardsSelected.set(0, !destinationCardsSelected.get(0));

                if(destinationCardsSelected.get(0))
                    firstDestinationCard.setBackgroundColor(Color.parseColor(selectedColor));

                else
                    firstDestinationCard.setBackgroundColor(Color.parseColor(nonSelectedColor));

                checkDestinationSelections();
            }
        });

        secondDestinationCard = (View) v.findViewById(R.id.secondDestinationCard);
        secondDestinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationCardsSelected.set(1, !destinationCardsSelected.get(1));

                if(destinationCardsSelected.get(1))
                    secondDestinationCard.setBackgroundColor(Color.parseColor(selectedColor));

                else
                    secondDestinationCard.setBackgroundColor(Color.parseColor(nonSelectedColor));

                checkDestinationSelections();
            }
        });


        thirdDestinationCard = (View) v.findViewById(R.id.thirdDestinationCard);
        thirdDestinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destinationCardsSelected.set(2, !destinationCardsSelected.get(2));

                if(destinationCardsSelected.get(2))
                    thirdDestinationCard.setBackgroundColor(Color.parseColor(selectedColor));

                else
                    thirdDestinationCard.setBackgroundColor(Color.parseColor(nonSelectedColor));

                checkDestinationSelections();
            }
        });
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

    public boolean isDestinationCardSelected(int i){
        return destinationCardsSelected.get(i);
    }

    @Override
    public void setPlayerNumber(Integer num) {
        //TODO
    }

    @Override
    public void setPlayerColor(Color color) {
        //TODO
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

package root.tickettorideclient.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/21/2018.
 */

public class SetupView extends Fragment {
    View v;

    View firstDestinationCard;
    View secondDestinationCard;
    View thirdDestinationCard;
    
    Button readyButton;

    ArrayList<Boolean>destinationCardsSelected = new ArrayList<>();
    ArrayList<Boolean>trainCardsSelected = new ArrayList<>();

    String selectedColor = "#b5b5b5";
    String nonSelectedColor = "#f4d76e";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setUpSelections();
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

            }
        });

        setDestinationCardInputs();
    }


    private void setDestinationCardInputs(){
        firstDestinationCard.findViewById(R.id.firstDestinationCard);
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

        secondDestinationCard.findViewById(R.id.secondDestinationCard);
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


        thirdDestinationCard.findViewById(R.id.thirdDestinationCard);
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

}

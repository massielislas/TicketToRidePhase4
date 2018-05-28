package root.tickettorideclient.Views;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Model.InGameModels.Cities;
import Model.InGameModels.City;
import Model.InGameModels.DestinationCard;
import Model.InGameModels.PlayerShallow;
import Model.InGameModels.Route;
import Model.InGameModels.Routes;
import Model.InGameModels.TrainCard;
import root.tickettorideclient.Callbacks.IDestinationCardsCallback;
import root.tickettorideclient.Callbacks.IDrawDestinationsCallback;
import root.tickettorideclient.Presenters.BoardPresenter;
import root.tickettorideclient.Presenters.IBoardView;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/21/2018.
 */

public class BoardView extends Fragment implements OnMapReadyCallback, IBoardView {

    IBoardPresenter presenter;

    GoogleMap myGoogleMap;
    MapView myMapView;
    View myView;

    TextView userPointsBanner;
    TextView userTrainsBanner;

    TextView yourHandBanner;
    LinearLayout yourHandDisplay;
    TextView playerPinkCards;
    TextView playerWhiteCards;
    TextView playerBlueCards;
    TextView playerYellowCards;
    TextView playerOrangeCards;
    TextView playerBlackCards;
    TextView playerRedCards;
    TextView playerGreenCards;
    TextView playerWildCards;

    TextView availableCardsBanner;
    LinearLayout availableCardsDisplay;
    TextView trainCardsDeck;
    TextView destinationCardsDeck;
    View faceUpCard1;
    View faceUpCard2;
    View faceUpCard3;
    View faceUpCard4;
    View faceUpCard5;
    View trainCardsLeftInDeck;

    TextView viewYourDestinationCardsBanner;

    TextView chatboxBanner;
    LinearLayout chatBoxContainer;
    TextView chatBox;
    EditText typedMessage;
    Button sendMessageButton;

    TextView otherPlayerBanner;
    RecyclerView otherPlayerRecyclerView;

    ArrayList<PlayerStats> otherPlayers = new ArrayList<>();
    ArrayList<City> cities;
    ArrayList<Route> routes;
    Map<City, Marker>markers = new HashMap<>();
    Map<Route, Polyline>lines = new HashMap<>();

    private OtherPlayerAdapter playerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BoardPresenter(this, getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_board, container, false);
        setUpTopInputs();
        setUpBottomInputs();
        createRecyclerView();

        //For testing - use update pattern for real thing
        this.cities = new ArrayList<>(Cities.getInstance().getCities());
        //draw cities
        this.routes = new ArrayList<>(new Routes().getRouteList());
        //draw routes

        return myView;
    }

    public void createRecyclerView(){
        otherPlayerRecyclerView = (RecyclerView) myView.findViewById(R.id.otherPlayersRecyclerView);
        otherPlayerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    public void updateUI(){
       addFakePlayers();
        playerAdapter = new OtherPlayerAdapter(otherPlayers);
        otherPlayerRecyclerView.setAdapter(playerAdapter);
    }

    public void addFakePlayers(){
        for(int i = 0; i < 4; i++){
            PlayerStats playerStats = new PlayerStats();
            playerStats.setUsername("User" + i);
            playerStats.setDestinationCards(i);
            playerStats.setTrainCards(i);
            playerStats.setTrainPieces(i);
            playerStats.setPoints(i);
            otherPlayers.add(playerStats);
        }
    }

    public void setUpTopInputs(){
        userPointsBanner = (TextView) myView.findViewById(R.id.pointsDisplay);
        userTrainsBanner = (TextView) myView.findViewById(R.id.trainDisplay);

        yourHandDisplay = (LinearLayout) myView.findViewById(R.id.yourHandDisplay);
        yourHandBanner = (TextView)myView.findViewById(R.id.yourHandBanner);
        yourHandBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yourHandDisplay.getVisibility() == View.VISIBLE)
                    yourHandDisplay.setVisibility(View.GONE);

                else
                    yourHandDisplay.setVisibility(View.VISIBLE);
            }
        });

        playerPinkCards = (TextView) myView.findViewById(R.id.playerPinkCards);
        playerWhiteCards = (TextView) myView.findViewById(R.id.playerWhiteCards);
        playerBlueCards = (TextView) myView.findViewById(R.id.playerBlueCards);
        playerYellowCards = (TextView) myView.findViewById(R.id.playerYellowCards);
        playerOrangeCards = (TextView) myView.findViewById(R.id.playerOrangeCards);
        playerBlackCards = (TextView) myView.findViewById(R.id.playerBlackCards);
        playerRedCards = (TextView) myView.findViewById(R.id.playerRedCards);
        playerWildCards = (TextView) myView.findViewById(R.id.playerWildCards);
        playerGreenCards = (TextView) myView.findViewById(R.id.playerGreenCards);

        availableCardsDisplay = (LinearLayout) myView.findViewById(R.id.availableCardsDisplay);
        availableCardsBanner = (TextView)myView.findViewById(R.id.availableCardsBanner);
        availableCardsBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(availableCardsDisplay.getVisibility() == View.VISIBLE)
                    availableCardsDisplay.setVisibility(View.GONE);
                else
                    availableCardsDisplay.setVisibility(View.VISIBLE);
            }
        });

        trainCardsDeck = (TextView)myView.findViewById(R.id.trainCardsDeck);
        destinationCardsDeck = (TextView)myView.findViewById(R.id.destinationCardsDeck);
        destinationCardsDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IDrawDestinationsCallback) getActivity()).goToDrawDestinations();
            }
        });

        faceUpCard1 = (View) myView.findViewById(R.id.faceUpCard1);
        faceUpCard2 = (View) myView.findViewById(R.id.faceUpCard2);
        faceUpCard3 =  (View) myView.findViewById(R.id.faceUpCard3);
        faceUpCard4 =  (View) myView.findViewById(R.id.faceUpCard4);
        faceUpCard5 = (View) myView.findViewById(R.id.faceUpCard5);
        trainCardsLeftInDeck = (View) myView.findViewById(R.id.trainCardsLeftInDeck);

        otherPlayerBanner = (TextView) myView.findViewById(R.id.otherPlayersBanner);
        otherPlayerBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otherPlayerRecyclerView.getVisibility() == View.VISIBLE)
                    otherPlayerRecyclerView.setVisibility(View.GONE);
                else
                    otherPlayerRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        viewYourDestinationCardsBanner = (TextView) myView.findViewById(R.id.viewYourDestinationCardsBanner);
        viewYourDestinationCardsBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IDestinationCardsCallback) getActivity()).goToDestinationCards();
            }
        });
    }

    public void setUpBottomInputs(){
        chatBoxContainer = (LinearLayout)myView.findViewById(R.id.chatboxContainer);
        chatBox = (TextView) myView.findViewById(R.id.chatBox);
        typedMessage = (EditText) myView.findViewById(R.id.typeMessageLine);

        sendMessageButton = (Button) myView.findViewById(R.id.submitMessageButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!typedMessage.getText().toString().equals("")) {
                    presenter.sendChat(typedMessage.getText().toString());
                    typedMessage.setText("");
                }
            }
        });

        chatboxBanner = (TextView)myView.findViewById(R.id.chatAndHistoryBanner);
        chatboxBanner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(chatBoxContainer.getVisibility() == View.VISIBLE)
                    chatBoxContainer.setVisibility(View.GONE);
                else
                    chatBoxContainer.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myMapView = (MapView) myView.findViewById(R.id.map);

        if(myMapView != null){
            myMapView.onCreate(null);
            myMapView.onResume();
            myMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        myGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = myGoogleMap.getUiSettings();
        uiSettings.setZoomGesturesEnabled(true);
        drawCities();
        drawRoutes();
        zoomToCenter();
    }

    public void zoomToCenter(){
        CameraUpdate centerOn =
                CameraUpdateFactory.newLatLng(new LatLng(39.8283, -98.5795));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(3);
        myGoogleMap.moveCamera(centerOn);
        myGoogleMap.animateCamera(zoom);
    }


    @Override
    public void addAllHistory(ArrayList<String> messages) {
        chatBox.setText("");
        for (int i = 0; i < messages.size(); ++i) {
            String text = chatBox.getText() + "\n" + messages.get(i);
            chatBox.setText(text);
        }
    }

    @Override
    public void addOneHistory(String message) {
        String text = chatBox.getText() + "\n" + message;
        chatBox.setText(text);
    }

    @Override
    public void updateHand(ArrayList<TrainCard> cards) {
       Integer black = 0;
       Integer blue = 0;
       Integer green = 0;
       Integer orange = 0;
       Integer pink = 0;
       Integer red = 0;
       Integer white = 0;
       Integer wild = 0;
       Integer yellow = 0;

       for (int i = 0; i < cards.size(); ++i ) {
           TrainCard card = cards.get(i);
           String cardColor = card.getColor().toLowerCase();

           switch (cardColor) {
               case "black":
                    black = black + 1;
                    break;
               case "blue":
                    blue = blue + 1;
                    break;
               case "green":
                    green = green + 1;
                    break;
               case "orange":
                    orange = orange + 1;
                    break;
               case "pink":
                    pink = pink + 1;
                    break;
               case "red":
                    red = red + 1;
                    break;
               case "white":
                    white = white + 1;
                    break;
               case "wild":
                    wild = wild + 1;
                    break;
               case "yellow":
                    yellow = yellow + 1;
                    break;
           }
       }

        playerBlackCards.setText(black.toString());
        playerBlueCards.setText(blue.toString());
        playerGreenCards.setText(green.toString());
        playerOrangeCards.setText(orange.toString());
        playerPinkCards.setText(pink.toString());
        playerRedCards.setText(red.toString());
        playerWhiteCards.setText(white.toString());
        playerWildCards.setText(wild.toString());
        playerYellowCards.setText(yellow.toString());

    }

    @Override
    public void updatePlayerPoints(String playerID, Integer points) {
        for (int i = 0; i < otherPlayers.size(); ++i) {
            if (otherPlayers.get(i).getUsername().equals(playerID)) {
                otherPlayers.get(i).setPoints(points);
                return;
            }
        }
        userPointsBanner.setText("POINTS: " + points);
    }

    @Override
    public void updatePlayerPieces(String playerID, Integer pieces) {
        for (int i = 0; i < otherPlayers.size(); ++i) {
            if (otherPlayers.get(i).getUsername().equals(playerID)) {
                otherPlayers.get(i).setTrainPieces(pieces);
                return;
            }
        }
        userPointsBanner.setText("TRAINS: " + pieces + "/45");
    }

    @Override
    public void updatePlayerTrainCards(String playerID, Integer cards) {
        for (int i = 0; i < otherPlayers.size(); ++i) {
            if (otherPlayers.get(i).getUsername().equals(playerID)) {
                otherPlayers.get(i).setTrainCards(cards);
            }
        }
    }

    @Override
    public void updatePlayerDestCards(String playerID, Integer cards) {
        for (int i = 0; i < otherPlayers.size(); ++i) {
            if (otherPlayers.get(i).getUsername().equals(playerID)) {
                otherPlayers.get(i).setDestinationCards(cards);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void updateFaceUp(ArrayList<TrainCard> cards) {
        //TODO: COLORS
        if (cards.size() > 0) {
            faceUpCard1.setBackgroundColor(Color.parseColor(cards.get(0).getColor()));

        }
        if (cards.size() > 1) {
            faceUpCard2.setBackgroundColor(Color.parseColor(cards.get(1).getColor()));

        }
        if (cards.size() > 2) {
            faceUpCard3.setBackgroundColor(Color.parseColor(cards.get(2).getColor()));

        }
        if (cards.size() > 3) {
            faceUpCard4.setBackgroundColor(Color.parseColor(cards.get(3).getColor()));
        }
        if (cards.size() > 4) {
            faceUpCard5.setBackgroundColor(Color.parseColor(cards.get(4).getColor()));

        }
    }


    @Override
    public void updateDestinationDeck(Integer cardCount) {
        destinationCardsDeck.setText(cardCount + " Destination Cards");
    }

    @Override
    public void updateTrainDeck(Integer cardCount) {
        trainCardsDeck.setText(cardCount + " Train Cards");
    }

    @Override
    public void addAllCities (ArrayList<City> cities) {
        this.cities = cities;
        //drawCities
    }

    @Override
    public void addAllRoutes (ArrayList<Route> routes) {
        this.routes = routes;
        //drawRoutes
    }

    @Override
    public void addAllPlayers (ArrayList<PlayerStats> players) {
        this.otherPlayers = players;
    }

    @Override
    public void popToast(String message) {
        Toast toast = Toast.makeText(getContext(),message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void switchToEndView() {
        //TODO: implement in next phase
        popToast("Game ended: switch to end view");
    }

    @Override
    public Integer getNumCities() {return cities.size();}

    @Override
    public Integer getNumPlayers() {return otherPlayers.size(); }

    @Override
    public Integer getNumRoutes() {return routes.size(); }


    public void drawCities(){
        float color = BitmapDescriptorFactory.HUE_RED;
        for(int i = 0; i < cities.size(); i++){
            LatLng latLng = new LatLng(cities.get(i).getLatitude(), cities.get(i).getLongitude());
            Marker marker = myGoogleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(color)));
            markers.put(cities.get(i), marker);
        }
    }

    public void drawRoutes(){
        for(int i = 0; i < routes.size(); i++){
            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.add(new LatLng(
                    routes.get(i).getCity1().getLatitude(), routes.get(i).getCity1().getLongitude()),
                    new LatLng(routes.get(i).getCity2().getLatitude(), routes.get(i).getCity2().getLongitude()));
            polylineOptions.width(7);
            String color = routes.get(i).getColor();
            switch (color){
                case "Gray":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainGray));
                    break;
                case "Yellow":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainYellow));
                    break;
                case "Blue":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainBlue));
                    break;
                case "Green":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainGreen));
                    break;
                case "Pink":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainPink));
                    break;
                case "Black":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainBlack));
                    break;
                case "Orange":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainOrange));
                    break;
                case "White":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainWhite));
                    break;
                case "Red":
                    polylineOptions.color(ContextCompat.getColor(getContext(), R.color.trainRed));
                    break;
                default:
                    polylineOptions.color(Color.CYAN);
                    break;
            }
            lines.put(routes.get(i), myGoogleMap.addPolyline(polylineOptions));

            if(routes.get(i).isDouble()){
                double doubleRouteOffset = .25;
                PolylineOptions polylineOptions2 = new PolylineOptions();
                polylineOptions2.add(new LatLng(
                                routes.get(i).getCity1().getLatitude()+doubleRouteOffset, routes.get(i).getCity1().getLongitude()+doubleRouteOffset),
                        new LatLng(routes.get(i).getCity2().getLatitude()+doubleRouteOffset, routes.get(i).getCity2().getLongitude()+doubleRouteOffset));
                polylineOptions2.width(10);
                String color2 = routes.get(i).getDoubleColor();
                switch (color2){
                    case "Gray":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainGray));
                        break;
                    case "Yellow":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainYellow));
                        break;
                    case "Blue":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainBlue));
                        break;
                    case "Green":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainGreen));
                        break;
                    case "Pink":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainPink));
                        break;
                    case "Black":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainBlack));
                        break;
                    case "Orange":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainOrange));
                        break;
                    case "White":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainWhite));
                        break;
                    case "Red":
                        polylineOptions2.color(ContextCompat.getColor(getContext(), R.color.trainRed));
                        break;
                    default:
                        polylineOptions2.color(Color.CYAN);
                        break;
                }
                lines.put(routes.get(i), myGoogleMap.addPolyline(polylineOptions2));
            }
        }
    }

    public class OtherPlayerHolder extends RecyclerView.ViewHolder{
        TextView trainPieces;
        TextView trainCards;
        TextView destinationCards;
        TextView username;
        TextView points;
        public OtherPlayerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.other_player_stats_item, parent, false));
            trainPieces = (TextView) itemView.findViewById(R.id.otherPlayerTrainPieces);
            trainCards = (TextView) itemView.findViewById(R.id.otherPlayerTrainCards);
            destinationCards = (TextView) itemView.findViewById(R.id.otherPlayerDestinationCards);
            username = (TextView) itemView.findViewById(R.id.otherPlayerUsername);
            points = (TextView) itemView.findViewById(R.id.otherPlayerPoints);
        }

        public void bind(final PlayerStats playerStats){
            username.setText(playerStats.getUsername());
            trainPieces.setText("T:" + playerStats.getTrainPieces());
            trainCards.setText("C:" + playerStats.getTrainCards());
            destinationCards.setText("D:" + playerStats.getDestinationCards());
            points.setText("P:" + playerStats.getPoints());
        }
    }

    public class OtherPlayerAdapter extends RecyclerView.Adapter<OtherPlayerHolder>{
        private ArrayList<PlayerStats>playerStats;

        public OtherPlayerAdapter(ArrayList<PlayerStats> playerStats){
            this.playerStats = playerStats;
        }

        @Override
        public OtherPlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new OtherPlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(OtherPlayerHolder holder, int position) {
            PlayerStats playerStats = this.playerStats.get(position);
            holder.bind(playerStats);
        }

        public int getItemCount(){
            return playerStats.size();
        }
    }
}

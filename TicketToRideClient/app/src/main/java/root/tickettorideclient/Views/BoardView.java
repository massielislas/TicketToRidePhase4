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
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
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
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import root.tickettorideclient.Callbacks.IEndGameCallback;
import root.tickettorideclient.Presenters.BoardPresenter;
import root.tickettorideclient.Presenters.IBoardView;
import root.tickettorideclient.R;

/**
 * Created by Massiel on 5/21/2018.
 */

public class BoardView extends Fragment implements OnMapReadyCallback, IBoardView, GoogleMap.OnInfoWindowClickListener{

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

    TextView playersTurnText;

    TextView viewYourDestinationCardsBanner;

    TextView chatboxBanner;
    LinearLayout chatBoxContainer;
    TextView chatBox;
    EditText typedMessage;
    Button sendMessageButton;

    TextView otherPlayerBanner;
    RecyclerView otherPlayerRecyclerView;

    ArrayList<PlayerStats> otherPlayers = new ArrayList<>();
    ArrayList<City> cities = new ArrayList<>();
    ArrayList<Route> routes = new ArrayList<>();
    Map<City, Marker>markers = new HashMap<>();
    Map<Polyline, Integer>lines = new HashMap<>();

    private OtherPlayerAdapter playerAdapter;

    Route routeClicked = null;
    int routeClickedID;

    final int LINE_WIDTH = 10;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_board, container, false);
        setUpTopInputs();
        setUpBottomInputs();
        createRecyclerView();
        return myView;
    }

    public void createRecyclerView(){
        otherPlayerRecyclerView = (RecyclerView) myView.findViewById(R.id.otherPlayersRecyclerView);
        otherPlayerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
    }

    public void updateUI(){
//       addFakePlayers();
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

    public void removeLines(){
        Iterator it = lines.keySet().iterator();

        while(it.hasNext()){
            Polyline polyline = (Polyline) it.next();
            polyline.remove();
        }
    }

    public void setUpTopInputs(){
        userPointsBanner = (TextView) myView.findViewById(R.id.pointsDisplay);
     /*   userPointsBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.test();
            }
        });
    */
        userTrainsBanner = (TextView) myView.findViewById(R.id.trainDisplay);
        userTrainsBanner.setOnClickListener(new View.OnClickListener() {
            //Secret button to take you to end view
            @Override
            public void onClick(View view) {
                ((IEndGameCallback) getActivity()).goToEndGame();
            }
        });

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

        playersTurnText = (TextView) myView.findViewById(R.id.playersTurn);


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
        chatBox.setMovementMethod(new ScrollingMovementMethod());
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
        zoomToCenter();
        polylineOnClickListener();
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.info_window, null);
                TextView textView = (TextView) v.findViewById(R.id.infoWindowText);
                textView.setText(marker.getTitle());
                return v;
            }
        });
        this.cities = new ArrayList<>(Cities.getInstance().getCities());
        this.routes = new ArrayList<>(new Routes().getRouteList());
        drawCities();
        presenter = new BoardPresenter(this, getActivity());
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        presenter.claimRoute(routeClickedID);
    }

    public void polylineOnClickListener(){
        myGoogleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
            @Override
            public void onPolylineClick(Polyline polyline) {
                routeClickedID = lines.get(polyline);
                Marker marker = markers.get(routeClicked.getCity1());
                String addToClaim = "\nClick To Claim!";
                String infoWindowText = routeClicked.getCity1().getName() + " to " + routeClicked.getCity2().getName() + "\n" +
                        "Points: " + routeClicked.getScoreValue() + "\n" +
                        "Length: " + routeClicked.getLength();
                if(!routeClicked.isClaimed()){
                    infoWindowText+=  addToClaim;
                }

                String marker1text = routeClicked.getCity1() + " P:" + routeClicked.getScoreValue() + " L:" + routeClicked.getLength();
                marker.setTitle(marker1text);

                marker.setTitle(infoWindowText);
                marker.showInfoWindow();
            }
        });
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
        updateUI();
    }

    @Override
    public void addOneHistory(String message) {
        String text = chatBox.getText() + "\n" + message;
        chatBox.setText(text);
        updateUI();
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
               case "gray":
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

        updateUI();
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
        updateUI();
    }

    @Override
    public void updatePlayerPieces(String playerID, Integer pieces) {
        for (int i = 0; i < otherPlayers.size(); ++i) {
            if (otherPlayers.get(i).getUsername().equals(playerID)) {
                otherPlayers.get(i).setTrainPieces(pieces);
                return;
            }
        }
        userTrainsBanner.setText("TRAINS: " + pieces + "/45");
        updateUI();
    }

    @Override
    public void updatePlayerTrainCards(String playerID, Integer cards) {
        for (int i = 0; i < otherPlayers.size(); ++i) {
            if (otherPlayers.get(i).getUsername().equals(playerID)) {
                otherPlayers.get(i).setTrainCards(cards);
            }
        }
        updateUI();
    }

    @Override
    public void updatePlayerDestCards(String playerID, Integer cards) {
        for (int i = 0; i < otherPlayers.size(); ++i) {
            if (otherPlayers.get(i).getUsername().equals(playerID)) {
                otherPlayers.get(i).setDestinationCards(cards);
            }
        }
        updateUI();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void updateFaceUp(ArrayList<TrainCard> cards) {
        String[] colors = new String[cards.size()];
        for (int i = 0; i < cards.size(); ++i) {
            colors[i] = cards.get(i).getColor();
        }

        Integer[] colorInts = new Integer[cards.size()];
        for (int i = 0; i < cards.size(); ++i) {
            switch (colors[i].toLowerCase()) {
                case "gray": //aka wild
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainWild);
                    break;
                case "yellow":
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainYellow);
                    break;
                case "blue":
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainBlue);
                    break;
                case "green":
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainGreen);
                    break;
                case "pink":
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainPink);
                    break;
                case "black":
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainBlack);
                    break;
                case "orange":
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainOrange);
                    break;
                case "white":
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainWhite);
                    break;
                case "red":
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.trainRed);
                    break;
                default:
                    colorInts[i] = ContextCompat.getColor(getContext(), R.color.colorPrimary);
                    break;
            }
        }

        if (cards.size() > 0) {
            faceUpCard5.setBackgroundColor(colorInts[0]);

        }
        if (cards.size() > 1) {
            faceUpCard4.setBackgroundColor(colorInts[1]);

        }
        if (cards.size() > 2) {
            faceUpCard3.setBackgroundColor(colorInts[2]);

        }
        if (cards.size() > 3) {
            faceUpCard2.setBackgroundColor(colorInts[3]);
        }
        if (cards.size() > 4) {
            faceUpCard1.setBackgroundColor(colorInts[4]);

        }

        clearFaceUpCards(5 - cards.size());

        updateUI();
    }

    public void clearFaceUpCards (int numEmptySlots) {
        if (numEmptySlots > 0) {
            faceUpCard1.setBackgroundColor(0);
        }
        if (numEmptySlots > 1) {
            faceUpCard2.setBackgroundColor(0);
        }
        if (numEmptySlots > 2) {
            faceUpCard3.setBackgroundColor(0);
        }
        if (numEmptySlots > 3) {
            faceUpCard4.setBackgroundColor(0);
        }
        if (numEmptySlots > 4) {
            faceUpCard5.setBackgroundColor(0);
        }
    }


    @Override
    public void updateDestinationDeck(Integer cardCount) {
        destinationCardsDeck.setText(cardCount + " Destination Cards");
        updateUI();
    }

    @Override
    public void updateTrainDeck(Integer cardCount) {
        trainCardsDeck.setText(cardCount + " Train Cards");
        updateUI();
    }

    @Override
    public void addAllCities (ArrayList<City> cities) {
        this.cities = cities;
    }

    @Override
    public void addAllRoutes (ArrayList<Route> routes) {
        this.routes = routes;
        removeLines();
        drawRoutes();
    }

    @Override
    public void addAllPlayers (ArrayList<PlayerStats> players) {
        this.otherPlayers = players;
        updateUI();
    }

    @Override
    public void updateTurn (String playerID) {
        this.playersTurnText.setText("It is " + playerID + "'s turn.");
        updateUI();
    }

    @Override
    public void popToast(String message) {
        Toast toast = Toast.makeText(getContext(),message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        updateUI();
    }

    @Override
    public void switchToEndView() {
        //TODO: implement in next phase
        popToast("Game ended: switch to end view");
    }

    @Override
    public Integer getNumCities() {
        updateUI();
        return cities.size();}

    @Override
    public Integer getNumPlayers() {
        updateUI();
        return otherPlayers.size(); }

    @Override
    public Integer getNumRoutes() {
        updateUI();
        return routes.size(); }


    public void drawCities(){
        float color = BitmapDescriptorFactory.HUE_RED;
        for(int i = 0; i < cities.size(); i++){
            LatLng latLng = new LatLng(cities.get(i).getLatitude(), cities.get(i).getLongitude());
            Marker marker = myGoogleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(color)));
            markers.put(cities.get(i), marker);
        }
    }

    public Polyline drawLine(City city1, City city2, String color, double offset, List<PatternItem>dashedPattern){
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(
                        city1.getLatitude() + offset, city1.getLongitude() + offset),
                new LatLng(city2.getLatitude() + offset, city2.getLongitude() + offset));
        polylineOptions.width(LINE_WIDTH);
        polylineOptions.pattern(dashedPattern);
        polylineOptions.clickable(true);
        switch (color) {
            case "Gray":
                polylineOptions.color(Color.GRAY);
                break;
            case "Yellow":
                polylineOptions.color(Color.YELLOW);
                break;
            case "Blue":
                polylineOptions.color(Color.BLUE);
                break;
            case "Green":
                polylineOptions.color(Color.GREEN);
                break;
            case "Pink":
                polylineOptions.color(Color.MAGENTA);
                break;
            case "Black":
                polylineOptions.color(Color.BLACK);
                break;
            case "Orange":
                polylineOptions.color(Color.rgb(255, 175, 58));
                break;
            case "White":
                polylineOptions.color(Color.WHITE);
                break;
            case "Red":
                polylineOptions.color(Color.RED);
                break;
            default:
                polylineOptions.color(Color.CYAN);
                break;
        }
        return myGoogleMap.addPolyline(polylineOptions);
    }

    public Polyline drawLine(City city1, City city2, String color, double offset){
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(
                        city1.getLatitude() + offset, city1.getLongitude() + offset),
                new LatLng(city2.getLatitude() + offset, city2.getLongitude() + offset));
        polylineOptions.width(LINE_WIDTH);
        polylineOptions.clickable(true);
        switch (color) {
            case "Gray":
                polylineOptions.color(Color.GRAY);
                break;
            case "Yellow":
                polylineOptions.color(Color.YELLOW);
                break;
            case "Blue":
                polylineOptions.color(Color.BLUE);
                break;
            case "Green":
                polylineOptions.color(Color.GREEN);
                break;
            case "Pink":
                polylineOptions.color(Color.MAGENTA);
                break;
            case "Black":
                polylineOptions.color(Color.BLACK);
                break;
            case "Orange":
                polylineOptions.color(Color.rgb(255, 175, 58));
                break;
            case "White":
                polylineOptions.color(Color.WHITE);
                break;
            case "Red":
                polylineOptions.color(Color.RED);
                break;
            default:
                polylineOptions.color(Color.CYAN);
                break;
        }
        Polyline polyline = myGoogleMap.addPolyline(polylineOptions);
       return polyline;
    }

    public void drawRoutes(){
        removeLines();
        lines.clear();
        int dashGap = 30;
        List<PatternItem> dashedPattern = Arrays.asList(new Dash(dashGap), new Gap(dashGap));
        double doubleRouteOffset = .3;
        for(int i = 0; i < routes.size(); i++){
            Route route = routes.get(i);
            if(!route.isClaimed())
                lines.put(drawLine(route.getCity1(), route.getCity2(), route.getColor(), 0, dashedPattern), route.getID());
            else
                lines.put(drawLine(route.getCity1(), route.getCity2(), route.getColor(), 0), route.getID());

            if(routes.get(i).isDouble()){
                if(!route.isDoubleClaimed())
                    lines.put(drawLine(route.getCity1(), route.getCity2(), route.getDoubleColor(), doubleRouteOffset, dashedPattern), route.getID() * -1);
                else
                    lines.put(drawLine(route.getCity1(), route.getCity2(), route.getDoubleColor(), doubleRouteOffset), route.getID() * -1);
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

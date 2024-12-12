package com.example.mobileprojectvaclavtuma;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    ListView myListView; // ListView instance
    String[] items; // string array with items
    String[] descriptions; // string array with description

    boolean chacked = false;
    private GoogleMap Map;
    //private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Used with toggle switch
        ConstraintLayout mainLayout = findViewById(R.id.design);
        TextView title = findViewById(R.id.title);
        ListView list = findViewById(R.id.myListView);
        Switch switchMode = findViewById(R.id.switchMode);


        Resources res = getResources(); // creation of res variable
        myListView = (ListView) findViewById(R.id.myListView);
        items = res.getStringArray(R.array.items); // look for array called item
        descriptions = res.getStringArray(R.array.descriptions); // look for array called descriptions
        //myListView.setAdapter(new ArrayAdapter<String>(this, R.layout.my_listview_detail, items));// adapter to merge these two files, String - 3 params (this list, what layout file I want to use, array to manage)

        ItemAdapter itemAdapter = new ItemAdapter(this, items, descriptions, chacked); // item adapter I will giv it items, prices, descrition, only referencing
        myListView.setAdapter(itemAdapter); // where I will use it

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailActivity = new Intent(getApplicationContext(), detailActivity.class);
                showDetailActivity.putExtra("com.example.mobileprojectvaclavtuma.ITEM_INDEX", position);// send activity which ID was clicked
                showDetailActivity.putExtra("com.example.mobileprojectvaclavtuma.DARK_MODE", chacked);
                startActivity(showDetailActivity);
            }
        });

        // launch activity outside of my app, it will open Visit Czechia
        Button linkButton = (Button) findViewById(R.id.linkButton);
        linkButton.setOnClickListener(new View.OnClickListener() { // listener to clicking the google button and what will happen after
            @Override
            public void onClick(View v) {
                String visit = "https://www.visitczechia.com/en-us/campaigns/traditions-2022/architecture/most-beautiful-czech-castles";
                Uri webaddress = Uri.parse(visit);

                Intent gotoVisitCzechia = new Intent(Intent.ACTION_VIEW, webaddress);
                startActivity(gotoVisitCzechia);
            }
        });


        // switch
        switchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            chacked = isChecked;
            if (isChecked) { // dark mode
                mainLayout.setBackgroundColor(getResources().getColor(R.color.darkGray));
                title.setTextColor(getResources().getColor(R.color.white));
                list.setBackgroundColor(getResources().getColor(R.color.darkGray));

            } else { // light mode
                mainLayout.setBackgroundColor(getResources().getColor(R.color.white));
                title.setTextColor(getResources().getColor(R.color.black));
                list.setBackgroundColor(getResources().getColor(R.color.white));
            }
            itemAdapter.setDarkMode(isChecked);
        });

        // initialize MAP
        SupportMapFragment fragmentMap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(fragmentMap != null){
            fragmentMap.getMapAsync(this);
        }



    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        Map = googleMap; // inicialized Map
        LatLng Karlstejn = new LatLng(49.93,14.88);// latitude longtitude for Karlstejn
        LatLng cameraView = new LatLng(49.88,15.217);// latitude longtitude for Karlstejn
        Map.addMarker(new MarkerOptions().position(Karlstejn).title("Karlstejn"));
        Map.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraView, 7f)); // change camera to the center of republic and change zoom lvl
    }
}
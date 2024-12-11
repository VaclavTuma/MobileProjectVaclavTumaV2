package com.example.mobileprojectvaclavtuma;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView myListView; //class
    String[] items; // string array with items
    String[] prices; // string array with prices
    String[] descriptions; // string array with description

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Resources res = getResources(); // creation of res variable
        myListView = (ListView) findViewById(R.id.myListView);
        items = res.getStringArray(R.array.items); // look for array called items
        prices = res.getStringArray(R.array.prices); // look for array called prices
        descriptions = res.getStringArray(R.array.descriptions); // look for array called descriptions
        //myListView.setAdapter(new ArrayAdapter<String>(this, R.layout.my_listview_detail, items));// adapter to merge these two files, String - 3 params (this list, what layout file I want to use, array to manage)

        ItemAdapter itemAdapter = new ItemAdapter(this, items, descriptions, prices); // item adapter I will giv it items, prices, descrition, only referencing
        myListView.setAdapter(itemAdapter); // where I will use it

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailActivity = new Intent(getApplicationContext(), detailActivity.class);
                showDetailActivity.putExtra("com.example.mobileprojectvaclavtuma.ITEM_INDEX", position);// send activity which ID was clicked
                startActivity(showDetailActivity);
            }
        });

    }
}
//android:id="@+id/imageView"
package com.example.rajkumar.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class
        ListActivity extends ActionBarActivity {

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] {

                "Bhaggyam Sahridaya",
                "GRN'S Jeevan Brama Enclave",
                "Daffodils",
                "Adroit Sculptra",
                "S & S Ishan",
                "Mylai Meadow",
                "Luz Amor"

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
 if (position==0) {
     Intent intent = new Intent(getApplicationContext(), ProjectDetailActivity.class);
     startActivity(intent);
 }
else
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Sorry Currently Unavailable, Please  Check Bhaggyam Sahridaya", Toast.LENGTH_LONG)
                        .show();

            }

        });
    }

}
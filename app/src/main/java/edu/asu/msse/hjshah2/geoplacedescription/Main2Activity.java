package edu.asu.msse.hjshah2.geoplacedescription;

import android.support.v7.app.ActionBar;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import org.json.JSONException;
import android.content.Intent;
import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    PlaceDescriptionLibrary placeLib;
    ListView listView;
    ArrayList<String> arr ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.listView);

        placeLib = new PlaceDescriptionLibrary(this);
        arr = (ArrayList<String>) placeLib.loadFromJSON(this);

        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(simpleAdapter);
        //listView.setOnClickListener(new){

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) listView.getItemAtPosition(position);
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                intent.putExtra("name", selected);
                startActivity(intent);
            }
        });

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.mainmenu, menu);
            return true;
        }

    }
}

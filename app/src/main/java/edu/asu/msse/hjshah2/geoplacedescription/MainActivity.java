package edu.asu.msse.hjshah2.geoplacedescription;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
/*
 * Copyright 2017 Himani Shah,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * instuctor and the University with the right to build and evaluate the software package for the purpose of determining your grade and program assessment
 *
 * Purpose: Example of single view application
 * Allows to display the objects of a class to access the GUI
 *
 * Ser423 Mobile Applications
 * @author Himani Shah Himani.shah@asu.edu
 *         Software Engineering, CIDSE, ASU Poly
 * @version January 2017
 */
public class MainActivity extends AppCompatActivity {

    EditText name, description, category, adddresstitle, address, elevation, latitude, longitude;
    PlaceDescription PlaceDescriptionObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.displayName);
        description = (EditText)findViewById(R.id.displayDescription);
        category = (EditText)findViewById(R.id.displayCategory);
        adddresstitle = (EditText)findViewById(R.id.displayAddresstitle);
        address = (EditText)findViewById(R.id.displayAddress);
        elevation = (EditText)findViewById(R.id.displayElevation);
        latitude = (EditText)findViewById(R.id.displayLatitude);
        longitude = (EditText)findViewById(R.id.displayLongitude);
        Bundle bundle = getIntent().getExtras();
        String name1 = bundle.getString("name");
        name.setText(name1);
        PlaceDescriptionLibrary pdl = new PlaceDescriptionLibrary(this);

        PlaceDescriptionObject = new PlaceDescription ();
        PlaceDescriptionObject = pdl.getPlaceDescription(name1);
        name.setText(PlaceDescriptionObject.name);
        description.setText(PlaceDescriptionObject.description);
        category.setText(PlaceDescriptionObject.category);
        adddresstitle.setText(PlaceDescriptionObject.addresstitle);
        address.setText(PlaceDescriptionObject.address);
        elevation.setText(String.valueOf(PlaceDescriptionObject.elevation));
        latitude.setText(String.valueOf(PlaceDescriptionObject.latitude));
        longitude.setText(String.valueOf(PlaceDescriptionObject.longitude));


    }
}

package edu.asu.msse.hjshah2.geoplacedescriptiondb;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
 * Purpose: Multiple view Android app for managing PlaceDescriptions.
 *
 * Ser423 Mobile Applications
 * @author Himani Shah Himani.shah@asu.edu
 *         Software Engineering, CIDSE, ASU Poly
 * @version January 2017
 */
public class PlaceDescriptionLibrary implements Serializable {
    protected Hashtable<String, PlaceDescription> places;
    public ArrayList<String> str = new ArrayList<String>();
    Resources resources;
    public PlaceDescriptionLibrary(Context appContext) {
        places = new Hashtable<String, PlaceDescription>();
        InputStream is = appContext.getResources().openRawResource(R.raw.places);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try{
            JSONObject placesJSON = new JSONObject(new JSONTokener(br.readLine()));
            Iterator<String> it = placesJSON.keys();
            while(it.hasNext()) {
                String pTitle = it.next();
                JSONObject aPlace = placesJSON.optJSONObject(pTitle);

                if(aPlace != null) {
                    PlaceDescription md = new PlaceDescription(aPlace, pTitle);
                    places.put(pTitle, md);
                }
            }
        }
        catch(Exception e){
            e.getStackTrace();
        }

    }


    public List<String> loadFromJSON(Context appContext){
        places = new Hashtable<String, PlaceDescription>();
        InputStream is = appContext.getResources().openRawResource(R.raw.places);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try{
            JSONObject placesJSON = new JSONObject(new JSONTokener(br.readLine()));
            Iterator<String> it = placesJSON.keys();
            while(it.hasNext()) {
                String pTitle = it.next();
                JSONObject aPlace = placesJSON.optJSONObject(pTitle);

                if(aPlace != null) {
                    PlaceDescription md = new PlaceDescription(aPlace, pTitle);
                    places.put(pTitle, md);
                    str.add(pTitle);
                }
            }
        }
        catch(Exception e){
            e.getStackTrace();
        }
        return str;
    }

    public List<String> getTitles (Context appContext){
        Iterator<Map.Entry<String, PlaceDescription>> it = places.entrySet().iterator();
        List<String> retstr = new ArrayList<String>();

        while(it.hasNext()) {
            Map.Entry<String,PlaceDescription> entry = it.next();
            retstr.add(entry.getKey());
        }
        return retstr;
    }

    public boolean remove(String aName) {
        //debug("removing student named: " + aName);
        return ((places.remove(aName) == null) ? false : true);
    }

    public void add(String placeTitle, PlaceDescription placeDescriptionObject){
        places.put(placeTitle,placeDescriptionObject);
    }
    public void update(String placeTitle, PlaceDescription placeDescriptionObject){
        places.put(placeTitle, placeDescriptionObject);
    }
    public PlaceDescription getPlaceDescription(String pTitle) {
        return places.get(pTitle);
    }

}

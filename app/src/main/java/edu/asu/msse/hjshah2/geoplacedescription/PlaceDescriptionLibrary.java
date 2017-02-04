package edu.asu.msse.hjshah2.geoplacedescription;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hjshah2 on 1/29/17.
 */
public class PlaceDescriptionLibrary implements Serializable {
    protected Hashtable<String, PlaceDescription> places;
    private static PlaceDescriptionLibrary instance;
    private static final boolean debugOn = true;

    private PlaceDescriptionLibrary() {
        places = new Hashtable<String, PlaceDescription>();
    }

    public synchronized static PlaceDescriptionLibrary initInstance(Context appContext) {
        if(instance == null) {
            instance = new PlaceDescriptionLibrary();
            instance.loadPlaces(appContext);
        }
        return instance;
    }

    public static PlaceDescriptionLibrary getInstance() {
        return instance;
    }

    private void debug(String msg) {
        if(debugOn)
            Log.d(this.getClass().getSimpleName(), msg);
    }

    public void loadPlaces(Context appContext) {
        try {
            places.clear();
            String fileName = "places.json";
            InputStream is = appContext.getResources().openRawResource(R.raw.places);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            JSONObject placesJSON = new JSONObject(new JSONTokener(br.readLine()));
            Iterator<String> it = placesJSON.keys();
            while(it.hasNext()) {
                String pTitle = it.next();
                JSONObject aPlace = placesJSON.optJSONObject(pTitle);
                debug("importing place description titled " + pTitle + " json is " +aPlace.toString());
                if(aPlace != null) {
                    PlaceDescription md = new PlaceDescription(aPlace.toString(), pTitle);
                    places.put(pTitle, md);
                }
            }
        } catch(Exception e) {
            Log.d(this.getClass().getSimpleName(), "Exception while loading places " + e.getMessage());
        }
    }
    public PlaceDescription getPlaceDescription(String pTitle) {
        return places.get(pTitle);
    }

}

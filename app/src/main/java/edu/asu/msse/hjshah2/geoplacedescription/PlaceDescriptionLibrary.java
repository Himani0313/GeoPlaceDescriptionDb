package edu.asu.msse.hjshah2.geoplacedescription;

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

/**
 * Created by hjshah2 on 1/29/17.
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
    public PlaceDescription getPlaceDescription(String pTitle) {
        return places.get(pTitle);
    }

}

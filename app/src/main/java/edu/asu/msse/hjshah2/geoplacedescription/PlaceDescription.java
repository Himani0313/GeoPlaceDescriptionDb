package edu.asu.msse.hjshah2.geoplacedescription;

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

import org.json.JSONObject;

public class PlaceDescription {
    String name;
    String description;
    String category;
    String addresstitle;
    String address;
    double elevation;
    double latitude;
    double longitude;

    PlaceDescription(String s){
        try{
            JSONObject jo = new JSONObject(s);
            name = jo.getString("name");
            description = jo.getString("description");
            category = jo.getString("category");
            addresstitle = jo.getString("addresstitle");
            address = jo.getString("address");
            elevation = jo.getDouble("elevation");
            latitude = jo.getDouble("latitude");
            longitude = jo.getDouble("longitude");
        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }

    }
    public String toJsonString(){
        String ret = "";
        try{
            JSONObject jo = new JSONObject();
            jo.put("name",name);
            jo.put("description",description);
            jo.put("category",category);
            jo.put("addresstitle",addresstitle);
            jo.put("address",address);
            jo.put("elevation",elevation);
            jo.put("latitude", latitude);
            jo.put("longitude", longitude);

        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }
        return ret;
    }
}

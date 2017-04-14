package edu.asu.msse.hjshah2.geoplacedescriptiondb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    int count;
    public PlaceDescriptionLibrary(Context appContext){
        count = 15;
    }
    public PlaceDescription getPlaceDescription(Context context,String placeTitle){
        PlaceDescription pd = null;
        try {
            placeDB db = new placeDB(context);
            SQLiteDatabase crsDB = db.openDB();
            Cursor cur = crsDB.rawQuery("select * from places where name=? ;",
                    new String[]{placeTitle});
            cur.moveToNext();
            pd = new PlaceDescription(cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4),cur.getString(5),cur.getDouble(6),cur.getDouble(7),cur.getDouble(8));
        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception getting place info: "+
                    ex.getMessage());
        }
        return pd;
    }
    public List<String> getTitles(Context context) {
        ArrayList<String> names = new ArrayList<String>();
        try {
            placeDB db = new placeDB(context);
            SQLiteDatabase crsDB = db.openDB();
            Cursor cur = crsDB.rawQuery("select * from PLACES;",null);
            while(cur.moveToNext()){
                names.add(cur.getString(1));
            }
        }
        catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception getting place info: "+
                    ex.getMessage());
        }
        return names;
    }



    public void remove(Context context, String aName) {
        try {
            placeDB db = new placeDB(context);
            SQLiteDatabase crsDB = db.openDB();
            String delete = "delete from places where name='"+ aName +"';";
            crsDB.execSQL(delete);
            crsDB.close();
            db.close();
        }
        catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception deleting place info: "+
                    ex.getMessage());
        }
    }

    public void add(Context context, PlaceDescription placeDescriptionObject){
        try {
            count = count + 1;
            placeDB db = new placeDB(context);
            SQLiteDatabase crsDB = db.openDB();
            String insert = "insert into places values (" + count + ",'"+ placeDescriptionObject.name + "','" + placeDescriptionObject.description + "','" + placeDescriptionObject.category + "','" + placeDescriptionObject.addresstitle + "','" + placeDescriptionObject.address + "'," + placeDescriptionObject.elevation + "," + placeDescriptionObject.latitude + "," + placeDescriptionObject.longitude + ");";
            crsDB.execSQL(insert);
            crsDB.close();
            db.close();
        }
        catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception adding place info: "+
                    ex.getMessage());
        }
    }
    public void update(Context context, PlaceDescription placeDescriptionObject){
        try {
            placeDB db = new placeDB(context);
            SQLiteDatabase crsDB = db.openDB();
            String update = "update places set description = '" + placeDescriptionObject.description + "', category = '" + placeDescriptionObject.category + "', addtitle = '" + placeDescriptionObject.addresstitle + "', addstreet = '" + placeDescriptionObject.address + "', elevation = " + placeDescriptionObject.elevation + ", latitude = " + placeDescriptionObject.latitude + ", longitude = " + placeDescriptionObject.longitude + " where name = '" + placeDescriptionObject.name +"';";
            crsDB.execSQL(update);
            crsDB.close();
            db.close();
        }
        catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception editing place info: "+
                    ex.getMessage());
        }
    }


}

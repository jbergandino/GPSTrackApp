package com.example.john.gpstrackapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends Activity {


    //private final LatLng LOCATION_HARLEYSVILLE = new LatLng (40.285311,-75.402775);
    private final LatLng LOCATION_HARLEYSVILLE = new LatLng(40.285311,-75.402775);
    GPSTracker gps;

    Button btnShowLocation;

    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        //map.addMarker(new MarkerOptions().position(LOCATION_HARLEYSVILLE).title("Harleysville!!"));

//        btnShowLocation = (Button) findViewById(R.id.pushButton);
//
//        btnShowLocation.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                gps = new GPSTracker(MainActivity.this);
//
//                if(gps.canGetLocation()) {
//                    double latitude = gps.getLatitude();
//                    double longitude = gps.getLongitude();
//
//                    Toast.makeText(
//                            getApplicationContext(),
//                            "Your Location is -\nLat: " + latitude + "\nLong: "
//                                    + longitude, Toast.LENGTH_LONG
//                    ).show();
//                } else {
//                    gps.showSettingsAlert();
//                }
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick_Harleysville(View v){
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_HARLEYSVILLE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_HARLEYSVILLE, 16);
        map.animateCamera(update);
    }

    public void onClick_gps(View v){
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // CameraUpdate update = CameraUpdateFactory.newLatLng(LOCATION_HARLEYSVILLE);


        gps = new GPSTracker(MainActivity.this);

        if(gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            final LatLng LOCATION_TRACKED = new LatLng(latitude, longitude);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_TRACKED, 16);
            map.animateCamera(update);

            Toast.makeText(
                    getApplicationContext(),
                    "Your Location is -\nLat: " + latitude + "\nLong: "
                            + longitude, Toast.LENGTH_LONG
            ).show();
        } else {
            gps.showSettingsAlert();
        }

    }


}

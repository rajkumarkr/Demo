package com.example.rajkumar.mymap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapViewActivity extends ActionBarActivity {


    static final LatLng BhaggyamSahridaya =new LatLng(13.033262,80.260065);
    static final LatLng GRNJeevanBramaEncalve = new LatLng(13.045009, 80.271935);
    static final LatLng Daffodils =new LatLng(13.043548,80.266306);
    static final LatLng AdroitSculptra = new LatLng(13.039036,80.261162);
    static final LatLng SSIshan = new LatLng(13.039495,80.267925);
    static final LatLng MylaiMeadow = new LatLng(13.037931,80.275758);
    static final LatLng LuzAmor = new LatLng(13.037819,80.267474);
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        Marker grnjeevanbramaencalve = map.addMarker(new MarkerOptions().position(GRNJeevanBramaEncalve)
               .title("GRN's Jeevan Brama Encalve"));

        Marker daffodils = map.addMarker(new MarkerOptions().position(Daffodils)
                .title("Daffodils"));

        Marker adroitsculptra = map.addMarker(new MarkerOptions().position(AdroitSculptra)
                .title("AdroitSculptra"));

        Marker ssishan = map.addMarker(new MarkerOptions().position(SSIshan)
                .title("S&S Ishan"));

        Marker mylaimeadow = map.addMarker(new MarkerOptions().position(MylaiMeadow)
                .title("Mylai Meadow"));

        Marker luzamor = map.addMarker(new MarkerOptions().position(LuzAmor)
                .title("Luz Amor"));

       Marker bhaggyamsahridaya = map.addMarker(new MarkerOptions()
                .position(BhaggyamSahridaya)
               .title("Bhaggyam Sahridaya")
                .snippet("Tap here for Description")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.agency)));

        // Move the camera instantly to bhaggyamsahridya with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(BhaggyamSahridaya, 20));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 3000, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}

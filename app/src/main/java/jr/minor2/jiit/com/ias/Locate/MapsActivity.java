package jr.minor2.jiit.com.ias.Locate;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import jr.minor2.jiit.com.ias.Curr_Data;
import jr.minor2.jiit.com.ias.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView tot,avail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tot=(TextView)findViewById(R.id.text_totarea);
        avail=(TextView)findViewById(R.id.text_avail_area);
    }


    @Override
    public void onMapReady (GoogleMap googleMap){
        mMap = googleMap;
        double temp=0.0;
        for(int i=0; i<Curr_Data.map.size(); i++){
            LatLng latLng=new LatLng(Curr_Data.map.get(i).lat,Curr_Data.map.get(i).log);
            mMap.addMarker(new MarkerOptions().position(latLng).title(Curr_Data.map.get(i).iname));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera( CameraUpdateFactory.zoomTo( 8.1f ) );
            temp+=(Curr_Data.map.get(i).parea);
        }
        tot.setText("Total Area- "+Curr_Data.map.get(0).tot_area+ " sq. km");
        avail.setText("Avalable Area- "+temp+ " sq. km");//Toast.makeText(this,Curr_Data.map.get(0).tot_area)
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Curr_Data.map.clear();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

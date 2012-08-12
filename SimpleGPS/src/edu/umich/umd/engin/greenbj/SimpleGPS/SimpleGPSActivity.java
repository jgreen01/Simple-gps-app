package edu.umich.umd.engin.greenbj.SimpleGPS;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SimpleGPSActivity extends Activity 
			implements OnClickListener, LocationListener{
	
	private String TAG = "SimpleGPS";
    
	private LocationManager lm;
	private Location loc;
	
	private Button refreshButton;
	private TextView latit;
	private TextView lngit;
	private TextView provideIt;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        refreshButton = (Button) this.findViewById(R.id.locbutton);
        refreshButton.setOnClickListener(this);
        
        latit = (TextView) this.findViewById(R.id.lat);
        lngit = (TextView) this.findViewById(R.id.lng);
        provideIt = (TextView) this.findViewById(R.id.provider);
        
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, this);
        loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
        provideIt.setText("Provider: " +LocationManager.GPS_PROVIDER);
        
        if(loc != null){
        	latit.setText("Lat: " + loc.getLatitude());
        	lngit.setText("Lng: " + loc.getLongitude());
        }
        
    }
    
    @Override
	public void onClick(View v) {
		if(v.getId() == R.id.locbutton){
			loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
			if(loc != null){
	        	latit.setText("Lat: " + loc.getLatitude());
	        	lngit.setText("Lng: " + loc.getLongitude());
	        }
		}
		
	}

	@Override
	public void onLocationChanged(Location loc) {}

	@Override
	public void onProviderDisabled(String locProvider) {}

	@Override
	public void onProviderEnabled(String locProvider) {}

	@Override
	public void onStatusChanged(String locProvider, int status, Bundle extras) {}
    
}
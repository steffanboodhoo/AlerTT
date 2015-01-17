package steffbood.crimewatch.activities;

import java.util.ArrayList;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.gcm.postendpoint.model.Post;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import crimewatch.helper.DbFunctions;

public class MapMain extends FragmentActivity implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener, OnMapClickListener {
	private LocationClient mLocationClient;
	private boolean isConnected;
	private Location lastLoc;
	private GoogleMap map;
	private Double latitude;
	private Double longitude;
	private String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapmain);
		mLocationClient = new LocationClient(this, this, this);
		isConnected = false;
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		setup();
		Bundle data = this.getIntent().getExtras();
		try {
			message = data.getString("message");
			latitude = Double.parseDouble(data.getString("latitude"));
			longitude = Double.parseDouble(data.getString("longitude"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (message != null && !message.equals(""))
			handleBroadcast();
		else{
			setupMap();
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		mLocationClient.connect();
	}

	private void setup() {
		Button getLoc = (Button) findViewById(R.id.btn_Map_getLocation);
		Click c = new Click();
		getLoc.setOnClickListener(c);
//		map.setOnMapClickListener(this);

	}

	private class Click implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btn_Map_getLocation) {
				if (isConnected) {
					lastLoc = mLocationClient.getLastLocation();
					if (lastLoc != null) {
						createMarker();
					}
				}
			}
		}
	}
	
	
	private void setupMap() {
		ArrayList<Post> posts=new ArrayList<Post>();
		DbFunctions dbh=new DbFunctions(MapMain.this);
		dbh.getAllPosts(posts);
		for(Post p:posts){
			createMapPost(p);
		}
	}
	

	private void createMapPost(Post p) {
		LatLng pos = new LatLng(p.getLatitude(), p.getLongitude());
		float clr = 0;
		if(p.getType().equals("Danger"))
			clr=BitmapDescriptorFactory.HUE_RED;
		else if(p.getType().equals("Warning"))
			clr=BitmapDescriptorFactory.HUE_ORANGE;
		else if(p.getType().equals("Suspicious"))
			clr=BitmapDescriptorFactory.HUE_YELLOW;
		else if(p.getType().equals("Event"))
			clr=BitmapDescriptorFactory.HUE_BLUE;
		
		map.addMarker(new MarkerOptions()
			.title(p.getSubject())
			.position(pos)
			.icon(BitmapDescriptorFactory.defaultMarker(clr)));
	}
	
	
	
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub

	}

	public void createMarker() {
		LatLng pos = new LatLng(lastLoc.getLatitude(), lastLoc.getLongitude());
		map.addMarker(new MarkerOptions().title("Im here").position(pos));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));

	}

	@Override
	public void onConnected(Bundle connectionHint) {
		isConnected = true;
		Toast.makeText(this, "connection", Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
	}

	@Override
	public void onDisconnected() {
		isConnected = false;
		Toast.makeText(this, "Connection Lost !", Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub

	}

	public void handleBroadcast() {

		LatLng pos = new LatLng(latitude, longitude);
		map.addMarker(new MarkerOptions().title("Im here").position(pos));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
	}

	@Override
	public void onMapClick(LatLng point) {
		System.out.println(point.latitude + "," + point.longitude);
		map.addMarker(new MarkerOptions().title("Im here").position(point));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
	}

}

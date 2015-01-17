package steffbood.crimewatch.activities;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import crimewatch.helper.AppengineHelper;
import crimewatch.services.ServiceUtils;

public class CreateListener extends FragmentActivity implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener, OnMapClickListener {
	private static final double EQUATOR_LENGTH = 6378140;
	private double screenWidth = 0;
	private LocationClient mLocationClient;
	private boolean isConnected;
	private Location lastLoc;

	private GoogleMap map;
	private Marker currMark;
	private Circle currCircle;
	private int distance = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_listener);
		mLocationClient = new LocationClient(this, this, this);
		isConnected = false;
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		setup();
		// Bundle data=this.getIntent().getExtras();

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenWidth = size.x;
	}

	@Override
	public void onStart() {
		super.onStart();
		mLocationClient.connect();
	}

	private void setup() {
		Button getLoc = (Button) findViewById(R.id.btn_Map_getLocation_Listener);
		Button clear = (Button) findViewById(R.id.btn_map_finished);
		Button add = (Button) findViewById(R.id.btn_map_increaseKm);
		Button minus = (Button) findViewById(R.id.btn_map_decreaseKm);
		Click c = new Click();
		getLoc.setOnClickListener(c);
		clear.setOnClickListener(c);
		add.setOnClickListener(c);
		minus.setOnClickListener(c);
		map.setOnMapClickListener(this);
	}

	private class Click implements OnClickListener {
		TextView dist = (TextView) findViewById(R.id.tv_map_currentKm);

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btn_Map_getLocation_Listener) {
				if (isConnected) {
					lastLoc = mLocationClient.getLastLocation();
					if (lastLoc != null) {
						createMarker(new LatLng(lastLoc.getLatitude(),
								lastLoc.getLongitude()));
					}
				}
			} else if (v.getId() == R.id.btn_map_finished) {
				AppengineHelper hp = new AppengineHelper(CreateListener.this,CreateListener.this);
				if (currMark != null) {
					LatLng lg = currMark.getPosition();
					hp.insertListener(lg.longitude, lg.latitude, distance,
							CreateListener.this);
					currMark.remove();
					currCircle.remove();
					ServiceUtils.saveListenerCreated(true, CreateListener.this);
					(CreateListener.this).finish();
				}
			} else if (v.getId() == R.id.btn_map_decreaseKm) {
				if (distance == 1000) {
					Toast.makeText(CreateListener.this, "Cannot decrease size",
							Toast.LENGTH_SHORT).show();
				} else {
					distance -= 1000;
					reset();
				}
			} else if (v.getId() == R.id.btn_map_increaseKm) {
				if (distance == 5000) {
					Toast.makeText(CreateListener.this, "Cannot increase size",
							Toast.LENGTH_SHORT).show();
				} else {
					distance += 1000;
					reset();
				}
			}
		}

		private void reset() {
			if (currCircle != null) {
				currCircle.setRadius(distance / 2);
				createMarker(currMark.getPosition());
			}
			dist.setText((distance / 1000) + " Km");

		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub

	}

	public void createMarker(LatLng pos) {
		try {
			currMark.remove();
			currCircle.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}

		currMark = map.addMarker(new MarkerOptions().title("Im here").position(
				pos));
		currCircle = map.addCircle(new CircleOptions().center(pos)
				.radius(distance / 2).fillColor(Color.argb(33, 38, 12, 44))
				.visible(true));
		float zoom = (float) calculateZoomLevel(distance);
		// map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, zoom));
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, zoom));
	}

	private double calculateZoomLevel(int meters) {
		float equatorLength = 40075004; // in meters
		double metersPerPixel = meters / screenWidth;
		double a = Math.log10(equatorLength / (256 * metersPerPixel));
		double b = a / (Math.log10(2));
		return Math.floor(b) - 1;
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

	@Override
	public void onMapClick(LatLng point) {
		System.out.println(point.latitude + "," + point.longitude);
		createMarker(point);
	}

}

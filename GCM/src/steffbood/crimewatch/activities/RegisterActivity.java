package steffbood.crimewatch.activities;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import crimewatch.helper.GcmSender;
import crimewatch.services.Registration;

public class RegisterActivity extends Activity implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener {
	private LocationClient mLocationClient;
	private boolean isConnected;
	private Location lastLoc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// register
		final Registration regis = new Registration(this, this.getBaseContext());
		final TextView text = (TextView) findViewById(R.id.et_messageSend);
		mLocationClient = new LocationClient(this, this, this);

		Button send = (Button) findViewById(R.id.btn_gcmSend);
		Button register = (Button) findViewById(R.id.btn_gcmRegister);
		class Click implements OnClickListener {
			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.btn_gcmSend) {
					GcmSender gs = new GcmSender(getBaseContext());
					String message = text.getText().toString();

					if (isConnected && message != null && !message.equals("")) {
						//ocationClient.getLastLocation());
					}

				} else if (v.getId() == R.id.btn_gcmRegister) {
					regis.register();
				}
			}
		}
		Click click = new Click();
		register.setOnClickListener(click);
		send.setOnClickListener(click);

	}

	@Override
	public void onStart() {
		super.onStart();
		mLocationClient.connect();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnected(Bundle connectionHint) {
		isConnected = true;
		Toast.makeText(this, "connection", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDisconnected() {
		isConnected = false;
		Toast.makeText(this, "Connection Lost !", Toast.LENGTH_SHORT).show();
	}
}

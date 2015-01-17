package steffbood.crimewatch.activities;



import com.gcm.postendpoint.model.Post;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import crimewatch.helper.AppengineHelper;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class NewPost extends Activity implements OnItemSelectedListener,GooglePlayServicesClient.ConnectionCallbacks,GooglePlayServicesClient.OnConnectionFailedListener {
	private LocationClient mLocationClient;
	private boolean isConnected;
	ImageButton btn;
	EditText sub,desc;
	Spinner spinner;
	private String alertType,description,subject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		mLocationClient = new LocationClient(this, this, this);
		spinnerFn();
		buttonFn();
	}
	
	private void buttonFn() {
		btn=(ImageButton)findViewById(R.id.sendButton);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sub=(EditText)findViewById(R.id.editText1);
				desc=(EditText)findViewById(R.id.editText2);
				subject=sub.getText().toString();
				description=desc.getText().toString();
				
				if (v==btn){
					if(! (subject.equals(null) || subject.equals("") || subject.equals(" ") ) 
						&& 	! (description.equals(null) || description.equals("") || description.equals(" ") )
						&& 	!alertType.equals("")
						&& isConnected){
							//make post here
							//String infoString="Subject: "+subject+ "\nType: "+alertType+"\nDesc: "+description;
							final Toast toast=Toast.makeText(getApplicationContext(),"Alert sent",Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -150);
							toast.show();	
							AppengineHelper ah= new AppengineHelper(getApplicationContext(), NewPost.this);
							Post post=ah.createPost(subject,alertType,description,mLocationClient.getLastLocation());
							ah.insertPost(post);
							finish();
					}//end if not empty	
					else if(!isConnected){
						final Toast toast=Toast.makeText(getApplicationContext(),"You are not connected.",  Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -150);
						toast.show();
					}
					else{
						final Toast toast=Toast.makeText(getApplicationContext(),"Subject, Alert Type or Description not selected",  Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -150);
						toast.show();
					}
				}//end if v==btn
			}//end on click
		});//end setOnClick
	}

	private void spinnerFn() {
		Resources res = getResources();	
		String[] alertTypes = res.getStringArray(R.array.alert_types);
		spinner= (Spinner) findViewById(R.id.altypespinner);
		spinner.setPrompt("Select Alert Type");
		ArrayAdapter<String> adapter_alType = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alertTypes);
		spinner.setAdapter(adapter_alType);
		spinner.setOnItemSelectedListener(this);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_post, menu);
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

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
//		if(spinner.getSelectedItemPosition()==0)
//			alertType="";
//		else
			alertType=spinner.getSelectedItem().toString();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		alertType="";
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
		Toast.makeText(this, "Connection", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onDisconnected() {
		isConnected = false;
		Toast.makeText(this, "Connection Lost !", Toast.LENGTH_SHORT).show();
		
	}
}

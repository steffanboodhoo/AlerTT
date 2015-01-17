package steffbood.crimewatch.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import com.gcm.postendpoint.model.Post;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import crimewatch.helper.DbFunctions;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Received extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {
	
	private ArrayList<Post> postlist= new ArrayList<Post>();
	private LocationClient mLocationClient;
	private boolean isConnected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_received);
		mLocationClient = new LocationClient(this, this, this);
		
//		populateList
		DbFunctions dbf= new DbFunctions(Received.this);
		dbf.getAllPosts(postlist);
		populateListView();
		
	}
	


	
	private void populateListView() {
		ArrayAdapter<Post> adapter=new PostAdapter();
		ListView list=(ListView) findViewById(R.id.recList);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LayoutInflater inflater= (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				View popup=inflater.inflate(R.layout.popup_layout, null);
				final PopupWindow popupWindow= new PopupWindow(popup,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

				ImageButton close=(ImageButton) popup.findViewById(R.id.pClose);
				TextView tv1=(TextView)popup.findViewById(R.id.pSub),
						tv2=(TextView)popup.findViewById(R.id.pType),
						tv3=(TextView)popup.findViewById(R.id.pDist),
						tv4=(TextView)popup.findViewById(R.id.pDes);
				
				Post item=(Post) parent.getItemAtPosition(position);
				
				tv1.setText("Subject: "+item.getSubject());
				tv2.setText("Type: "+item.getType());
				double d=distTo(item.getLatitude(), item.getLongitude());
				if(d!= -1)
					tv3.setText("Distance: "+d+" km away");
				else
					tv3.setText("Distance: unknown");				
				tv4.setText("Description: "+item.getMessage());
				
				close.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						popupWindow.dismiss();
						
					}
				});
				popupWindow.showAtLocation(parent,Gravity.CENTER , 0, 0);
			}
			
		});
	}
	
	private double distTo(double lat, double lng){
		if(isConnected){
			double distance;	
			Location here=mLocationClient.getLastLocation();
			Location there=new Location(here);
			there.setLatitude(lat);
			there.setLongitude(lng);	
			distance=here.distanceTo(there);
			distance=Math.round(distance*100);
			distance/=100000;
			distance=Math.round(distance*100);
			distance/=100;
			return distance;
		}
		else 
			return -1;
	}
	
	
	private class PostAdapter extends ArrayAdapter<Post>{
		public PostAdapter(){
			super(Received.this,R.layout.alert_layout,postlist);
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemview=convertView;
			if(itemview==null){
				itemview=getLayoutInflater().inflate(R.layout.alert_layout,parent,false);
			}
			
			Post curPost=postlist.get(position);
			String t=curPost.getType();
			
			ImageView iv=(ImageView) itemview.findViewById(R.id.alpic);
			if(t.equalsIgnoreCase("Danger"))
				iv.setImageResource(R.drawable.danger);
			else if(t.equalsIgnoreCase("Suspicious"))
				iv.setImageResource(R.drawable.suspicious);
			else if(t.equalsIgnoreCase("Event"))
				iv.setImageResource(R.drawable.event);
			else if(t.equalsIgnoreCase("Warning"))
				iv.setImageResource(R.drawable.warning);
			else if(t.equalsIgnoreCase("Break in"))
				iv.setImageResource(R.drawable.breakin);
			else
				iv.setImageResource(R.drawable.unknown);
			
			TextView tv1=(TextView) itemview.findViewById(R.id.altype);
			tv1.setText(t);
			
			TextView tv2=(TextView) itemview.findViewById(R.id.alsub);
			tv2.setText(curPost.getSubject());
			
			TextView tv3=(TextView) itemview.findViewById(R.id.aldist);	
			double d=distTo(curPost.getLatitude(), curPost.getLongitude());
			if(d!= -1)
				tv3.setText(d+" km away");
			else
				tv3.setText("Distance unknown");
			
			TextView tv4=(TextView) itemview.findViewById(R.id.altime);	
			Date date = new Date(curPost.getTimeCreated()*1000L); // *1000 is to convert seconds to milliseconds
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd-MM-yyyy"); // the format of your date
			String formattedDate = sdf.format(date);
			tv4.setText(formattedDate);
			
				
			
			return itemview;
		}
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.received, menu);
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
	public void onStart() {
		super.onStart();
		mLocationClient.connect();
	}
	

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onConnected(Bundle arg0) {
		isConnected = true;
		Toast.makeText(this, "Connection", Toast.LENGTH_SHORT).show();
		
	}


	@Override
	public void onDisconnected() {
		isConnected = false;
		Toast.makeText(this, "Connection Lost !", Toast.LENGTH_SHORT).show();
	}
}

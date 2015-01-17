package crimewatch.helper;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.gcm.locationlistenerendpoint.Locationlistenerendpoint;
import com.gcm.locationlistenerendpoint.model.LocationListener;
import com.gcm.locationlistenerendpoint.model.StringCollection;
import com.gcm.postendpoint.Postendpoint;
import com.gcm.postendpoint.model.Post;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

import crimewatch.services.CloudEndpointUtils;
import crimewatch.services.ServiceUtils;

public class AppengineHelper {
	private Context context;
	private Activity activity;
	private DbFunctions dbf;;

	public AppengineHelper(Context c, Activity a) {
		this.context = c;
		this.activity = a;
		dbf = new DbFunctions(context);
	}

	// *********************************INSERTING A LOCATION LISTENER
	public void insertListener(Double longitude, double latitude, int distance,
			Activity activity) {
		String deviceCode = ServiceUtils.getRegId(activity);
		String userId = null;
		new InsertListener(longitude, latitude, distance, deviceCode, userId)
				.execute();// starts task on another thread(InsertListener) to
							// insert a location listener
	}

	protected class InsertListener extends
			AsyncTask<Void, Void, LocationListener> {
		private double longitude, latitude;
		private int distance;
		private String deviceCode, userId;

		public InsertListener(double longitude, double latitude, int distance,
				String deviceCode, String userid) {
			super();
			this.longitude = longitude;
			this.latitude = latitude;
			this.deviceCode = deviceCode;
			this.userId = userid;
			this.distance = distance;
		}

		@Override
		protected LocationListener doInBackground(Void... params) {
			Locationlistenerendpoint.Builder endpointBuilder = new Locationlistenerendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);
			endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);
			Locationlistenerendpoint endpoint = endpointBuilder.build();
			
			LocationListener l = new LocationListener();
			l.setDeviceCode(deviceCode);
			l.setUserId(userId);
			l.setLongitude(longitude);
			l.setLatitude(latitude);
			l.setDistance(distance);
			try {
				l=endpoint.insertLocationListener(l).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return l;
		}

		@Override
		protected void onPostExecute(LocationListener result) {
			try{
				long id = (result.getListenerId()).getId();
				dbf.insertListener(activity, longitude, latitude, distance, id);
			}catch(Exception e){
				Toast.makeText(activity, "Check network connection", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}

	}

	// ********************************INSERTING POST/NOTIFICATION
	public void insertPost(Post p) {
		new InsertPost(p).execute();
	}

	protected class InsertPost extends AsyncTask<Void, Void, Post> {
		Post p;

		public InsertPost(Post p) {
			super();
			this.p = p;
		}

		@Override
		protected Post doInBackground(Void... params) {
			Postendpoint.Builder postEndpointBuilder = new Postendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);
			postEndpointBuilder = CloudEndpointUtils
					.updateBuilder(postEndpointBuilder);
			Postendpoint postEndpoint = postEndpointBuilder.build();

			try {
				p = postEndpoint.insertPost(p).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return p;
		}

		@Override
		protected void onPostExecute(Post result) {
			try {
				Log.i("POST Id", "" + p.getKey().getId());
				alertPeople(p);
				//dbf.insertPost(p);
			} catch (Exception e) {
				e.printStackTrace();
				// ui code to say message could not be delivered
			}

			super.onPostExecute(result);
		}

	}

	public void alertPeople(Post p) {
		new AlertPeople(p).execute();
	}

	public class AlertPeople extends AsyncTask<Void, Void, Void> {
		Post p;

		public AlertPeople(Post p) {
			this.p = p;
		}

		@Override
		protected Void doInBackground(Void... params) {
			Locationlistenerendpoint.Builder endpointBuilder = new Locationlistenerendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);
			endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);

			StringCollection listeners = null;
			Locationlistenerendpoint endpoint = endpointBuilder.build();
			try {
				
				listeners = endpoint.alertPeople(p.getMessage(),p.getSubject(),p.getType(),
						p.getLatitude(), p.getLongitude(),p.getTimeCreated()).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*
			 * List<String> listnrs = listeners.getItems(); for (String k :
			 * listnrs) { System.out.println(k); }
			 */
			return null;
		}
	}

	public Post createPost(String subject, String type, String message, Location location) {
		Post p = new Post();
		p.setLatitude(location.getLatitude());
		p.setLongitude(location.getLongitude());
		p.setMessage(message);
		p.setSubject(subject);
		p.setType(type);
		long unixTime = System.currentTimeMillis() / 1000L;
		p.setTimeCreated(unixTime);
		return p;
	}

	// ******************************REMOVES LISTENER
	public void removeListener(long id) {
		new RemoveListener(id).execute();
	}

	public class RemoveListener extends AsyncTask<Void, Void, Void> {
		long id;

		public RemoveListener(long id) {
			this.id = id;
		}

		@Override
		protected Void doInBackground(Void... params) {
			Locationlistenerendpoint.Builder endpointBuilder = new Locationlistenerendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);
			endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);
			Locationlistenerendpoint endpoint = endpointBuilder.build();
			try {
				endpoint.removeLocationListener(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	public void getAllNotificationsAfter(long lastUpdate) {
		Postendpoint.Builder postEndpointBuilder = new Postendpoint.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				null);
		postEndpointBuilder = CloudEndpointUtils
				.updateBuilder(postEndpointBuilder);
		Postendpoint postEndpoint = postEndpointBuilder.build();
		// --to be completed
	}

	public void removePost(Long id) {
		new RemovePost(id).execute();
	}

	public class RemovePost extends AsyncTask<Void, Void, Boolean> {
		long id;

		public RemovePost(long id) {
			this.id = id;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			Postendpoint.Builder postEndpointBuilder = new Postendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);
			postEndpointBuilder = CloudEndpointUtils
					.updateBuilder(postEndpointBuilder);
			Postendpoint postEndpoint = postEndpointBuilder.build();
			Boolean deleted = false;
			try {
				postEndpoint.removePost(id).execute();
				deleted = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return deleted;
		}

		@Override
		protected void onPostExecute(Boolean deleted) {
			if (!deleted) {// if the post didnt get the deleted
				// then do stuff with the UI
			}
			super.onPostExecute(deleted);
		}
	}

}
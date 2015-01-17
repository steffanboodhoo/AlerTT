package crimewatch.services;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.gcm.deviceinfoendpoint.Deviceinfoendpoint;
import com.gcm.deviceinfoendpoint.model.DeviceInfo;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Registration {

	private Context ctx;
	private Activity activity;

	public Registration(Activity activity, Context context) {
		this.ctx = context;
		this.activity = activity;
	}

	public void register() {
		System.out.println("start register");
		GCMRegistrar.checkDevice(ctx);
		GCMRegistrar.checkManifest(ctx);
		new RegisterInBack().execute();
	}

	public class RegisterInBack extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			System.out.println("registreing in background");
			GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(ctx);
			String regId = ServiceUtils.getRegId(activity);
			boolean success = true;
			if (regId.equals("")) {
				try {
					regId = gcm.register(ServiceUtils.SENDER_ID);
					System.out.println("device regid:" + regId);
					ServiceUtils.saveRegId(regId, activity);
					insertDevice(regId);
				} catch (Exception e) {
					success = false;
					e.printStackTrace();
				}
			} else {
				try {
					System.out.println("device regid:" + regId);
					insertDevice(regId);
				} catch (Exception e) {
					e.printStackTrace();
					success = false;
				}
			}
			return null;
		}
	}

	private void insertDevice(String regId) throws Exception {
		Deviceinfoendpoint.Builder endpointBuilder = new Deviceinfoendpoint.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				new HttpRequestInitializer() {
					public void initialize(HttpRequest httpRequest) {
					}
				});
		Deviceinfoendpoint endpoint = CloudEndpointUtils.updateBuilder(
				endpointBuilder).build();

		boolean infoExists = false;
		try {
			DeviceInfo existingInfo = endpoint.getDeviceInfo(regId).execute();// checking
																				// to
																				// see
																				// if
																				// the
																				// device
																				// is
																				// already
																				// in
																				// the
																				// cloud
			if (existingInfo != null
					&& regId.equals(existingInfo.getDeviceRegistrationID())) {// if
																				// info
																				// exists
																				// then
																				// set
																				// info
																				// exists
																				// to
																				// true
				infoExists = true;
				System.out.println("info exissts:"
						+ existingInfo.getDeviceRegistrationID());
			}
		} catch (IOException e) {
			e.printStackTrace();
			// Ignore
		}
		if (!infoExists) {// if info doesnt exist in the cloud
			DeviceInfo info = new DeviceInfo();
			info.setDeviceRegistrationID(regId);
			System.out.println("info didnt exist so inserting");
			endpoint.insertDeviceInfo(info).execute();// the exception thrown
														// will be caught by
														// registerInBackGround
		}
	}
}

package crimewatch.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class MyGcmIntentService extends IntentService {
	public MyGcmIntentService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		// The getMessageType() intent parameter must be the intent you received
		// in your BroadcastReceiver.
		String messageType = gcm.getMessageType(intent);
		System.out.println("service");
		// intent.getStringExtra("")
		if (!extras.isEmpty()) { // has effect of unparcelling Bundle
			/*
			 * Filter messages based on message type. Since it is likely that
			 * GCM will be extended in the future with new message types, just
			 * ignore any message types you're not interested in, or that you
			 * don't recognize.
			 */
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				Log.i("GcmIntentService", "type of broadcast is error");
				// sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				// sendNotification("Deleted messages on server: "
				// +extras.toString());
				Log.i("GcmIntentService", "type of broadcast is deleted");
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {
				// This loop represents the service doing some work.
				Log.i("GcmIntentService", "type of broadcast is Message");
				Log.i("Message", extras.getString("Message"));
				Log.i("Details", "Received: " + extras.toString());
			}
			// Post notification of received message.
			// sendNotification("Received: " + extras.toString());

		}
		// Release the wake lock provided by the WakefulBroadcastReceiver.
		MyBroadcastReceiver.completeWakefulIntent(intent);
	}

}

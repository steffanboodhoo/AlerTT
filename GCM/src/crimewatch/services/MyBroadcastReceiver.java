package crimewatch.services;


import com.gcm.postendpoint.model.Post;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;

import crimewatch.helper.AppengineHelper;
import crimewatch.helper.DbFunctions;
import steffbood.crimewatch.activities.MapMain;
import steffbood.crimewatch.activities.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends WakefulBroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("BroadcastReciever",
				"message:" + (intent.getExtras()).getString("message"));
		
		Intent gcmService = new Intent(context, MyGcmIntentService.class);
		handleNotification(intent, context);
		try{
			Post p=new Post();
			
			p.setSubject((intent.getExtras()).getString("subject"));
			p.setMessage((intent.getExtras()).getString("message"));
			p.setType((intent.getExtras()).getString("type"));
			p.setLatitude(  Double.parseDouble((intent.getExtras()).getString("latitude") ));
			p.setLongitude(  Double.parseDouble(  (intent.getExtras()).getString("longitude") ));
			p.setTimeCreated(Long.parseLong(intent.getStringExtra("time")));
			DbFunctions dbf=new DbFunctions(context);
			dbf.insertPost(p);
			Toast.makeText(context, "message received", Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(context, "registered", Toast.LENGTH_SHORT).show();
		}
		startWakefulService(context, gcmService);
		setResultCode(Activity.RESULT_OK);
		
	}
	
	
//	private void pushtoDB(Context context,Post p){
//		DbFunctions dbf=new DbFunctions(context);
//		dbf.insertPost(p);
//		
//	}
//	
	
	private void handleNotification(Intent intent, Context ctx) {

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(ctx, MapMain.class);
		resultIntent.putExtra("message", intent.getExtras()
				.getString("message"));
		resultIntent.putExtra("latitude",
				intent.getExtras().getString("latitude"));
		resultIntent.putExtra("longitude",
				intent.getExtras().getString("longitude"));

		// TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
		// Adds the back stack for the Intent (but not the Intent itself)
		// stackBuilder.addParentStack(MapMain.class);
		// Adds the Intent that starts the Activity to the top of the stack
		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, resultIntent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		/*
		 * stackBuilder.addNextIntent(resultIntent); PendingIntent
		 * resultPendingIntent = stackBuilder.getPendingIntent( 0,
		 * PendingIntent.FLAG_UPDATE_CURRENT );
		 * mBuilder.setContentIntent(resultPendingIntent);
		 */
		long[] pattern={500,500,500,500,500,500,500,500,500};
		Notification noti = new Notification.Builder(ctx)
			.setContentTitle(intent.getExtras().getString("type"))//.setContentTitle("New mail from") 
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentText(intent.getExtras().getString("subject")+": "+intent.getExtras().getString("message")).setAutoCancel(true) //	.setContentText("nothing much").setAutoCancel(true) 
			.setOnlyAlertOnce(true).setTicker("Alertt")
			.setContentIntent(pIntent)
			.setVibrate(pattern).build();

		NotificationManager mNotificationManager = (NotificationManager) ctx
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(0, noti);
	}

}

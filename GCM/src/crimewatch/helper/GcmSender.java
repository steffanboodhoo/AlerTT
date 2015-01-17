package crimewatch.helper;

import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

public class GcmSender {
	private Context ctx;
	private AtomicInteger msgId;

	public GcmSender(Context ctx) {
		this.ctx = ctx;
	}

//	public void alertPeople(String message, Location location) {
//		AppengineHelper app = new AppengineHelper();
//		app.insertPost(app.createPost(message, location));
//	}
//
//	public static void insertListener(Activity a) {
//		AppengineHelper app = new AppengineHelper();
		// app.insertListener(1.0, 1.0, a);
//	}
	/*
	 * public static void insertMessage(String message){ AppengineHelper app=new
	 * AppengineHelper(); app.insertPost(message, null); }
	 */

}

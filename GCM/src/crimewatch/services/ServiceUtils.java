package crimewatch.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ServiceUtils {
	public static String SENDER_ID = "1044025777638";// this is the project number (PROJECT CONSOLE)
	public static final String RegPref = "Registration Pref";// name of file(s) that stores my preferences for this activity [key value pair storage]
	public static final String RegId = "RegistrationId";
	public static final String ListenerCreated= "ListenerCreated";
	
	public static String getRegId(Activity activity) {
		SharedPreferences pref = (activity.getApplicationContext())
				.getSharedPreferences(RegPref, Context.MODE_PRIVATE);
		// PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		String id = pref.getString(RegId, "");
		return id;
	}

	public static void saveRegId(String regId, Activity activity) {
		SharedPreferences pref = activity.getSharedPreferences(RegPref,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(RegId, regId);
		editor.commit();
	}
	
	public static void saveListenerCreated(boolean created, Activity activity){
		SharedPreferences pref = activity.getSharedPreferences(RegPref, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean(ListenerCreated, created);
		editor.commit();
	}
	public static boolean getListenerCreated(Activity activity){
		SharedPreferences pref = (activity.getApplicationContext())
				.getSharedPreferences(RegPref, Context.MODE_PRIVATE);
		boolean created=pref.getBoolean(ListenerCreated, false);
		return created;
	}
}

package crimewatch.helper;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gcm.locationlistenerendpoint.model.LocationListener;
import com.gcm.postendpoint.model.Post;

import crimewatch.services.ServiceUtils;

public class DbFunctions {
	private Context ctx;
	private SQLiteDatabase db;
	private DbHelper dbh;

	public DbFunctions(Context ctx) {
		super();
		this.ctx = ctx;
		dbh = new DbHelper(ctx);
		db = dbh.getReadableDatabase();
	}

	public void insertListener(Activity activity, double longitude,
			double latitude, int distance,long id) {
		ContentValues cv = new ContentValues();
		cv.put(DbHelper.Listener_longitude, longitude);
		cv.put(DbHelper.Listener_latitude, latitude);
		String deviceCode = ServiceUtils.getRegId(activity);
		cv.put(DbHelper.Listener_DeviceId, deviceCode);
		cv.put(DbHelper.Listener_distance, distance);
		cv.put(DbHelper.Listener_Id, id);
		db.insert(DbHelper.Table_Listeners, null, cv);
	}

	public void insertPost(Post p) {
		ContentValues cv = new ContentValues();
//		cv.put(DbHelper.Post_id, p.getKey().getId());
		cv.put(DbHelper.Post_longitude, p.getLongitude());
		cv.put(DbHelper.Post_latitude, p.getLatitude());
		cv.put(DbHelper.Post_time, p.getTimeCreated());
		cv.put(DbHelper.Post_message, p.getMessage());
		cv.put(DbHelper.Post_subject, p.getSubject());
		cv.put(DbHelper.Post_type, p.getType());
		cv.put(DbHelper.Post_user, p.getUserId());
		db.insert(DbHelper.Table_Posts, null, cv);
	}
	
	public void getAllPosts(ArrayList<Post> posts){
		String code="select * from "+DbHelper.Table_Posts+";";
		Cursor cursor=db.rawQuery(code, null);
		if(cursor.getCount()<1)
			return;
		
		while(cursor.moveToNext()){
			Post p=new Post();
			p.setMessage(cursor.getString(cursor.getColumnIndex(DbHelper.Post_message)));		
			p.setSubject(cursor.getString(cursor.getColumnIndex(DbHelper.Post_subject)));
			p.setType(cursor.getString(cursor.getColumnIndex(DbHelper.Post_type)));			
			p.setLongitude(cursor.getDouble(cursor.getColumnIndex(DbHelper.Post_longitude)));
			p.setLatitude(cursor.getDouble(cursor.getColumnIndex(DbHelper.Post_latitude)));
			p.setUserId(cursor.getInt(cursor.getColumnIndex(DbHelper.Post_user)));
			p.setTimeCreated(cursor.getLong(cursor.getColumnIndex(DbHelper.Post_time)));
			posts.add(p);
		}
		return;
	} 
	
	public void getAllListeners(ArrayList<LocationListener> listeners){
		String code="select * from "+DbHelper.Table_Listeners+";";
		Cursor cursor=db.rawQuery(code, null);
		if(cursor.getCount()<1)
			return;
		while(cursor.moveToNext()){
			LocationListener l=new LocationListener();
			l.setDeviceCode(cursor.getString(cursor.getColumnIndex(DbHelper.Listener_DeviceId)));
			l.setLatitude(cursor.getDouble(cursor.getColumnIndex(DbHelper.Listener_latitude)));
			l.setLongitude(cursor.getDouble(cursor.getColumnIndex(DbHelper.Listener_longitude)));
			l.setDistance(cursor.getInt(cursor.getColumnIndex(DbHelper.Listener_distance)));
			listeners.add(l);
		}
		return;
	}
	
}

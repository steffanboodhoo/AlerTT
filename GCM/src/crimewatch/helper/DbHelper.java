package crimewatch.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static String DATABASE_NAME = "eCommuneDb";
	private static int VERSION = 4;
	private Context ctx;

	public static String Table_Listeners = "Listeners";
	public static String Listener_Id = "ListenerId";
	public static String Listener_DeviceId = "DeviceId";
	public static String Listener_latitude = "latitude";
	public static String Listener_longitude = "longitude";
	public static String Listener_distance= "distance";

	public static String Table_Posts = "Posts";
	public static String Post_id = "id";
	public static String Post_time = "time";
	public static String Post_message = "message";
	public static String Post_subject = "subject";
	public static String Post_type = "type";
	public static String Post_user = "user";
	public static String Post_longitude = "longitude";
	public static String Post_latitude = "latitude";
	public static String Post_distance = "distance";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		this.ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTables(db);
	}

	private void createTables(SQLiteDatabase db) {
		createListener(db);
		createPost(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTables(db);
		createTables(db);
	}

	private void dropTables(SQLiteDatabase db) {
		db.execSQL("drop table if exists " + DbHelper.Table_Listeners);
		db.execSQL("drop table if exists " + DbHelper.Table_Posts);
	}

	public void createListener(SQLiteDatabase db) {
		String code = "create table " + DbHelper.Table_Listeners + "( " + " "
				+ DbHelper.Listener_Id + " text primary key," 
				+ DbHelper.Listener_DeviceId + " text," 
				+ DbHelper.Listener_distance + " real,"
				+ DbHelper.Listener_latitude + " real,"
				+ DbHelper.Listener_longitude + " number);";
		db.execSQL(code);
	}

	public void createPost(SQLiteDatabase db) {
		String code = "create table " + DbHelper.Table_Posts + "(" + " "
				+ DbHelper.Post_id + " integer primary key autoincrement," 
				+ DbHelper.Post_time+ " number," 
				+ DbHelper.Post_message+ " text,"
				+ DbHelper.Post_subject+ " text,"
				+ DbHelper.Post_type+ " text," 
				+ DbHelper.Post_latitude + " real,"
				+ DbHelper.Post_longitude + " real,"
				+ DbHelper.Post_distance + " real," 
				+ DbHelper.Post_user + " text);";
		db.execSQL(code);
	}
	
	
	
}

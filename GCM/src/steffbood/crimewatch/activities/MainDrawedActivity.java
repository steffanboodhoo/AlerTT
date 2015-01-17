package steffbood.crimewatch.activities;

import crimewatch.services.Registration;
import crimewatch.services.ServiceUtils;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainDrawedActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private Button btn1,btn2,btn3,btn4;
	final Registration regis = new Registration(this, this);
	
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_drawed);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		buttonFn();
	}

	
	

private void buttonFn() {
	btn1=(Button) findViewById(R.id.newpostbtnDraw);
	btn1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainDrawedActivity.this,NewPost.class));
		}
	});
	
	btn2=(Button) findViewById(R.id.receivedbtnDraw);
	btn2.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainDrawedActivity.this,Received.class));
		}
	});
	
	btn3=(Button) findViewById(R.id.mapbtnDraw);
	btn3.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainDrawedActivity.this,MapMain.class));
		}
	});
	
	btn4=(Button) findViewById(R.id.regbtnDraw);
	if(ServiceUtils.getListenerCreated(MainDrawedActivity.this)){
		btn4.setText("Registered");
		//btn4.setClickable(false);
		/*}else{
		regis.register();*/
	}
	btn4.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View v) {			
			regis.register();
			btn4.setClickable(false);
			btn4.setText("Registered");
			Toast.makeText(MainDrawedActivity.this, "Registered", Toast.LENGTH_SHORT).show();
		}
	});
	
}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		if(position==0){
			Intent i=new Intent(MainDrawedActivity.this,CreateListener.class);
			startActivity(i);
		}else if(position==1){
			
		}
		Toast.makeText(MainDrawedActivity.this, position+"#", Toast.LENGTH_SHORT).show();
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main_drawed, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */


}

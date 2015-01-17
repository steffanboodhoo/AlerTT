package steffbood.crimewatch.activities;

import crimewatch.helper.FlyOutContainer;
import crimewatch.services.Registration;
import crimewatch.services.ServiceUtils;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * The Main Activity.
 * 
 * This activity starts up the RegisterActivity immediately, which communicates
 * with your App Engine backend using Cloud Endpoints. It also receives push
 * notifications from backend via Google Cloud Messaging (GCM).
 * 
 * Check out RegisterActivity.java for more details.
 */
public class MainActivity extends Activity {

//	FlyOutContainer root;
	private Button btn1,btn2,btn3,btn4;
	final Registration regis = new Registration(this, this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i=new Intent(this,MainDrawedActivity.class);
		startActivity(i);
//		this.root=(FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_main, null);
//		this.setContentView(root);
		setContentView(R.layout.activity_main);
		/*if(!ServiceUtils.getListenerCreated(MainActivity.this)){
			Intent i=new Intent(this,CreateListener.class);
			startActivity(i);
		}*/

		buttonFn();
	}


private void buttonFn() {
	btn1=(Button) findViewById(R.id.newpostbtn);
	btn1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainActivity.this,NewPost.class));
		}
	});
	
	btn2=(Button) findViewById(R.id.receivedbtn);
	btn2.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainActivity.this,Received.class));
		}
	});
	
	btn3=(Button) findViewById(R.id.mapbtn);
	btn3.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(MainActivity.this,MapMain.class));
		}
	});
	
	btn4=(Button) findViewById(R.id.regbtn);
	if(ServiceUtils.getListenerCreated(MainActivity.this)){
		btn4.setText("Registered");
		btn4.setClickable(false);
	}else{
		regis.register();
	}
	/*btn4.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View v) {			
			regis.register();
			btn4.setClickable(false);
			btn4.setText("Registered");
			Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
		}
	});*/
	
}
public void changeBtn(){
	btn4.setText("Registered");
	btn4.setClickable(false);
}
//public void toggleMenu(View v){
//	this.root.toggleMenu();
//}
	
}
package com.example.imagegallary;

import java.net.URL;
import java.util.ArrayList;
/*
 * Abhijit Pasupuleti
 * Sandeep dandi
 */

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
try{
			
			//CREATE VIEW, URL STRING PAIR AND PUT IT INTO ARRAYLIST
			ArrayList<ViewUrlPairs> arrayToSend = new ArrayList<ViewUrlPairs>();
			arrayToSend.add(new ViewUrlPairs(new URL(getResources().getString(R.string.uncc_main_thumb)), (ImageButton) findViewById(R.id.imageButton1)));
			arrayToSend.add(new ViewUrlPairs(new URL(getResources().getString(R.string.ifest_main_thumb)), (ImageButton) findViewById(R.id.imageButton2)));
			arrayToSend.add(new ViewUrlPairs(new URL(getResources().getString(R.string.football_main_thumb)), (ImageButton) findViewById(R.id.imageButton3)));
			arrayToSend.add(new ViewUrlPairs(new URL(getResources().getString(R.string.commencement_main_thumb)), (ImageButton) findViewById(R.id.imageButton4)));
			
//			 Toast.makeText(getBaseContext(), arrayToSend.size()+"" , Toast.LENGTH_SHORT).show();
			
			//SET THE BACKGROUND OF IMAGEBUTTON USING ASYNCTASK
			new getMainButtons(this).execute(this,arrayToSend); 
			
			//SET THE STRING HEADER FOR THE TEXT VIEWS FOR INFO
			TextView tv =(TextView) findViewById(R.id.textView1);
			tv.setText(getResources().getString(R.string.label_uncc));
			
			tv = (TextView) findViewById(R.id.textView2);
			tv.setText(getResources().getString(R.string.label_ifest));
			
			tv = (TextView) findViewById(R.id.textView3);
			tv.setText(getResources().getString(R.string.label_sports));
			
			tv = (TextView) findViewById(R.id.textView4);
			tv.setText(getResources().getString(R.string.label_commencement));
			
			//COMMON CLICK LISTENER
			OnClickListener imgBtnClick= new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i= new Intent(getBaseContext(), GallaryActivity.class);
					if(v.getId() == R.id.imageButton1){
//						toastie("uncc");
						i.putExtra("photos", "uncc");
						}
					else if(v.getId() == R.id.imageButton2){
//						toastie("ifest");
						i.putExtra("photos", "ifest");
						}
					else if(v.getId() == R.id.imageButton3){
//						toastie("sports");
						i.putExtra("photos", "sports");
						}
					else if(v.getId() == R.id.imageButton4){
//						toastie("commencement");
						i.putExtra("photos", "commencement");
						}
					else if(v.getId()==R.id.button1){
						 finish();
				            System.exit(0);
					}
					startActivity(i);
				}
			};
			
			//SETTING THE ONCLICK FUNCTION FOR IMAGE VIEWS
			//CREATE AN INSTANCE OF THE IMAGEBUTTON IN THE VIEW
			((ImageButton) findViewById(R.id.imageButton1)).setOnClickListener(imgBtnClick);
			((Button) findViewById(R.id.button1)).setOnClickListener(imgBtnClick);
			
			((ImageButton) findViewById(R.id.imageButton2)).setOnClickListener(imgBtnClick);
			((ImageButton) findViewById(R.id.imageButton3)).setOnClickListener(imgBtnClick);
			((ImageButton) findViewById(R.id.imageButton4)).setOnClickListener(imgBtnClick);


			
		}catch(Exception e){}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

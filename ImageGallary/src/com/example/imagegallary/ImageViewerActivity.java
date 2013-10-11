/*
 * Abhijit Pasupuleti
 * Sandeep dandi
 */

package com.example.imagegallary;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageViewerActivity extends Activity implements OnGestureListener  {

	String[] images = null;
	ProgressDialog pg;
	Handler handler;
	int index=0;
    private GestureDetector gestureScanner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_viewer);
		

        gestureScanner = new GestureDetector(this);
		
		Bundle b = getIntent().getExtras();
		Resources res = getResources();
		pg = new ProgressDialog(this);
		pg.setMessage("Loading Please Wait");
		pg.show();
		pg.setCancelable(false);
		if (b.containsKey("category")) {
			if (b.getString("category").equals("ifest")) {
				images = res.getStringArray(R.array.ifest_photos);
			} else if (b.getString("category").equals("uncc")) {
				images = res.getStringArray(R.array.uncc_photos);
			} else if (b.getString("category").equals("commencement")) {
				images = res.getStringArray(R.array.commencement_photos);
			} else {
				images = res.getStringArray(R.array.football_photos);
			}
			index=b.getInt("ids");
			//Toast.makeText(getBaseContext(), images[index]+":::"+index, Toast.LENGTH_SHORT).show();
			
		}
		handler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				try {
					ArrayList<Object> arr = (ArrayList<Object>) message.obj;
					if (Integer.parseInt(arr.get(1).toString()) == 1) {
						ImageView img = (ImageView)(findViewById(R.id.imgV_Original));
						img.setImageDrawable((Drawable) arr.get(0));
					}
					else
					{
						Toast.makeText(getBaseContext(), "Error While Loading Image", Toast.LENGTH_SHORT).show();
					}
					pg.hide();
				}
				catch(Exception e)
				{
					Toast.makeText(getBaseContext(), "Error While Loading Image", Toast.LENGTH_SHORT).show();
				}
			}
		};
		GetSingleImage imgs=new GetSingleImage(handler, images[index]);
		imgs.start();
		Button btn=(Button)findViewById(R.id.btn_Back);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		btn.bringToFront();
		btn=(Button)findViewById(R.id.btn_Left);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(index>=1)
				{
					index--;
					GetSingleImage imgs=new GetSingleImage(handler, images[index]);
					imgs.start();
					pg.show();
					
				}
				else
				{
					Toast.makeText(getBaseContext(), "You have reached the first Image...!!!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		btn.bringToFront();
		
		btn=(Button)findViewById(R.id.btn_Right);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(index<=images.length-2)
				{
					index++;
					GetSingleImage imgs=new GetSingleImage(handler, images[index]);
					imgs.start();
					pg.show();
					
				}
				else
				{
					Toast.makeText(getBaseContext(), "You have reached the last Image...!!!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		btn.bringToFront();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_image_viewer, menu);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;


	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		
		try{
			if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
            {
//				Toast.makeText(getApplicationContext(), "right"+(e1.getX() - e2.getX()) , Toast.LENGTH_SHORT).show();
				Button temp = ((Button)findViewById(R.id.btn_Right));
	    		temp.performClick();
            }else if(e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
            {
//				Toast.makeText(getApplicationContext(), "left"+(e1.getX() - e2.getX()) , Toast.LENGTH_SHORT).show();
				Button temp = ((Button)findViewById(R.id.btn_Left));
	    		temp.performClick();
            }
		}
		catch(Exception e){}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
    	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	 @Override
	    public boolean onTouchEvent(MotionEvent me)
	    {
	    	return gestureScanner.onTouchEvent(me);
	    }

}

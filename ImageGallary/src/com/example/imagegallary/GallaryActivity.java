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
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class GallaryActivity extends Activity {

	ProgressDialog pg;
	int totalImages;
	GridLayout gv;
	Handler handler;
	int counter = 0;
	Activity act;
	ArrayList arr;
	public static String img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallary);
		Resources res = getResources();

		String[] images = null;
		// img="ifest";

		Bundle b = getIntent().getExtras();
		if (b.containsKey("photos")) {
//			Toast.makeText(getBaseContext(), b.getString("photos"),
//					Toast.LENGTH_SHORT).show();
			img = b.getString("photos");
			if (b.getString("photos").equals("ifest")) {
				images = res.getStringArray(R.array.ifest_thumbs);
			} else if (b.getString("photos").equals("uncc")) {
				images = res.getStringArray(R.array.uncc_thumbs);
			} else if (b.getString("photos").equals("commencement")) {
				images = res.getStringArray(R.array.commencement_thumbs);
			} else {
				images = res.getStringArray(R.array.football_thumbs);
			}
		}

		gv = (GridLayout) (findViewById(R.id.gl_Images));
		act = this;
		totalImages = images.length;
		pg = new ProgressDialog(this);
		pg.setMessage("Loading Please Wait");
		pg.setCancelable(false);
		pg.show();

		handler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				try {
					arr = (ArrayList<Object>) message.obj;
					if (Integer.parseInt(arr.get(1).toString()) == 1) {
						ImageView img = new ImageView(getBaseContext());
						img.setPadding(0, 0, 0, 10);
						img.setImageDrawable((Drawable) arr.get(0));
						gv.addView(img);
						img.getLayoutParams().height = 120;
						img.getLayoutParams().width = 120;
						img.setId(Integer.parseInt(arr.get(2).toString()));
						img.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								int id = v.getId();

								Intent i = new Intent(getBaseContext(),
										ImageViewerActivity.class);
								i.putExtra("ids", id);
								i.putExtra("category", GallaryActivity.img);
								startActivity(i);

								// Toast.makeText(getBaseContext(), id+"",
								// Toast.LENGTH_SHORT).show();
							}
						});
					} else {
						Toast.makeText(getBaseContext(),
								"Error while retrieving some image",
								Toast.LENGTH_SHORT).show();
					}
					counter++;

					if (counter >= totalImages)
						pg.hide();
				} catch (Exception e) {
					Toast.makeText(getBaseContext(),
							"Exception " + e.toString(), Toast.LENGTH_SHORT)
							.show();
				}
			}

		};
		GetImage imagesGet = new GetImage(handler, images, getBaseContext());
		imagesGet.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_gallary, menu);
		return true;
	}

}

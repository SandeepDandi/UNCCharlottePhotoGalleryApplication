/*
 * Abhijit Pasupuleti
 * Sandeep dandi
 */

package com.example.imagegallary;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class getMainButtons extends AsyncTask<Object, Object, Object> {

	public Context ctx;
	public View temp_view;
	public ArrayList<ViewUrlPairs> gotPair;
	public ProgressDialog dialog;

	public getMainButtons(Context ctx) {
		// START PROGRESS BAR
		dialog = ProgressDialog.show(ctx, "", "Loading. Please wait...", true);
		dialog.setCancelable(false);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object doInBackground(Object... params) {

		Bitmap bm = null;
		InputStream is;

		// GETS THE CONTEXT OF MAIN TO MAYBE GENERATE A LOADING OR SOMETHING
		ctx = (Context) params[0];
		
		// GETS THE ARRAYLIST AND THE VIEW THAT IS BEING PASSED
		gotPair= (ArrayList<ViewUrlPairs>) params[1];
		
		//LOOP OVER ALL THE ELEMENTS OF THE ARRAYLIST
		for(int loop_counter=0; loop_counter < gotPair.size(); loop_counter++){
			try {
				
				temp_view = (gotPair.get(loop_counter)).getView_from_pair();	
				
				// PARSE STRING TO URL
				URL temp_hold = gotPair.get(loop_counter).getUrl_from_pair();
				
				// OPEPN THE URL AS AN INPUT STREAM
				is = temp_hold.openStream();
				// DECODE THE IMPUT STREAM INTO AN IMAGE
				bm = new BitmapFactory().decodeStream(is);
				
				
				//END THE PROGRESS BAR
				publishProgress(bm,temp_view);
				
				//CLOSE THE URL STREAM
				is.close();
				
			} catch (Exception e) {
				Log.d("ERROR_IMG_GALL", "ERROR LOADING IMG");
				dialog.cancel();
			}
			
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Object... images) {
		((ImageButton) images[1]).setImageBitmap((Bitmap) images[0]);
		((ImageButton) images[1]).getLayoutParams().width=170;
		((ImageButton) images[1]).getLayoutParams().height
		=150;
		
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		dialog.cancel();

	}

}

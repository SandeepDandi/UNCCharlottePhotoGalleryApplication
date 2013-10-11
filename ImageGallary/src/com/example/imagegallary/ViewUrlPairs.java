/*
 * Abhijit Pasupuleti
 * Sandeep dandi
 */

package com.example.imagegallary;

import java.net.URL;

import android.view.View;

public class ViewUrlPairs {
	
	private URL url_from_pair;
	private View view_from_pair;
	
	public ViewUrlPairs( URL url,View view){
		setUrl_from_pair(url);
		setView_from_pair(view);
	}
	
	public URL getUrl_from_pair() {
		return url_from_pair;
	}
	public void setUrl_from_pair(URL url_from_pair) {
		this.url_from_pair = url_from_pair;
	}
	public View getView_from_pair() {
		return view_from_pair;
	}
	public void setView_from_pair(View view_from_pair) {
		this.view_from_pair = view_from_pair;
	}
	
}

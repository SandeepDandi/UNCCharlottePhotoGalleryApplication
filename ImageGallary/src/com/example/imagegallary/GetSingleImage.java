/*
 * Abhijit Pasupuleti
 * Sandeep dandi
 */

package com.example.imagegallary;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class GetSingleImage extends Thread {

	/**
	 * @param args
	 */
	ArrayList<Object> a = new ArrayList<Object>();
	Handler h;
	String url;
	

	public GetSingleImage(Handler handle, String url) {
		h=handle;
		this.url = url;
		
	}

	public void run() {
		
		String s = url;
		a = new ArrayList<Object>();
		try {
			Drawable d = LoadImageFromWebOperations(s);

			a.add(d);
			a.add(1);			
			a.add(s);

		} catch (Exception e) {

			a.add(null);
			a.add(0);			
			a.add(s);

		}
		Message m = h.obtainMessage();
		m.obj = a;
		h.sendMessage(m);
	}

	private Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "image.png");
			return d;
		} catch (Exception e) {
			System.out.println("Exc=" + e);
			String ex = e.toString();
			System.out.println(ex + url);
			Message message = h.obtainMessage();
			message.obj = a;
			h.sendMessage(message);
			a = new ArrayList();
			a.add(null);
			a.add(0);
			a.add(url);
			return null;
		}
	}

}

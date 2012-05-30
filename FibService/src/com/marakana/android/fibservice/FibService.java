package com.marakana.android.fibservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FibService extends Service {
	static final String TAG = "FibService";
	
	private static IFibServiceImpl service=null;

	@Override
	public void onCreate() {
		super.onCreate();
		service = new IFibServiceImpl();
	}


	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		return service;
	}

}

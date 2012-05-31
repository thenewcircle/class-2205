package com.marakana.android.fibservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LogService extends Service {
	static final String TAG = LogService.class.getSimpleName();

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		return new ILogServiceImpl();
	}

}

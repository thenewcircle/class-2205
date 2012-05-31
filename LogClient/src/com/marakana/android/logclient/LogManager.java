package com.marakana.android.logclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.marakana.android.logcommon.ILogService;

public class LogManager {
	private static final String TAG = LogManager.class.getSimpleName();
	private static ILogService service;
	private static final Intent INTENT = new Intent("com.marakana.android.logcommon.ILogService");
	private static final ServiceConnection CONN = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName component, IBinder binder) {
			service = ILogService.Stub.asInterface(binder);
			Log.d(TAG, "onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName component) {
			service = null;
			Log.d(TAG, "onServiceDisconnected");
		}	
	};

	public LogManager(Context context) {
		boolean ret = context.bindService(INTENT, CONN, Context.BIND_AUTO_CREATE);
		Log.d(TAG, "LogManager ret="+ret);
	}
	
	public void log(int priority, String tag, String message) {
		try {
			service.log(priority, tag, message);
		} catch (RemoteException e) {
			Log.e(TAG, "Failed to run service.log()", e);
		} 
	}
}

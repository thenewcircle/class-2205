package com.marakana.android.fibclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.marakana.android.fibcommon.IFibService;

public class FibActivity extends Activity {
	static final String TAG = "FibActivity";
	private EditText in;
	private TextView out;
	private static IFibService service;
	private boolean ret=false;
	private static final Intent INTENT = new Intent(
			"com.marakana.android.fibcommon.IFibService");

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		in = (EditText) findViewById(R.id.in);
		out = (TextView) findViewById(R.id.out);

		ret = bindService(INTENT, CONN, BIND_AUTO_CREATE);

		Log.d(TAG, "onCreate ret="+ret);
	}

	private static ServiceConnection CONN = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			service = IFibService.Stub.asInterface(binder);
			Log.d(TAG, "onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			service = null;
			Log.d(TAG, "onServiceDisconnected");
		}

	};

	/**
	 * Called when the button is clicked.
	 * 
	 * @throws RemoteException
	 */
	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(in.getText().toString());

		// Java recursive
		long start = System.currentTimeMillis();
		long resultJ = service.fibJ(n);
		long timeJ = System.currentTimeMillis() - start;
		out.append(String.format("\nfibJ(%d)=%d (%d ms)", n, resultJ, timeJ));

		// Native recursive
		start = System.currentTimeMillis();
		long resultN = service.fibN(n);
		long timeN = System.currentTimeMillis() - start;
		out.append(String.format("\nfibN(%d)=%d (%d ms)", n, resultN, timeN));

		Log.d(TAG, "onClick n="+n);
	}
}
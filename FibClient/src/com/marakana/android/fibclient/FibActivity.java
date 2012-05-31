package com.marakana.android.fibclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.marakana.android.fibcommon.IFibListener;
import com.marakana.android.fibcommon.IFibService;
import com.marakana.android.fibcommon.Request;
import com.marakana.android.fibcommon.Response;

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

	private static final int MSG_ID = 47;
	private Handler responseHandler = new Handler()  {

		@Override
		public void handleMessage(Message msg) {
			if(msg.what==MSG_ID) {
				out.append( "\n"+((Response) msg.obj).toString() );
			}
		}
	};
	
	private IFibListener listener = new IFibListener.Stub() {
		@Override
		public void onResponse(Response response) throws RemoteException {
//			out.append(String.format("\nfib()=%s",response));
			Message msg = responseHandler.obtainMessage(MSG_ID, response);
			responseHandler.sendMessage(msg);
		}
	};
	
	/**
	 * Called when the button is clicked.
	 * 
	 * @throws RemoteException
	 */
	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(in.getText().toString());
		
		service.asyncFib( new Request(Request.JAVA_RECURSIVE, n), listener);
		service.asyncFib( new Request(Request.NATIVE_RECURSIVE, n), listener);
		service.asyncFib( new Request(Request.JAVA_ITERATIVE, n), listener);
		service.asyncFib( new Request(Request.NATIVE_ITERATIVE, n), listener);

//		// Java recursive
//		Response resultJ = service.fib( new Request(Request.JAVA_RECURSIVE, n));
//		out.append(String.format("\nfibJ(%d)=%s", n, resultJ));
//
//		// Native recursive
//		Response resultN = service.fib( new Request(Request.NATIVE_RECURSIVE, n));
//		out.append(String.format("\nfibN(%d)=%s", n, resultN));

		out.append("\nrequests submitted");

		Log.d(TAG, "onClick n="+n);
	}
}
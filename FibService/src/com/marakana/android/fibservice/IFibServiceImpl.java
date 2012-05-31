package com.marakana.android.fibservice;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;

import com.marakana.android.fib.FibLib;
import com.marakana.android.fibcommon.IFibListener;
import com.marakana.android.fibcommon.IFibService;
import com.marakana.android.fibcommon.Request;
import com.marakana.android.fibcommon.Response;

public class IFibServiceImpl extends IFibService.Stub {
	private Context context;

	public IFibServiceImpl(Context context) {
		this.context = context;
	}

	@Override
	public long fibJ(long n) throws RemoteException {
		return FibLib.fibJ(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

	@Override
	public Response fib(Request request) throws RemoteException {

		// Check permissions
		if (request.getAlgorithm() == Request.JAVA_ITERATIVE
				|| request.getAlgorithm() == Request.NATIVE_ITERATIVE) {
			if (context
					.checkCallingOrSelfPermission("com.marakana.android.fibcommon.PREMIUM_SERVICE") != PackageManager.PERMISSION_GRANTED)
				Log.d("IFibServiceImpl", "Failed for request: "+request.toString());
				throw new SecurityException("Only slow algorithms for you!");
		}

		long result = 0;
		long time;
		long n = request.getN();
		long start = System.currentTimeMillis();

		switch (request.getAlgorithm()) {
		case Request.JAVA_RECURSIVE:
			result = FibLib.fibJ(n);
			break;
		case Request.JAVA_ITERATIVE:
			result = FibLib.fibJI(n);
			break;
		case Request.NATIVE_RECURSIVE:
			result = FibLib.fibN(n);
			break;
		case Request.NATIVE_ITERATIVE:
			result = FibLib.fibNI(n);
			break;
		}
		time = System.currentTimeMillis() - start;

		return new Response(result, time);
	}

	@Override
	public void asyncFib(Request request, IFibListener listener)
			throws RemoteException {
		// Do the potentially long running job
		Response response = this.fib(request);

		// Notify the caller when done
		listener.onResponse(response);
	}

}

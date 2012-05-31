package com.marakana.android.fibservice;

import android.os.RemoteException;

import com.marakana.android.fib.FibLib;
import com.marakana.android.fibcommon.IFibService;
import com.marakana.android.fibcommon.Request;
import com.marakana.android.fibcommon.Response;

public class IFibServiceImpl extends IFibService.Stub {

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
		long result=0;
		long time;
		long n = request.getN();
		long start = System.currentTimeMillis();
		
		switch(request.getAlgorithm()) {
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

}

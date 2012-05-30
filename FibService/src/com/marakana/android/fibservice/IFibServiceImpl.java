package com.marakana.android.fibservice;

import android.os.RemoteException;

import com.marakana.android.fib.FibLib;
import com.marakana.android.fibcommon.IFibService;

public class IFibServiceImpl extends IFibService.Stub {

	@Override
	public long fibJ(long n) throws RemoteException {
		return FibLib.fibJ(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

}

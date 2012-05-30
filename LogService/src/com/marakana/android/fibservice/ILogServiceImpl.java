package com.marakana.android.fibservice;

import android.os.RemoteException;

import com.marakana.android.logcommon.ILogService;
import com.marakana.android.lognative.LogLib;

public class ILogServiceImpl extends ILogService.Stub {

	@Override
	public void log(int priority, String tag, String message)
			throws RemoteException {
		LogLib.log(priority, tag, message);
	}

}

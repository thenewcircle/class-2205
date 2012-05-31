package com.marakana.android.fibcommon;

import com.marakana.android.fibcommon.Request;
import com.marakana.android.fibcommon.Response;
import com.marakana.android.fibcommon.IFibListener;

interface IFibService {
	long fibJ(long n);
	long fibN(long n);
	Response fib( in Request request);
	oneway void asyncFib( in Request request, in IFibListener listener);
}
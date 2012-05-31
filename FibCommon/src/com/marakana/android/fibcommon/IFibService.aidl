package com.marakana.android.fibcommon;

import com.marakana.android.fibcommon.Request;
import com.marakana.android.fibcommon.Response;

interface IFibService {
	long fibJ(long n);
	long fibN(long n);
	Response fib( in Request r);
}
package com.marakana.android.fibcommon;

import com.marakana.android.fibcommon.Response;

oneway interface IFibListener {
	void onResponse( in Response response );
}
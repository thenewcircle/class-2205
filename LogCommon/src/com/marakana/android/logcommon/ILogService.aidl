package com.marakana.android.logcommon;

import com.marakana.android.logcommon.LogMessage;

interface ILogService {
	void log(int priority, String tag, String message);
	void logit(in LogMessage message);
}
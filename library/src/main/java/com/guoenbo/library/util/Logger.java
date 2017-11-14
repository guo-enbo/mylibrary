package com.guoenbo.library.util;

import android.util.Log;

public class Logger {
	private static Logger mLogger = null;
	private static String TAG = "";

	public static Logger getLogger(String className) {
		TAG = className;
		if (mLogger == null) {
			mLogger = new Logger();
		}
		return mLogger;
	}

	public void debug(String msg) {
		Log.d(TAG, msg);
	}

	public void info(String msg) {
		Log.i(TAG, msg);
	}

	public void error(String msg) {
		Log.e(TAG, msg);
	}

}

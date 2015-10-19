package com.appzone.feminaplus;

import android.app.Application;

/**
 * Created by user on 16/10/15.
 */
public class AnalyticsApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
	AnalyticsTrackers.initialize(this);
	}
}

/**
 * MyApplication.java [V 1..0.0]
 * classes : com.hb56.DriverReservation.android.app.MyApplication
 * zhangyx Create at 2014-11-26 下午4:04:30
 */
package com.zhangyx.MyGestureLock.app;

import com.zhangyx.MyGestureLock.view.LockPatternUtils;

import android.app.Application;

/***
 * 
 *com.zhangyx.MyGestureLock.app.MyApplication
 * @author Admin-zhangyx
 *
 * create at 2015-1-16 下午3:14:07
 */
public class MyApplication extends Application {

	private LockPatternUtils mLockPatternUtils;// 手势锁

	private String userName;
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		// 手势锁
		mLockPatternUtils = new LockPatternUtils(this);

	}

	public LockPatternUtils getLockPatternUtils() {
		return mLockPatternUtils;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}

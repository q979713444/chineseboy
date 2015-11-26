/**
 * BaseActivity.java [V 1..0.0]
 * classes : com.zhangyx.MyGestureLock.BaseActivity
 * zhangyx Create at 2015-1-16 下午2:23:13
 */
package com.zhangyx.MyGestureLock;

import com.zhangyx.MyGestureLock.app.MyApplication;
import com.zhangyx.MyGestureLock.util.ToastHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * com.zhangyx.MyGestureLock.BaseActivity
 * 
 * @author Admin-zhangyx
 * 
 *         create at 2015-1-16 下午2:23:13
 */
public class BaseActivity extends Activity {
	private MyApplication app;// 程序进程

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		app = (MyApplication) getApplication();
	}

	public void showToast(String message) {
		ToastHelper.show(this, message);
	}

	/**
	 * @return the app
	 */
	public MyApplication getApp() {
		return app;
	}

	
}

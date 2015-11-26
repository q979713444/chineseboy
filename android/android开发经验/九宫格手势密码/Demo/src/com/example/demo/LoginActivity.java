package com.example.demo;

import com.example.demo.LocusPassWordView.OnCompleteListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private LocusPassWordView lpwv;
	private static final int SPLASH_SHOW_TIME = 2000;
	Handler handler = new Handler();
	Intent intent = new Intent();

	TextView title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		title = (TextView) findViewById(R.id.login_toast);
		lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);

		if (lpwv.isPasswordEmpty()) {
			title.setVisibility(View.GONE);
			lpwv.setVisibility(View.GONE);
			
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					intent.setClass(getApplicationContext(), MainActivity.class);
					startActivity(intent);
					finish();
				}
			}, SPLASH_SHOW_TIME);
		} else {
			lpwv.setVisibility(View.VISIBLE);
			lpwv.setOnCompleteListener(new OnCompleteListener() {
				@Override
				public void onComplete(String mPassword) {
					// 如果密码正确,则进入主页面。
					if (lpwv.verifyPassword(mPassword)) {
						Toast.makeText(LoginActivity.this, "登录成功！",
								Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(LoginActivity.this, "密码输入错误,请重新输入",
								Toast.LENGTH_SHORT).show();
						lpwv.clearPassword();
					}
				}
			});
		}
	}
}
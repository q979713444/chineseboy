package com.example.demo;

import com.example.demo.LocusPassWordView.OnCompleteListener;
import com.example.util.StringUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SetPasswordActivity extends Activity {

	private LocusPassWordView lpwv;
	private String password;

	private Button btnRight;
	private Button btnLeft;

	private TextView title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setpassword_activity);

		findViews();

		passwordIsEmpty();
	}

	private void findViews() {
		btnRight = (Button) findViewById(R.id.btnright);
		btnLeft = (Button) findViewById(R.id.btnleft);
		lpwv = (LocusPassWordView) findViewById(R.id.mLocusPassWordView);

		title = (TextView) findViewById(R.id.title_text);

		btnRight.setVisibility(View.VISIBLE);
		btnLeft.setVisibility(View.VISIBLE);

	}

	/**
	 * 判断密码是否为空
	 */
	private void passwordIsEmpty() {
		if (lpwv.isPasswordEmpty()) {// 密码为空，设置密码
			title.setText("绘制登录密码图案");

			btnRight.setText("继续");
			btnLeft.setText("取消");

			btnRight.setEnabled(false);
			btnLeft.setEnabled(true);

			lpwv.setOnCompleteListener(new OnCompleteListener() {
				@Override
				public void onComplete(String mPassword) {
					btnRight.setEnabled(true);
					password = mPassword;
					btnLeft.setText("重试");

					carryOn();
				}
			});

			// 删除前一步设置的隐私密码，结束本页面
			btnLeft.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					lpwv.clearPassword();
					lpwv.resetPassWord("");// 密码置空
					finish();
				}
			});
		} else {// 密码不为空，不显示设置按钮，只绘制密码
			title.setText("确认已保存的密码图案");
			btnRight.setVisibility(View.GONE);
			btnLeft.setVisibility(View.GONE);

			lpwv.setOnCompleteListener(new OnCompleteListener() {// 绘制密码
				@Override
				public void onComplete(String mPassword) {
					if (lpwv.verifyPassword(mPassword)) {// 绘制的密码一致，删除密码
						lpwv.clearPassword();
						lpwv.resetPassWord("");// 密码置空
						Toast.makeText(getApplicationContext(), "登录密码已经取消！",
								Toast.LENGTH_SHORT).show();
						finish();
					} else {// 绘制的密码不一致，提示用户
						Toast.makeText(getApplicationContext(), "密码绘制错误！",
								Toast.LENGTH_SHORT).show();
						lpwv.clearPassword();
					}
				}
			});
		}
	}

	/**
	 * 继续
	 */
	private void carryOn() {
		btnRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (StringUtil.isNotEmpty(password)) {
					lpwv.resetPassWord(password);
					lpwv.clearPassword();

					btnRight.setText("确定");
					btnRight.setEnabled(false);
					btnLeft.setText("取消");

					title.setText("确认登录密码图案");

					resetPWD();
				} else {
					lpwv.clearPassword();
					Toast.makeText(SetPasswordActivity.this, "密码不能为空,请输入密码.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		btnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				lpwv.clearPassword();
				lpwv.resetPassWord("");
				btnLeft.setText("取消");
				passwordIsEmpty();
			}
		});
	}

	private void resetPWD() {
		btnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				lpwv.clearPassword();
				lpwv.resetPassWord("");
				finish();
			}
		});

		lpwv.setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(String mPassword) {
				if (lpwv.verifyPassword(mPassword)) {
					btnRight.setEnabled(true);

					btnRight.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							lpwv.resetPassWord(password);
							finish();
						}
					});
				} else {
					Toast.makeText(getApplicationContext(), "密码不一致！",
							Toast.LENGTH_SHORT).show();

					lpwv.clearPassword();
				}
			}
		});
	}
}

/**
 * LoginActivity.java [V 1..0.0]
 * classes : com.hb56.DriverReservation.android.activity.LoginActivity
 * zhangyx Create at 2014-11-26 下午4:22:46
 */
package com.zhangyx.MyGestureLock;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.MyGestureLock.gesture.GuideGesturePasswordActivity;
import com.zhangyx.MyGestureLock.gesture.UnlockGesturePasswordActivity;
import com.zhangyx.MyGestureLock.util.AnimationUtil;
import com.zhangyx.MyGestureLock.util.IntentDialog;
import com.zhangyx.MyGestureLock.util.Util;
import com.zhangyx.MyGestureLock.view.ClearEditText;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 登录界面 com.hb56.DriverReservation.android.activity.LoginActivity
 * 
 * @author Admin-zhangyx
 * 
 *         create at 2014-11-26 下午4:22:46
 */
public class LoginActivity extends BaseActivity {
	
	@ViewInject(R.id.cet_userNo)
	private ClearEditText userName;
	@ViewInject(R.id.cet_userPwd)
	private ClearEditText userPwd;
	@ViewInject(R.id.tv_login)
	private TextView loginBtn;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hb56.DriverReservation.android.BaseActivity#onCreate(android.os.Bundle
	 * )
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 初始化IOC注解
		 ViewUtils.inject(this);
		initView();
	}

	/**
	 * 
	 */
	private void initView() {
		// TODO Auto-generated method stub
		//addTextViewTextChangeListener();
		
		if (null != getApp().getLockPatternUtils()) {
			if (getApp().getLockPatternUtils().savedPatternExists()) {
				startActivity(new Intent(LoginActivity.this,UnlockGesturePasswordActivity.class));
				this.finish();
				return;
			}
		}

		initImgs();
		
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doLoginClickExecute();
			}
		});
	}

	// 初始化 图片和Logo字体
	@SuppressWarnings("deprecation")
	private void initImgs() {
		Drawable mIconPerson = getResources().getDrawable(
				R.drawable.txt_person_icon);
		mIconPerson.setBounds(5, 1, 60, 50);
		Drawable mIconLock = getResources().getDrawable(
				R.drawable.txt_lock_icon);
		mIconLock.setBounds(5, 1, 60, 50);

		userName.setCompoundDrawables(mIconPerson, null, null, null);
		userPwd.setCompoundDrawables(mIconLock, null, null, null);
		TextView topText = (TextView) findViewById(R.id.topname);
		topText.setTextColor(Color.BLACK);
		topText.setTextSize(26.0f);
		topText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
		// 使用TextPaint的仿“粗体”设置setFakeBoldText为true。目前还无法支持仿“斜体”方法
		// Typeface.BOLD_ITALIC
		TextPaint tp = topText.getPaint();

		tp.setFakeBoldText(true);
		ImageView loginImage = (ImageView) findViewById(R.id.loginImage);
		loginImage.setBackgroundDrawable(new BitmapDrawable(Util.toRoundBitmap(
				this, "company.png")));
		loginImage.getBackground().setAlpha(0);
		loginImage.setImageBitmap(Util.toRoundBitmap(this,
				"company.png"));

	}

	private String userNo;
	private String userPwdT;

	private void doLoginClickExecute() {
		userNo = userName.getText().toString();
		userPwdT = userPwd.getText().toString();
		if (TextUtils.isEmpty(userNo)) {
			showToast("用户名不能为空");
			userName.setFocusable(true);
			userName.requestFocus();
			return;
		}
		if (TextUtils.isEmpty(userPwdT)) {
			showToast("密码不能为空");
			userPwd.setFocusable(true);
			userPwd.requestFocus();
			return;
		}
		
		getApp().setUserName(userNo);
		
		startActivity(new Intent(this, GuideGesturePasswordActivity.class));
		AnimationUtil.finishActivityAnimation(LoginActivity.this);
		
		// 请求网络
		//new MyAsyncTaskHttp().execute(userNo);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_UP) {
				IntentDialog.showExitDialog(LoginActivity.this);
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

}

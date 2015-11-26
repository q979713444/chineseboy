package com.zh.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class AppLoadingActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取消标题栏(也可以在manifest里面配置)
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 取消状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.app_loading);

		// 三秒钟之后进入login
		ImageView loadingIv = (ImageView) this.findViewById(R.id.logo_bg);
		// 从浅到深,从百分之10到百分之百
		AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
		animation.setDuration(3000);
		loadingIv.setAnimation(animation);

		// 给animation设置监听
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 三秒之后跳出
				Intent it = new Intent(AppLoadingActivity.this,
						MainActivity.class);
				startActivity(it);
				// 三秒之后 这个窗口就没用了 应该finish
				finish();
			}
		});
	}

}

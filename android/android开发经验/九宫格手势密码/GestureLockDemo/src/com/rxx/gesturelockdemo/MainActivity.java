package com.rxx.gesturelockdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.rxx.view.GestureLockView;
import com.rxx.view.GestureLockView.OnGestureFinishListener;

public class MainActivity extends Activity {

	private GestureLockView gestureLockView;
	private TextView textview;
	private Animation animation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	/**初始化*/
	public void init()
	{
		gestureLockView=(GestureLockView)findViewById(R.id.gestureLockView);
		textview=(TextView)findViewById(R.id.textview);
		animation = new TranslateAnimation(-20, 20, 0, 0);
		animation.setDuration(50);
		animation.setRepeatCount(2);
		animation.setRepeatMode(Animation.REVERSE);
		//设置密码
		gestureLockView.setKey("0124678");
		//手势完成后回调
		gestureLockView.setOnGestureFinishListener(new OnGestureFinishListener() {
			@Override
			public void OnGestureFinish(boolean success, String key) {
				if(success)
				{
					textview.setTextColor(Color.parseColor("#FFFFFF"));
					textview.setVisibility(View.VISIBLE);
					textview.setText("密码正确！");
					textview.startAnimation(animation);
				}
				else
				{
					textview.setTextColor(Color.parseColor("#FF2525"));
					textview.setVisibility(View.VISIBLE);
					textview.setText("密码错误！");
					textview.startAnimation(animation);
				}
			}
		});
	}
}

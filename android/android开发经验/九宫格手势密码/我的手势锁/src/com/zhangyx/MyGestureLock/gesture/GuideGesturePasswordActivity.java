package com.zhangyx.MyGestureLock.gesture;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.MyGestureLock.R;
import com.zhangyx.MyGestureLock.app.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

/***
 * 创建手势密码---按钮提示
 *com.zhangyx.MyGestureLock.gesture.GuideGesturePasswordActivity
 * @author Admin-zhangyx
 *
 * create at 2015-1-16 下午3:09:06
 */
public class GuideGesturePasswordActivity extends Activity {

	@ViewInject(R.id.rootView)
	private RelativeLayout rootView;
	@ViewInject(R.id.gesturepwd_guide_btn)
	private Button gesturepwd_guide_btn;
	
	private MyApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesturepassword_guide);
		ViewUtils.inject(this);
		app=(MyApplication) getApplication();
		gesturepwd_guide_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				toCreateGesturePwd();
			}
		});
	}

	private void toCreateGesturePwd() {
		app.getLockPatternUtils().clearLock();
		Intent intent = new Intent(GuideGesturePasswordActivity.this,
				CreateGesturePasswordActivity.class);
		// 打开新的Activity
		startActivity(intent);
		finish();
	}

}

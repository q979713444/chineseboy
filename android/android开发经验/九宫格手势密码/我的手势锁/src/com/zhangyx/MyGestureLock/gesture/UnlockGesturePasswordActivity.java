package com.zhangyx.MyGestureLock.gesture;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zhangyx.MyGestureLock.BaseActivity;
import com.zhangyx.MyGestureLock.LoginActivity;
import com.zhangyx.MyGestureLock.MainActivity;
import com.zhangyx.MyGestureLock.R;
import com.zhangyx.MyGestureLock.app.MyApplication;
import com.zhangyx.MyGestureLock.util.AnimationUtil;
import com.zhangyx.MyGestureLock.view.LockPatternUtils;
import com.zhangyx.MyGestureLock.view.LockPatternView;
import com.zhangyx.MyGestureLock.view.LockPatternView.Cell;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/***
 * 解锁登录
 *com.zhangyx.MyGestureLock.gesture.UnlockGesturePasswordActivity
 * @author Admin-zhangyx
 *
 * create at 2015-1-16 下午3:09:47
 */
@SuppressLint("ResourceAsColor") 
public class UnlockGesturePasswordActivity extends BaseActivity {
	private LockPatternView mLockPatternView;
	private int mFailedPatternAttemptsSinceLastTimeout = 0;
	private CountDownTimer mCountdownTimer = null;
	// private Handler mHandler = new Handler();

	private Animation mShakeAnim;

	@ViewInject(R.id.gesturepwd_unlock_text)
	private TextView mHeadTextView;
	@ViewInject(R.id.rootView)
	private LinearLayout rootView;
	@ViewInject(R.id.changeUser)
	private Button changeUser;// 切换用户、清除手势锁

	private MyApplication app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesturepassword_unlock);
		ViewUtils.inject(this);
		app=(MyApplication) getApplication();
		mLockPatternView = (LockPatternView) findViewById(R.id.gesturepwd_unlock_lockview);
		mLockPatternView.setOnPatternListener(mChooseNewLockPatternListener);
		mLockPatternView.setTactileFeedbackEnabled(true);
		mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_x);

		changeUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 清除 手势文件
				app.getLockPatternUtils().clearLock();
				toLoginActivity();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 从未创建过手势时，开始创建---没有记住密码
		if (!app.getLockPatternUtils().savedPatternExists()) {
			toLoginActivity();
		}
	}

	private void toLoginActivity() {
		startActivity(new Intent(UnlockGesturePasswordActivity.this,
				LoginActivity.class));
		AnimationUtil
				.finishActivityAnimation(UnlockGesturePasswordActivity.this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mCountdownTimer != null)
			mCountdownTimer.cancel();
	}

	private Runnable mClearPatternRunnable = new Runnable() {
		public void run() {
			mLockPatternView.clearPattern();
		}
	};

	protected LockPatternView.OnPatternListener mChooseNewLockPatternListener = new LockPatternView.OnPatternListener() {

		@Override
		public void onPatternStart() {
			// TODO Auto-generated method stub
			mLockPatternView.removeCallbacks(mClearPatternRunnable);
			patternInProgress();
		}

		public void onPatternDetected(List<Cell> pattern) {
			// TODO Auto-generated method stub
			if (pattern == null)
				return;
			if (app.getLockPatternUtils()
					.checkPattern(pattern)) {// 解锁成功
				mLockPatternView
						.setDisplayMode(LockPatternView.DisplayMode.Correct);

				// 解锁成功返回需要用户信息的页面----
				loginSuccessToMainAcrtivity() ;
			} else {// 解锁失败-----重新登录
				mLockPatternView
						.setDisplayMode(LockPatternView.DisplayMode.Wrong);

				if (pattern.size() >= LockPatternUtils.MIN_PATTERN_REGISTER_FAIL) {

					mFailedPatternAttemptsSinceLastTimeout++;
					int retry = LockPatternUtils.FAILED_ATTEMPTS_BEFORE_TIMEOUT
							- mFailedPatternAttemptsSinceLastTimeout;
					if (retry > 0) {
						changeUser.setVisibility(View.VISIBLE);
						if (retry == 0)
							showToast(UnlockGesturePasswordActivity.this
									.getString(R.string.toastlock));
						mHeadTextView.setText("密码错误，还可以再输入" + retry + "次");
						mHeadTextView.setTextColor(Color.RED);
						mHeadTextView.startAnimation(mShakeAnim);
					} else {
						// 打开新的Activity
						// 清除 手势文件
						app.getLockPatternUtils().clearLock();
						toLoginActivity();
					}
				} else {
					showToast("输入长度不够，请重试");
				}
				mLockPatternView.clearPattern();
			}
		}

		@Override
		public void onPatternCleared() {
			// TODO Auto-generated method stub
			mLockPatternView.removeCallbacks(mClearPatternRunnable);
		}

		private void patternInProgress() {
		}

		@Override
		public void onPatternCellAdded(List<Cell> pattern) {
			// TODO Auto-generated method stub

		}

		
	};

	Runnable attemptLockout = new Runnable() {

		@Override
		public void run() {
			mLockPatternView.clearPattern();
			mLockPatternView.setEnabled(false);
			mCountdownTimer = new CountDownTimer(
					LockPatternUtils.FAILED_ATTEMPT_TIMEOUT_MS + 1, 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					int secondsRemaining = (int) (millisUntilFinished / 1000) - 1;
					if (secondsRemaining > 0) {
						mHeadTextView.setText(secondsRemaining + " 秒后重试");
					} else {
						mHeadTextView
								.setText(UnlockGesturePasswordActivity.this
										.getString(R.string.gesture_drawPwd));
						mHeadTextView.setTextColor(Color.WHITE);
					}

				}

				@Override
				public void onFinish() {
					mLockPatternView.setEnabled(true);
					mFailedPatternAttemptsSinceLastTimeout = 0;
				}
			}.start();
		}
	};
	
	// 登录成功
	private void loginSuccessToMainAcrtivity() {
		startActivity(new Intent(UnlockGesturePasswordActivity.this,
				MainActivity.class));
		AnimationUtil
				.finishActivityAnimation(UnlockGesturePasswordActivity.this);
	}

}

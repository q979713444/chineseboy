package com.example.testsplashview;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class Page1 extends BaseInShowPage {
	public static final String ACTION_OVER = "page1_animation_done";
	public static final long ANIMATION_NORMAL = 1000L;
	public static float Factor = 0.0F;
	public static int ScreenHeight = 0;
	public static int ScreenWidth = 0;
	public static final String TAG = "Page1";
	private ImageView mBattery;
	private ImageView mBee;
	private ImageView mClothe;
	private ImageView mDoraemon;
	private ImageView mHeadset;
	private boolean mIsNaturalOver;
	private boolean mIsOverActionCalled;
	private ImageView mPhone;
	private ImageView mRing;
	private RevealLayout mRootview;
	private ImageView mRouter;
	private AnimatorSet mSet;
	private ImageView mText;
	private int mTextHeight;
	private int mTextWidth;
	private ImageView mTv;

	@Override
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		View localView = paramLayoutInflater.inflate(R.layout.fragment_gameinshow_page1, null, false);
		DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
		ScreenWidth = localDisplayMetrics.widthPixels;
		ScreenHeight = localDisplayMetrics.heightPixels;
		if (ScreenWidth == 1080){
			Factor = 3.0F;
		}else if (ScreenWidth == 720){
			Factor = 1.5F;
		}else{
			Factor = 1.0F;
		}
		mSet = new AnimatorSet();
		setupViews(localView);
		
		return localView;
	}
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	@Override
	public void onPause() {
		super.onPause();
		mRootview.resetAnimation();
		if ((mSet == null) || (!mSet.isRunning()))
			return;
		mSet.cancel();
	}
	@Override
	public void onResume() {
		super.onResume();
		if (!((PowerManager)getActivity().getSystemService("power")).isScreenOn())
		      return;
	    mRootview.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				doCurrentViewAniamtion();
			}
		}, 500L);
	}

	private void setupViews(View paramView) {
		mRootview = ((RevealLayout) paramView.findViewById(R.id.gameinshow_page1_rootview));
		int i = View.MeasureSpec.makeMeasureSpec(0, 0);
		int j = View.MeasureSpec.makeMeasureSpec(0, 0);
		mText = ((ImageView) paramView.findViewById(R.id.doraemon_text));
		mText.measure(i, j);
		mTextWidth = mText.getMeasuredWidth();
		mTextHeight = mText.getMeasuredHeight();
		mDoraemon = ((ImageView) paramView.findViewById(R.id.doraemon));
		mRouter = ((ImageView) paramView.findViewById(R.id.router));
		mHeadset = ((ImageView) paramView.findViewById(R.id.headset));
		mTv = ((ImageView) paramView.findViewById(R.id.tv));
		mBattery = ((ImageView) paramView.findViewById(R.id.battery));
		mClothe = ((ImageView) paramView.findViewById(R.id.clothe));
		mBee = ((ImageView) paramView.findViewById(R.id.bee));
		mPhone = ((ImageView) paramView.findViewById(R.id.phone));
		mRing = ((ImageView) paramView.findViewById(R.id.ring));
	}

	private void setAnimation() {
		doAnimateOpen(mRouter, 0, 8, px2dip(450.0F) * Factor);
		doRotationAnimation(mRouter);
		doAnimateOpen(mHeadset, 1, 8, px2dip(500.0F) * Factor);
		doRotationAnimation(mHeadset);
		doAnimateOpen(mTv, 2, 8, px2dip(600.0F) * Factor);
		doRotationAnimation(mTv);
		doAnimateOpen(mBattery, 3, 8, px2dip(400.0F) * Factor);
		doRotationAnimation(mBattery);
		doAnimateOpen(mClothe, 4, 8, px2dip(550.0F) * Factor);
		doRotationAnimation(mClothe);
		doAnimateOpen(mBee, 5, 8, px2dip(350.0F) * Factor);
		doRotationAnimation(mBee);
		doAnimateOpen(mPhone, 6, 8, px2dip(400.0F) * Factor);
		doRotationAnimation(mPhone);
		doAnimateOpen(mRing, 7, 8, px2dip(350.0F) * Factor);
		doRotationAnimation(mRing);
		doAnimateText(mText);
		mSet.setDuration(1000L).start();
		mSet.setInterpolator(new DecelerateInterpolator());
		mSet.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator arg0) {
			}

			@Override
			public void onAnimationRepeat(Animator arg0) {
			}

			@Override
			public void onAnimationEnd(Animator arg0) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						doCloseAnimation();
					}
				}, 800L);
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
			}
		});
	}

	public int px2dip(float paramFloat) {
		return (int) (0.5F + paramFloat
				/ this.getResources().getDisplayMetrics().density);
	}
	private void doAnimateOpen(View paramView, int paramInt1, int paramInt2,
			float paramFloat) {
		if (paramView.getVisibility() != 0){
			paramView.setVisibility(0);
		}
		double d = Math.PI * (paramInt2 - paramInt1-1)
				/ (paramInt2 - 1);
		int i = (int) (paramFloat * Math.cos(d));
		int j = (int) (paramFloat * Math.sin(-d));
		AnimatorSet localAnimatorSet = mSet;
		Animator[] arrayOfAnimator = new Animator[2];
		float[] arrayOfFloat1 = new float[2];
		arrayOfFloat1[0] = 0.0F;
		arrayOfFloat1[1] = i;
		arrayOfAnimator[0] = ObjectAnimator.ofFloat(paramView, "translationX",
				arrayOfFloat1);
		float[] arrayOfFloat2 = new float[2];
		arrayOfFloat2[0] = 0.0F;
		arrayOfFloat2[1] = j;
		arrayOfAnimator[1] = ObjectAnimator.ofFloat(paramView, "translationY",
				arrayOfFloat2);
		localAnimatorSet.playTogether(arrayOfAnimator);
		
	}

	private void doAnimateText(View paramView) {
		if (paramView.getVisibility() != 0)
			paramView.setVisibility(0);
		AnimatorSet localAnimatorSet = mSet;
		Animator[] arrayOfAnimator = new Animator[1];
		float[] arrayOfFloat = new float[3];
		arrayOfFloat[0] = 0.0F;
		arrayOfFloat[1] = 0.8F;
		arrayOfFloat[2] = 1.0F;
		arrayOfAnimator[0] = ObjectAnimator.ofFloat(paramView, "alpha",
				arrayOfFloat);
		localAnimatorSet.playTogether(arrayOfAnimator);
	}

	private void doRotationAnimation(View paramView) {
		float[] arrayOfFloat = new float[2];
		arrayOfFloat[0] = 0.0F;
		arrayOfFloat[1] = 360.0F;
		ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView,
				"rotation", arrayOfFloat);
		localObjectAnimator.setRepeatMode(-1);
		localObjectAnimator.setInterpolator(new AccelerateInterpolator());
		localObjectAnimator.setDuration(2000L).start();
	}
	@Override
	protected void doCloseAnimation() {
		mRootview.post(new Runnable() {

			@Override
			public void run() {
				mRootview.hide(800, new AnimatorListener() {
					@Override
					public void onAnimationStart(Animator arg0) {
						mIsNaturalOver = true;
						mIsOverActionCalled = true;
					}

					@Override
					public void onAnimationRepeat(Animator arg0) {
					}

					@Override
					public void onAnimationEnd(Animator arg0) {
						if (!mRootview.isContentShown()){
							return;
						}
						sendOverAction("page1_animation_done");
					}

					@Override
					public void onAnimationCancel(Animator arg0) {
						mIsNaturalOver = false;
					}
				});

			}
		});
	}
	@Override
	protected void doCurrentViewAniamtion() {
		setAnimation();
	}
	@Override
	protected String getPageTag() {
		return "Page1";
	}
	@Override
	protected String getWaitingAction() {
		return "page0_animation_done";
	}
}
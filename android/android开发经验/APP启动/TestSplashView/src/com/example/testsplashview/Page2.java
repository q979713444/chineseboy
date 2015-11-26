package com.example.testsplashview;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class Page2 extends BaseInShowPage implements
		Page2Layout.TranslationYLinstener {
	public static final String ACTION_OVER = "page2_animation_done";
	public static final long ANIMATION_NORMAL = 800L;
	public static final String TAG = "Page2";
	private ImageView mBird;
	int mBirdHeight;
	int mBirdWidth;
	private Page2Layout mCloseAnimationLayout;
	private ImageView mCloudBottomRight;
	int mCloudBottomRightHeight;
	int mCloudBottomRightWidth;
	private ImageView mCloudMiddleRight;
	int mCloudMiddleRightHeight;
	int mCloudMiddleRightWidth;
	private ImageView mCloudUpLeft;
	int mCloudUpLeftHeight;
	int mCloudUpLeftWidth;
	private ImageView mCloudUpRight;
	int mCloudUpRightHeight;
	int mCloudUpRightWidth;
	private boolean mIsNaturalOver;
	private boolean mIsOverActionCalled ;
	private RelativeLayout mRootview;
	private int mScreenHeight;
	private int mScreenWidth;
	AnimatorSet mSet = new AnimatorSet();
	private ImageView mSuperMan;
	int mSuperManHeight;
	int mSuperManWidth;
	private ImageView mText;
	int mTextHeight;
	int mTextWidth;

	private void setAnimation() {
		setViewVisible();
		ImageView localImageView1 = mSuperMan;
		float[] arrayOfFloat1 = new float[2];
		arrayOfFloat1[0] = (-mSuperManWidth);
		arrayOfFloat1[1] = ((mScreenWidth - mSuperManWidth) / 2);
		ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(
				localImageView1, "translationX", arrayOfFloat1);
		localObjectAnimator1.setInterpolator(new DecelerateInterpolator());
		ImageView localImageView2 = mSuperMan;
		float[] arrayOfFloat2 = new float[2];
		arrayOfFloat2[0] = (mScreenHeight + mSuperManHeight);
		arrayOfFloat2[1] = ((mScreenHeight - mSuperManHeight) / 2);
		ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(
				localImageView2, "translationY", arrayOfFloat2);
		localObjectAnimator2.setInterpolator(new DecelerateInterpolator());
		AnimatorSet localAnimatorSet = mSet;
		Animator[] arrayOfAnimator = new Animator[8];
		arrayOfAnimator[0] = localObjectAnimator1;
		arrayOfAnimator[1] = localObjectAnimator2;
		ImageView localImageView3 = mBird;
		float[] arrayOfFloat3 = new float[2];
		arrayOfFloat3[0] = (-mBirdWidth);
		arrayOfFloat3[1] = 0.0F;
		arrayOfAnimator[2] = ObjectAnimator.ofFloat(localImageView3,
				"translationX", arrayOfFloat3);
		ImageView localImageView4 = mCloudUpLeft;
		float[] arrayOfFloat4 = new float[2];
		arrayOfFloat4[0] = (-mCloudUpLeftWidth);
		arrayOfFloat4[1] = 0.0F;
		arrayOfAnimator[3] = ObjectAnimator.ofFloat(localImageView4,
				"translationX", arrayOfFloat4);
		ImageView localImageView5 = mCloudUpRight;
		float[] arrayOfFloat5 = new float[2];
		arrayOfFloat5[0] = mScreenWidth;
		arrayOfFloat5[1] = 0.0F;
		arrayOfAnimator[4] = ObjectAnimator.ofFloat(localImageView5,
				"translationX", arrayOfFloat5);
		ImageView localImageView6 = mCloudBottomRight;
		float[] arrayOfFloat6 = new float[2];
		arrayOfFloat6[0] = mScreenWidth;
		arrayOfFloat6[1] = 0.0F;
		arrayOfAnimator[5] = ObjectAnimator.ofFloat(localImageView6,
				"translationX", arrayOfFloat6);
		ImageView localImageView7 = mCloudMiddleRight;
		float[] arrayOfFloat7 = new float[2];
		arrayOfFloat7[0] = (-mCloudMiddleRightWidth);
		arrayOfFloat7[1] = 0.0F;
		arrayOfAnimator[6] = ObjectAnimator.ofFloat(localImageView7,
				"translationX", arrayOfFloat7);
		ImageView localImageView8 = mText;
		float[] arrayOfFloat8 = new float[2];
		arrayOfFloat8[0] = 0.0F;
		arrayOfFloat8[1] = 1.0F;
		arrayOfAnimator[7] = ObjectAnimator.ofFloat(localImageView8, "alpha",
				arrayOfFloat8);
		localAnimatorSet.playTogether(arrayOfAnimator);
		mSet.setDuration(800L).start();
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
					public void run() {
						setNextAnimation();
					}
				}, 400L);
			}

			@Override
			public void onAnimationCancel(Animator arg0) {

			}
		});
	}

	private void setNextAnimation()
  {
    AnimatorSet localAnimatorSet = new AnimatorSet();
    ImageView localImageView1 = mSuperMan;
    float[] arrayOfFloat1 = new float[2];
    arrayOfFloat1[0] = ((mScreenWidth - mSuperManWidth) / 2);
    arrayOfFloat1[1] = mScreenWidth;
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localImageView1, "translationX", arrayOfFloat1);
    localObjectAnimator1.setInterpolator(new AccelerateInterpolator());
    ImageView localImageView2 = mSuperMan;
    float[] arrayOfFloat2 = new float[2];
    arrayOfFloat2[0] = ((mScreenHeight - mSuperManHeight) / 2);
    arrayOfFloat2[1] = (-mSuperManHeight);
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localImageView2, "translationY", arrayOfFloat2);
    localObjectAnimator2.setInterpolator(new AccelerateInterpolator());
    Animator[] arrayOfAnimator = new Animator[7];
    arrayOfAnimator[0] = localObjectAnimator1;
    arrayOfAnimator[1] = localObjectAnimator2;
    ImageView localImageView3 = mBird;
    float[] arrayOfFloat3 = new float[2];
    arrayOfFloat3[0] = 0.0F;
    arrayOfFloat3[1] = mScreenWidth;
    arrayOfAnimator[2] = ObjectAnimator.ofFloat(localImageView3, "translationX", arrayOfFloat3);
    ImageView localImageView4 = mCloudUpLeft;
    float[] arrayOfFloat4 = new float[2];
    arrayOfFloat4[0] = 0.0F;
    arrayOfFloat4[1] = mScreenWidth;
    arrayOfAnimator[3] = ObjectAnimator.ofFloat(localImageView4, "translationX", arrayOfFloat4);
    ImageView localImageView5 = mCloudUpRight;
    float[] arrayOfFloat5 = new float[2];
    arrayOfFloat5[0] = 0.0F;
    arrayOfFloat5[1] = (-3 * mCloudUpRightWidth);
    arrayOfAnimator[4] = ObjectAnimator.ofFloat(localImageView5, "translationX", arrayOfFloat5);
    ImageView localImageView6 = mCloudBottomRight;
    float[] arrayOfFloat6 = new float[2];
    arrayOfFloat6[0] = 0.0F;
    arrayOfFloat6[1] = (-3 * mCloudBottomRightWidth);
    arrayOfAnimator[5] = ObjectAnimator.ofFloat(localImageView6, "translationX", arrayOfFloat6);
    ImageView localImageView7 = mCloudMiddleRight;
    float[] arrayOfFloat7 = new float[2];
    arrayOfFloat7[0] = 0.0F;
    arrayOfFloat7[1] = mScreenWidth;
    arrayOfAnimator[6] = ObjectAnimator.ofFloat(localImageView7, "translationX", arrayOfFloat7);
    localAnimatorSet.playTogether(arrayOfAnimator);
    localAnimatorSet.setDuration(800L).start();
    localAnimatorSet.addListener(new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator arg0) {
		}
		@Override
		public void onAnimationRepeat(Animator arg0) {
		}
		@Override
		public void onAnimationEnd(Animator arg0) {
			doCloseAnimation();
		}
		@Override
		public void onAnimationCancel(Animator arg0) {
		}
	});
  }

	private void setViewVisible() {
		mSuperMan.setVisibility(0);
		mBird.setVisibility(0);
		mCloudUpRight.setVisibility(0);
		mCloudUpLeft.setVisibility(0);
		mCloudBottomRight.setVisibility(0);
		mCloudMiddleRight.setVisibility(0);
		mText.setVisibility(0);
	}
	
	private void setupViews(View paramView) {
		mRootview = ((RelativeLayout) paramView.findViewById(R.id.gameinshow_page2_rootview));
		mCloseAnimationLayout = ((Page2Layout) paramView
				.findViewById(R.id.gameinshow_page2_xiaocaiqi));
		mCloseAnimationLayout.setTranslationYLinstener(this);
		int i = View.MeasureSpec.makeMeasureSpec(0, 0);
		int j = View.MeasureSpec.makeMeasureSpec(0, 0);
		mText = ((ImageView) paramView.findViewById(R.id.superman_text));
		mText.measure(i, j);
		mTextWidth = mText.getMeasuredWidth();
		mTextHeight = mText.getMeasuredHeight();
		mSuperMan = ((ImageView) paramView.findViewById(R.id.superman));
		mSuperMan.measure(i, j);
		mSuperManWidth = mSuperMan.getMeasuredWidth();
		mSuperManHeight = mSuperMan.getMeasuredHeight();
		mBird = ((ImageView) paramView.findViewById(R.id.bird));
		mBird.measure(i, j);
		mBirdWidth = mBird.getMeasuredWidth();
		mBirdHeight = mBird.getMeasuredHeight();
		mCloudBottomRight = ((ImageView) paramView.findViewById(R.id.cloud_bottom_right));
		mCloudBottomRight.measure(i, j);
		mCloudBottomRightWidth = mCloudBottomRight.getMeasuredWidth();
		mCloudBottomRightHeight = mCloudBottomRight.getMeasuredHeight();
		mCloudUpRight = ((ImageView) paramView.findViewById(R.id.cloud_up_right));
		mCloudUpRight.measure(i, j);
		mCloudUpRightWidth = mCloudUpRight.getMeasuredWidth();
		mCloudUpRightHeight = mCloudUpRight.getMeasuredHeight();
		mCloudUpLeft = ((ImageView) paramView.findViewById(R.id.cloud_up_left));
		mCloudUpLeft.measure(i, j);
		mCloudUpLeftWidth = mCloudUpLeft.getMeasuredWidth();
		mCloudUpLeftHeight = mCloudUpLeft.getMeasuredHeight();
		mCloudMiddleRight = ((ImageView) paramView.findViewById(R.id.cloud_middle_left));
		mCloudMiddleRight.measure(i, j);
		mCloudMiddleRightWidth = mCloudMiddleRight.getMeasuredWidth();
		mCloudMiddleRightHeight = mCloudMiddleRight.getMeasuredHeight();
	}
	@Override
	protected void doCloseAnimation()
  {
    mCloseAnimationLayout.post(new Runnable() {
		
		@Override
		public void run() {
			mCloseAnimationLayout.hide(1290, new AnimatorListener() {
				@Override
				public void onAnimationStart(Animator arg0) {
					mIsOverActionCalled = true;
					mIsNaturalOver = true;
				}
				@Override
				public void onAnimationRepeat(Animator arg0) {
				}
				@Override
				public void onAnimationEnd(Animator arg0) {
					  if (!mIsNaturalOver)
					      return;
					   sendOverAction("page2_animation_done");
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
		return "Page2";
	}
	@Override
	protected String getWaitingAction() {
		return "page1_animation_done";
	}
	@Override
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		View localView = paramLayoutInflater.inflate(R.layout.fragment_gameinshow_page2, null, false);
		DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
		mScreenWidth = localDisplayMetrics.widthPixels;
		mScreenHeight = localDisplayMetrics.heightPixels;
		setupViews(localView);
		return localView;
	}
	@Override
	public void onPause() {
		super.onPause();
		mCloseAnimationLayout.cancelAnimation();
		if ((mSet == null) || (!mSet.isRunning()))
			return;
		mSet.cancel();
	}
	@Override
	public void onResume() {
		super.onResume();
		if (!mIsOverActionCalled)
			return;
		doCurrentViewAniamtion();
	}
	@Override
	public void onTranslationYChange(float paramFloat) {
		if ((mRootview == null) || (paramFloat <= mScreenHeight))
			return;
		mRootview.setVisibility(View.GONE);
	}
}
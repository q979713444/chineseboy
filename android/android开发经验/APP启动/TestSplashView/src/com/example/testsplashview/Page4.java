package com.example.testsplashview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class Page4 extends BaseInShowPage
  implements Page0Layout.TranslationYLinstener
{
  public static final String ACTION_OVER = "page4_animation_done";
  public static final String TAG = "Page4";
  private Page0Layout mCloseAnimationLayout;
  private boolean mIsNaturalOver;
  private boolean mIsOverActionCalled;
  private RelativeLayout mRootLayout;
  private ImageView mSpiderLeft;
  private int mSpiderLeftHeight;
  private ImageView mSpiderMiddle;
  private int mSpiderMiddleHeight;
  private ImageView mSpiderRight;
  private int mSpiderRightHeight;
  private ImageView mText;
  int mTextHeight;
  int mTextWidth;

  private void setupViews(View paramView)
  {
    int i = View.MeasureSpec.makeMeasureSpec(0, 0);
    int j = View.MeasureSpec.makeMeasureSpec(0, 0);
    mCloseAnimationLayout = ((Page0Layout)paramView.findViewById(R.id.gameinshow_page4_xiaocaiqi));
    mCloseAnimationLayout.setTranslationYLinstener(this);
    mRootLayout = ((RelativeLayout)paramView.findViewById(R.id.gameinshow_page4_rootview));
    mText = ((ImageView)paramView.findViewById(R.id.spiderman_text));
    mText.measure(i, j);
    mTextWidth = mText.getMeasuredWidth();
    mTextHeight = mText.getMeasuredHeight();
    mSpiderLeft = ((ImageView)paramView.findViewById(R.id.spider_left));
    mSpiderLeft.measure(i, j);
    mSpiderLeftHeight = mSpiderLeft.getMeasuredHeight();
    mSpiderMiddle = ((ImageView)paramView.findViewById(R.id.spider_middle));
    mSpiderMiddle.measure(i, j);
    mSpiderMiddleHeight = mSpiderMiddle.getMeasuredHeight();
    mSpiderRight = ((ImageView)paramView.findViewById(R.id.spider_right));
    mSpiderRight.measure(i, j);
    mSpiderRightHeight = mSpiderRight.getMeasuredHeight();
  }

  private void startAnimaton()
  {
    mText.setVisibility(0);
    mSpiderMiddle.setVisibility(0);
    AnimatorSet localAnimatorSet = new AnimatorSet();
    ImageView localImageView1 = mSpiderMiddle;
    float[] arrayOfFloat1 = new float[4];
    arrayOfFloat1[0] = (-mSpiderMiddleHeight);
    arrayOfFloat1[1] = 0.0F;
    arrayOfFloat1[2] = -100.0F;
    arrayOfFloat1[3] = -50.0F;
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localImageView1, "translationY", arrayOfFloat1);
    localObjectAnimator1.addListener(new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator arg0) {
		}
		@Override
		public void onAnimationRepeat(Animator arg0) {
		}
		@Override
		public void onAnimationEnd(Animator arg0) {
		}
		@Override
		public void onAnimationCancel(Animator arg0) {
		}
	});
    ImageView localImageView2 = mSpiderLeft;
    float[] arrayOfFloat2 = new float[4];
    arrayOfFloat2[0] = (-mSpiderLeftHeight);
    arrayOfFloat2[1] = 0.0F;
    arrayOfFloat2[2] = -100.0F;
    arrayOfFloat2[3] = -50.0F;
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localImageView2, "translationY", arrayOfFloat2);
    localObjectAnimator2.setStartDelay(200L);
    localObjectAnimator2.addListener(new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator arg0) {
			mSpiderLeft.setVisibility(View.VISIBLE);
		}
		@Override
		public void onAnimationRepeat(Animator arg0) {
		}
		@Override
		public void onAnimationEnd(Animator arg0) {
		}
		@Override
		public void onAnimationCancel(Animator arg0) {
			
		}
	});
    ImageView localImageView3 = mSpiderRight;
    float[] arrayOfFloat3 = new float[4];
    arrayOfFloat3[0] = (-mSpiderRightHeight);
    arrayOfFloat3[1] = 0.0F;
    arrayOfFloat3[2] = -100.0F;
    arrayOfFloat3[3] = -50.0F;
    ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(localImageView3, "translationY", arrayOfFloat3);
    localObjectAnimator3.addListener(new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator arg0) {
			mSpiderRight.setVisibility(View.VISIBLE);
		}
		@Override
		public void onAnimationRepeat(Animator arg0) {
		}
		@Override
		public void onAnimationEnd(Animator arg0) {
		}
		@Override
		public void onAnimationCancel(Animator arg0) {
		}
	});
    localObjectAnimator3.setStartDelay(200L);
    Animator[] arrayOfAnimator = new Animator[4];
    arrayOfAnimator[0] = localObjectAnimator1;
    arrayOfAnimator[1] = localObjectAnimator2;
    arrayOfAnimator[2] = localObjectAnimator3;
    ImageView localImageView4 = mText;
    float[] arrayOfFloat4 = new float[2];
    arrayOfFloat4[0] = 0.0F;
    arrayOfFloat4[1] = 1.0F;
    arrayOfAnimator[3] = ObjectAnimator.ofFloat(localImageView4, "alpha", arrayOfFloat4);
    localAnimatorSet.playTogether(arrayOfAnimator);
    localAnimatorSet.setDuration(1000L).start();
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

  protected void doCloseAnimation()
  {
    mCloseAnimationLayout.post(new Runnable() {
		
		@Override
		public void run() {
			mCloseAnimationLayout.hide(1500, new AnimatorListener() {
				@Override
				public void onAnimationStart(Animator arg0) {
					mIsNaturalOver = true;
					mIsOverActionCalled = true;
					
				}
				
				@Override
				public void onAnimationRepeat(Animator arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animator arg0) {
					if (!mIsNaturalOver)
					      return;
					sendOverAction("page4_animation_done");
					
				}
				
				@Override
				public void onAnimationCancel(Animator arg0) {
					mIsNaturalOver = false;
					
				}
			});
			
		}
	});
  }

  protected void doCurrentViewAniamtion()
  {
    startAnimaton();
  }

  protected String getPageTag()
  {
    return "Page4";
  }

  protected String getWaitingAction()
  {
    return "page3_animation_done";
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(R.layout.fragment_gameinshow_page4, null, false);
    setupViews(localView);
    return localView;
  }

  public void onResume()
  {
    super.onResume();
    mRootLayout.setVisibility(0);
    if (!mIsOverActionCalled)
      return;
    doCurrentViewAniamtion();
  }

  public void onTranslationYChange(float paramFloat)
  {
    Log.d("Page4", "YYYYY " + paramFloat);
    if ((mRootLayout == null) || (paramFloat + mCloseAnimationLayout.getXiaocaiqiHeight() <= 2 * Page1.ScreenHeight))
      return;
    mRootLayout.setVisibility(8);
  }
}
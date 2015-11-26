package com.example.testsplashview;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class Page3 extends BaseInShowPage
  implements Page3Layout.TranslationYLinstener
{
  public static final String ACTION_OVER = "page3_animation_done";
  public static final long ANIMATION_NORMAL = 800L;
  public static final String TAG = "Page3";
  private Page3Layout mCloseAnimationLayout;
  private boolean mIsNaturalOver;
  private boolean mIsOverActionCalled ;
  private int[] mLocationPoint11 = new int[2];
  private int[] mLocationPoint12 = new int[2];
  private int[] mLocationPoint21 = new int[2];
  private int[] mLocationPoint22 = new int[2];
  private int[] mLocationPoint31 = new int[2];
  private int[] mLocationPoint32 = new int[2];
  private int[] mLocationPoint41 = new int[2];
  private int[] mLocationPoint42 = new int[2];
  private int[] mLocationPoint51 = new int[2];
  private int[] mLocationPoint52 = new int[2];
  private int[] mPos1 = new int[2];
  private int[] mPos2 = new int[2];
  private int[] mPos3 = new int[2];
  private int[] mPos4 = new int[2];
  private int[] mPos5 = new int[2];
  private RelativeLayout mRootLayout;
  private AnimatorSet mSet;
  private ImageView mShell1;
  private int mShell1Height;
  private int mShell1Width;
  private ImageView mShell2;
  private int mShell2Height;
  private int mShell2Width;
  private ImageView mShell3;
  private int mShell3Height;
  private int mShell3Width;
  private ImageView mShell4;
  private int mShell4Height;
  private int mShell4Width;
  private ImageView mShell5;
  private int mShell5Height;
  private int mShell5Width;
  private ImageView mSurface1;
  private ImageView mSurface2;
  private ImageView mSurface3;
  private ImageView mSurface4;
  private ImageView mSurface5;
  private ImageView mText;
  private int mTextHeight;
  private int mTextWidth;
  private View mViewPos11;
  private View mViewPos12;
  private View mViewPos21;
  private View mViewPos22;
  private View mViewPos31;
  private View mViewPos32;
  private View mViewPos41;
  private View mViewPos42;
  private View mViewPos51;
  private View mViewPos52;

  private void getPointView(View paramView)
  {
    mViewPos11 = paramView.findViewById(R.id.view1_1);
    mViewPos12 = paramView.findViewById(R.id.view1_2);
    mViewPos21 = paramView.findViewById(R.id.view2_1);
    mViewPos22 = paramView.findViewById(R.id.view2_2);
    mViewPos31 = paramView.findViewById(R.id.view3_1);
    mViewPos32 = paramView.findViewById(R.id.view3_2);
    mViewPos41 = paramView.findViewById(R.id.view4_1);
    mViewPos42 = paramView.findViewById(R.id.view4_2);
    mViewPos51 = paramView.findViewById(R.id.view5_1);
    mViewPos52 = paramView.findViewById(R.id.view5_2);
  }

  private void setupViews(View paramView)
  {
    mCloseAnimationLayout = ((Page3Layout)paramView.findViewById(R.id.gameinshow_page3_closeanimation_view));
    mCloseAnimationLayout.setTranslationYLinstener(this);
    mRootLayout = ((RelativeLayout)paramView.findViewById(R.id.gameinshow_page3_rootview));
    int i = View.MeasureSpec.makeMeasureSpec(0, 0);
    int j = View.MeasureSpec.makeMeasureSpec(0, 0);
    mText = ((ImageView)paramView.findViewById(R.id.ironman_text));
    mText.measure(i, j);
    mTextWidth = mText.getMeasuredWidth();
    mTextHeight = mText.getMeasuredHeight();
    mShell1 = ((ImageView)paramView.findViewById(R.id.shell1));
    mSurface1 = ((ImageView)paramView.findViewById(R.id.surface1));
    mShell1.measure(i, j);
    mShell1Width = mShell1.getMeasuredWidth();
    mShell1Height = mShell1.getMeasuredHeight();
    mShell2 = ((ImageView)paramView.findViewById(R.id.shell2));
    mSurface2 = ((ImageView)paramView.findViewById(R.id.surface2));
    mShell2.measure(i, j);
    mShell2Width = mShell2.getMeasuredWidth();
    mShell2Height = mShell2.getMeasuredHeight();
    mShell3 = ((ImageView)paramView.findViewById(R.id.shell3));
    mSurface3 = ((ImageView)paramView.findViewById(R.id.surface3));
    mShell3.measure(i, j);
    mShell3Width = mShell3.getMeasuredWidth();
    mShell3Height = mShell3.getMeasuredHeight();
    mShell4 = ((ImageView)paramView.findViewById(R.id.shell4));
    mSurface4 = ((ImageView)paramView.findViewById(R.id.surface4));
    mShell4.measure(i, j);
    mShell4Width = mShell4.getMeasuredWidth();
    mShell4Height = mShell4.getMeasuredHeight();
    mShell5 = ((ImageView)paramView.findViewById(R.id.shell5));
    mSurface5 = ((ImageView)paramView.findViewById(R.id.surface5));
    mShell5.measure(i, j);
    mShell5Width = mShell5.getMeasuredWidth();
    mShell5Height = mShell5.getMeasuredHeight();
    getPointView(paramView);
  }

  private void startAnimaton()
  {
    mShell4.setVisibility(0);
    mSurface4.setVisibility(0);
    mText.setVisibility(0);
    float f1 = px2dip(10.0F) * Page1.Factor;
    float f2 = px2dip(16.0F) * Page1.Factor;
    float f3 = px2dip(18.0F) * Page1.Factor;
    float f4 = px2dip(20.0F) * Page1.Factor;
    float f5 = px2dip(22.0F) * Page1.Factor;
    mSet = new AnimatorSet();
    ImageView localImageView1 = mShell1;
    float[] arrayOfFloat1 = new float[2];
    arrayOfFloat1[0] = (mLocationPoint11[0] - mShell1Width);
    arrayOfFloat1[1] = (mPos1[0] - mShell1Width / 2);
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localImageView1, "translationX", arrayOfFloat1);
    ImageView localImageView2 = mShell1;
    float[] arrayOfFloat2 = new float[2];
    arrayOfFloat2[0] = (mLocationPoint11[1] - mShell1Height);
    arrayOfFloat2[1] = (mPos1[1] - mShell1Height);
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localImageView2, "translationY", arrayOfFloat2);
    ImageView localImageView3 = mSurface1;
    float[] arrayOfFloat3 = new float[2];
    arrayOfFloat3[0] = mLocationPoint12[0];
    arrayOfFloat3[1] = (f1 + (mPos1[0] - mShell1Width / 2));
    ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(localImageView3, "translationX", arrayOfFloat3);
    ImageView localImageView4 = mSurface1;
    float[] arrayOfFloat4 = new float[2];
    arrayOfFloat4[0] = mLocationPoint12[1];
    arrayOfFloat4[1] = (f1 + (mPos1[1] - mShell1Height));
    ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(localImageView4, "translationY", arrayOfFloat4);
    localObjectAnimator1.setStartDelay(100L);
    localObjectAnimator2.setStartDelay(100L);
    localObjectAnimator3.setStartDelay(100L);
    localObjectAnimator4.setStartDelay(100L);
    localObjectAnimator1.addListener(new AnimatorListener() {
		
		@Override
		public void onAnimationStart(Animator arg0) {
			mShell1.setVisibility(0);
		    mSurface1.setVisibility(0);
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
    ImageView localImageView5 = mShell2;
    float[] arrayOfFloat5 = new float[2];
    arrayOfFloat5[0] = (mLocationPoint21[0] - mShell2Width);
    arrayOfFloat5[1] = (mPos2[0] - mShell2Width / 2);
    ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(localImageView5, "translationX", arrayOfFloat5);
    ImageView localImageView6 = mShell2;
    float[] arrayOfFloat6 = new float[2];
    arrayOfFloat6[0] = (mLocationPoint21[1] - mShell2Height);
    arrayOfFloat6[1] = (mPos2[1] - mShell2Height);
    ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(localImageView6, "translationY", arrayOfFloat6);
    ImageView localImageView7 = mSurface2;
    float[] arrayOfFloat7 = new float[2];
    arrayOfFloat7[0] = mLocationPoint22[0];
    arrayOfFloat7[1] = (f2 + (mPos2[0] - mShell2Width / 2));
    ObjectAnimator localObjectAnimator7 = ObjectAnimator.ofFloat(localImageView7, "translationX", arrayOfFloat7);
    ImageView localImageView8 = mSurface2;
    float[] arrayOfFloat8 = new float[2];
    arrayOfFloat8[0] = mLocationPoint22[1];
    arrayOfFloat8[1] = (f2 + (mPos2[1] - mShell2Height));
    ObjectAnimator localObjectAnimator8 = ObjectAnimator.ofFloat(localImageView8, "translationY", arrayOfFloat8);
    localObjectAnimator5.setStartDelay(200L);
    localObjectAnimator6.setStartDelay(200L);
    localObjectAnimator7.setStartDelay(200L);
    localObjectAnimator8.setStartDelay(200L);
    localObjectAnimator5.addListener(new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator arg0) {
			mShell2.setVisibility(0);
		    mSurface2.setVisibility(0);
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
    ImageView localImageView9 = mShell3;
    float[] arrayOfFloat9 = new float[2];
    arrayOfFloat9[0] = (mLocationPoint31[0] - mShell3Width);
    arrayOfFloat9[1] = (mPos3[0] - mShell3Width / 2);
    ObjectAnimator localObjectAnimator9 = ObjectAnimator.ofFloat(localImageView9, "translationX", arrayOfFloat9);
    ImageView localImageView10 = mShell3;
    float[] arrayOfFloat10 = new float[2];
    arrayOfFloat10[0] = (mLocationPoint31[1] - mShell3Height);
    arrayOfFloat10[1] = (mPos3[1] - mShell3Height);
    ObjectAnimator localObjectAnimator10 = ObjectAnimator.ofFloat(localImageView10, "translationY", arrayOfFloat10);
    ImageView localImageView11 = mSurface3;
    float[] arrayOfFloat11 = new float[2];
    arrayOfFloat11[0] = mLocationPoint32[0];
    arrayOfFloat11[1] = (f3 + (mPos3[0] - mShell3Width / 2));
    ObjectAnimator localObjectAnimator11 = ObjectAnimator.ofFloat(localImageView11, "translationX", arrayOfFloat11);
    ImageView localImageView12 = mSurface3;
    float[] arrayOfFloat12 = new float[2];
    arrayOfFloat12[0] = mLocationPoint32[1];
    arrayOfFloat12[1] = (f3 + (mPos3[1] - mShell3Height));
    ObjectAnimator localObjectAnimator12 = ObjectAnimator.ofFloat(localImageView12, "translationY", arrayOfFloat12);
    localObjectAnimator9.setStartDelay(100L);
    localObjectAnimator10.setStartDelay(100L);
    localObjectAnimator11.setStartDelay(100L);
    localObjectAnimator12.setStartDelay(100L);
    localObjectAnimator9.addListener(new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator arg0) {
			mShell3.setVisibility(0);
		    mSurface3.setVisibility(0);
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
    ImageView localImageView13 = mShell4;
    float[] arrayOfFloat13 = new float[2];
    arrayOfFloat13[0] = (mLocationPoint41[0] - mShell4Width);
    arrayOfFloat13[1] = (mPos4[0] - mShell4Width / 2);
    ObjectAnimator localObjectAnimator13 = ObjectAnimator.ofFloat(localImageView13, "translationX", arrayOfFloat13);
    ImageView localImageView14 = mShell4;
    float[] arrayOfFloat14 = new float[2];
    arrayOfFloat14[0] = (mLocationPoint41[1] - mShell4Height);
    arrayOfFloat14[1] = (mPos4[1] - mShell4Height);
    ObjectAnimator localObjectAnimator14 = ObjectAnimator.ofFloat(localImageView14, "translationY", arrayOfFloat14);
    ImageView localImageView15 = mSurface4;
    float[] arrayOfFloat15 = new float[2];
    arrayOfFloat15[0] = mLocationPoint42[0];
    arrayOfFloat15[1] = (f4 + (mPos4[0] - mShell4Width / 2));
    ObjectAnimator localObjectAnimator15 = ObjectAnimator.ofFloat(localImageView15, "translationX", arrayOfFloat15);
    ImageView localImageView16 = mSurface4;
    float[] arrayOfFloat16 = new float[2];
    arrayOfFloat16[0] = mLocationPoint42[1];
    arrayOfFloat16[1] = (f4 + (mPos4[1] - mShell4Height));
    ObjectAnimator localObjectAnimator16 = ObjectAnimator.ofFloat(localImageView16, "translationY", arrayOfFloat16);
    ImageView localImageView17 = mShell5;
    float[] arrayOfFloat17 = new float[2];
    arrayOfFloat17[0] = (mLocationPoint51[0] - mShell5Width);
    arrayOfFloat17[1] = (mPos5[0] - mShell5Width / 2);
    ObjectAnimator localObjectAnimator17 = ObjectAnimator.ofFloat(localImageView17, "translationX", arrayOfFloat17);
    ImageView localImageView18 = mShell5;
    float[] arrayOfFloat18 = new float[2];
    arrayOfFloat18[0] = (mLocationPoint51[1] - mShell5Height);
    arrayOfFloat18[1] = (mPos5[1] - mShell5Height);
    ObjectAnimator localObjectAnimator18 = ObjectAnimator.ofFloat(localImageView18, "translationY", arrayOfFloat18);
    ImageView localImageView19 = mSurface5;
    float[] arrayOfFloat19 = new float[2];
    arrayOfFloat19[0] = mLocationPoint52[0];
    arrayOfFloat19[1] = (f5 + (mPos5[0] - mShell5Width / 2));
    ObjectAnimator localObjectAnimator19 = ObjectAnimator.ofFloat(localImageView19, "translationX", arrayOfFloat19);
    ImageView localImageView20 = mSurface5;
    float[] arrayOfFloat20 = new float[2];
    arrayOfFloat20[0] = mLocationPoint52[1];
    arrayOfFloat20[1] = (f5 + (mPos5[1] - mShell5Height));
    ObjectAnimator localObjectAnimator20 = ObjectAnimator.ofFloat(localImageView20, "translationY", arrayOfFloat20);
    localObjectAnimator17.setStartDelay(100L);
    localObjectAnimator18.setStartDelay(100L);
    localObjectAnimator19.setStartDelay(100L);
    localObjectAnimator20.setStartDelay(100L);
    localObjectAnimator17.addListener(new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator arg0) {
			mShell5.setVisibility(0);
		    mSurface5.setVisibility(0);
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
    AnimatorSet localAnimatorSet1 = mSet;
    Animator[] arrayOfAnimator = new Animator[21];
    arrayOfAnimator[0] = localObjectAnimator13;
    arrayOfAnimator[1] = localObjectAnimator14;
    arrayOfAnimator[2] = localObjectAnimator15;
    arrayOfAnimator[3] = localObjectAnimator16;
    ImageView localImageView21 = mText;
    float[] arrayOfFloat21 = new float[2];
    arrayOfFloat21[0] = 0.0F;
    arrayOfFloat21[1] = 1.0F;
    arrayOfAnimator[4] = ObjectAnimator.ofFloat(localImageView21, "alpha", arrayOfFloat21);
    arrayOfAnimator[5] = localObjectAnimator9;
    arrayOfAnimator[6] = localObjectAnimator10;
    arrayOfAnimator[7] = localObjectAnimator11;
    arrayOfAnimator[8] = localObjectAnimator12;
    arrayOfAnimator[9] = localObjectAnimator17;
    arrayOfAnimator[10] = localObjectAnimator18;
    arrayOfAnimator[11] = localObjectAnimator19;
    arrayOfAnimator[12] = localObjectAnimator20;
    arrayOfAnimator[13] = localObjectAnimator5;
    arrayOfAnimator[14] = localObjectAnimator6;
    arrayOfAnimator[15] = localObjectAnimator7;
    arrayOfAnimator[16] = localObjectAnimator8;
    arrayOfAnimator[17] = localObjectAnimator1;
    arrayOfAnimator[18] = localObjectAnimator2;
    arrayOfAnimator[19] = localObjectAnimator3;
    arrayOfAnimator[20] = localObjectAnimator4;
    localAnimatorSet1.playTogether(arrayOfAnimator);
    mSet.setInterpolator(new AccelerateInterpolator());
    mSet.setDuration(800L).start();
    AnimatorSet localAnimatorSet2 = mSet;
    localAnimatorSet2.addListener(new AnimatorListener() {
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
					startNextAnimaton();
				}
			}, 400L);
		}
		@Override
		public void onAnimationCancel(Animator arg0) {
		}
	});
  }

  private void startNextAnimaton()
  {
    AnimatorSet localAnimatorSet = new AnimatorSet();
    ImageView localImageView1 = mShell1;
    float[] arrayOfFloat1 = new float[2];
    arrayOfFloat1[0] = (mPos1[0] - mShell1Width / 2);
    arrayOfFloat1[1] = (-mLocationPoint11[0]);
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(localImageView1, "translationX", arrayOfFloat1);
    ImageView localImageView2 = mShell1;
    float[] arrayOfFloat2 = new float[2];
    arrayOfFloat2[0] = (mPos1[1] - mShell1Height);
    arrayOfFloat2[1] = (-mLocationPoint11[1] - mShell1Height);
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localImageView2, "translationY", arrayOfFloat2);
    ImageView localImageView3 = mSurface1;
    float[] arrayOfFloat3 = new float[2];
    arrayOfFloat3[0] = (10 + (mPos1[0] - mShell1Width / 2));
    arrayOfFloat3[1] = (10 + -mLocationPoint11[0]);
    ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(localImageView3, "translationX", arrayOfFloat3);
    ImageView localImageView4 = mSurface1;
    float[] arrayOfFloat4 = new float[2];
    arrayOfFloat4[0] = (10 + (mPos1[1] - mShell1Height));
    arrayOfFloat4[1] = (10 + (-mLocationPoint11[1] - mShell1Height));
    ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(localImageView4, "translationY", arrayOfFloat4);
    ImageView localImageView5 = mShell2;
    float[] arrayOfFloat5 = new float[2];
    arrayOfFloat5[0] = (mPos2[0] - mShell2Width / 2);
    arrayOfFloat5[1] = (-mLocationPoint21[0]);
    ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(localImageView5, "translationX", arrayOfFloat5);
    ImageView localImageView6 = mShell2;
    float[] arrayOfFloat6 = new float[2];
    arrayOfFloat6[0] = (mPos2[1] - mShell2Height);
    arrayOfFloat6[1] = (-mLocationPoint21[1]);
    ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(localImageView6, "translationY", arrayOfFloat6);
    ImageView localImageView7 = mSurface2;
    float[] arrayOfFloat7 = new float[2];
    arrayOfFloat7[0] = (16 + (mPos2[0] - mShell2Width / 2));
    arrayOfFloat7[1] = (16 + -mLocationPoint21[0]);
    ObjectAnimator localObjectAnimator7 = ObjectAnimator.ofFloat(localImageView7, "translationX", arrayOfFloat7);
    ImageView localImageView8 = mSurface2;
    float[] arrayOfFloat8 = new float[2];
    arrayOfFloat8[0] = (16 + (mPos2[1] - mShell2Height));
    arrayOfFloat8[1] = (16 + -mLocationPoint21[1]);
    ObjectAnimator localObjectAnimator8 = ObjectAnimator.ofFloat(localImageView8, "translationY", arrayOfFloat8);
    ImageView localImageView9 = mShell3;
    float[] arrayOfFloat9 = new float[2];
    arrayOfFloat9[0] = (mPos3[0] - mShell3Width / 2);
    arrayOfFloat9[1] = mLocationPoint31[0];
    ObjectAnimator localObjectAnimator9 = ObjectAnimator.ofFloat(localImageView9, "translationX", arrayOfFloat9);
    ImageView localImageView10 = mShell3;
    float[] arrayOfFloat10 = new float[2];
    arrayOfFloat10[0] = (mPos3[1] - mShell3Height);
    arrayOfFloat10[1] = (-4 * mLocationPoint31[1] - mShell3Height);
    ObjectAnimator localObjectAnimator10 = ObjectAnimator.ofFloat(localImageView10, "translationY", arrayOfFloat10);
    ImageView localImageView11 = mSurface3;
    float[] arrayOfFloat11 = new float[2];
    arrayOfFloat11[0] = (18 + (mPos3[0] - mShell3Width / 2));
    arrayOfFloat11[1] = (18 + mLocationPoint31[0]);
    ObjectAnimator localObjectAnimator11 = ObjectAnimator.ofFloat(localImageView11, "translationX", arrayOfFloat11);
    ImageView localImageView12 = mSurface3;
    float[] arrayOfFloat12 = new float[2];
    arrayOfFloat12[0] = (18 + (mPos3[1] - mShell3Height));
    arrayOfFloat12[1] = (18 + (-4 * mLocationPoint31[1] - mShell3Height));
    ObjectAnimator localObjectAnimator12 = ObjectAnimator.ofFloat(localImageView12, "translationY", arrayOfFloat12);
    ImageView localImageView13 = mShell4;
    float[] arrayOfFloat13 = new float[2];
    arrayOfFloat13[0] = (mPos4[0] - mShell4Width / 2);
    arrayOfFloat13[1] = (-mShell4Width);
    ObjectAnimator localObjectAnimator13 = ObjectAnimator.ofFloat(localImageView13, "translationX", arrayOfFloat13);
    ImageView localImageView14 = mShell4;
    float[] arrayOfFloat14 = new float[2];
    arrayOfFloat14[0] = (mPos4[1] - mShell4Height);
    arrayOfFloat14[1] = (mLocationPoint41[1] - 2 * mShell4Height);
    ObjectAnimator localObjectAnimator14 = ObjectAnimator.ofFloat(localImageView14, "translationY", arrayOfFloat14);
    ImageView localImageView15 = mSurface4;
    float[] arrayOfFloat15 = new float[2];
    arrayOfFloat15[0] = (20 + (mPos4[0] - mShell4Width / 2));
    arrayOfFloat15[1] = (20 + -mShell4Width);
    ObjectAnimator localObjectAnimator15 = ObjectAnimator.ofFloat(localImageView15, "translationX", arrayOfFloat15);
    ImageView localImageView16 = mSurface4;
    float[] arrayOfFloat16 = new float[2];
    arrayOfFloat16[0] = (20 + (mPos4[1] - mShell4Height));
    arrayOfFloat16[1] = (20 + (mLocationPoint41[1] - 2 * mShell4Height));
    ObjectAnimator localObjectAnimator16 = ObjectAnimator.ofFloat(localImageView16, "translationY", arrayOfFloat16);
    ImageView localImageView17 = mShell5;
    float[] arrayOfFloat17 = new float[2];
    arrayOfFloat17[0] = (mPos5[0] - mShell5Width / 2);
    arrayOfFloat17[1] = (-mShell5Width);
    ObjectAnimator localObjectAnimator17 = ObjectAnimator.ofFloat(localImageView17, "translationX", arrayOfFloat17);
    ImageView localImageView18 = mShell5;
    float[] arrayOfFloat18 = new float[2];
    arrayOfFloat18[0] = (mPos5[1] - mShell5Height);
    arrayOfFloat18[1] = (mLocationPoint51[1] - 2 * mShell5Height);
    ObjectAnimator localObjectAnimator18 = ObjectAnimator.ofFloat(localImageView18, "translationY", arrayOfFloat18);
    ImageView localImageView19 = mSurface5;
    float[] arrayOfFloat19 = new float[2];
    arrayOfFloat19[0] = (22 + (mPos5[0] - mShell5Width / 2));
    arrayOfFloat19[1] = (22 + -mShell5Width);
    ObjectAnimator localObjectAnimator19 = ObjectAnimator.ofFloat(localImageView19, "translationX", arrayOfFloat19);
    ImageView localImageView20 = mSurface5;
    float[] arrayOfFloat20 = new float[2];
    arrayOfFloat20[0] = (22 + (mPos5[1] - mShell5Height));
    arrayOfFloat20[1] = (22 + (mLocationPoint51[1] - 2 * mShell5Height));
    ObjectAnimator localObjectAnimator20 = ObjectAnimator.ofFloat(localImageView20, "translationY", arrayOfFloat20);
    Animator[] arrayOfAnimator = new Animator[20];
    arrayOfAnimator[0] = localObjectAnimator1;
    arrayOfAnimator[1] = localObjectAnimator2;
    arrayOfAnimator[2] = localObjectAnimator3;
    arrayOfAnimator[3] = localObjectAnimator4;
    arrayOfAnimator[4] = localObjectAnimator5;
    arrayOfAnimator[5] = localObjectAnimator6;
    arrayOfAnimator[6] = localObjectAnimator7;
    arrayOfAnimator[7] = localObjectAnimator8;
    arrayOfAnimator[8] = localObjectAnimator9;
    arrayOfAnimator[9] = localObjectAnimator10;
    arrayOfAnimator[10] = localObjectAnimator11;
    arrayOfAnimator[11] = localObjectAnimator12;
    arrayOfAnimator[12] = localObjectAnimator13;
    arrayOfAnimator[13] = localObjectAnimator14;
    arrayOfAnimator[14] = localObjectAnimator15;
    arrayOfAnimator[15] = localObjectAnimator16;
    arrayOfAnimator[16] = localObjectAnimator17;
    arrayOfAnimator[17] = localObjectAnimator18;
    arrayOfAnimator[18] = localObjectAnimator19;
    arrayOfAnimator[19] = localObjectAnimator20;
    localAnimatorSet.playTogether(arrayOfAnimator);
    localAnimatorSet.setDuration(800L).start();
    AnimatorListener local8 = new AnimatorListener() {
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
	};
    localAnimatorSet.addListener(local8);
  }
  public int px2dip(float paramFloat) {
		return (int) (0.5F + paramFloat
				/ getResources().getDisplayMetrics().density);
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
					 Log.d("Page3", "onAnimationEnd");
					 if (!mIsOverActionCalled){
					    	return;
					 }
					sendOverAction("page3_animation_done");
					
				}
				
				@Override
				public void onAnimationCancel(Animator arg0) {
					mIsNaturalOver = false;
				    Log.d("Page3", "onAnimationCancel");
					
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
    return "Page3";
  }

  protected String getWaitingAction()
  {
    return "page2_animation_done";
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    final View localView = paramLayoutInflater.inflate(R.layout.fragment_gameinshow_page3, null, false);
    setupViews(localView);
    mRootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		
		@Override
		public void onGlobalLayout() {
			mViewPos11.getLocationOnScreen(mLocationPoint11);
			mViewPos12.getLocationOnScreen(mLocationPoint12);
			mViewPos21.getLocationOnScreen(mLocationPoint21);
			mViewPos22.getLocationOnScreen(mLocationPoint22);
			mViewPos31.getLocationOnScreen(mLocationPoint31);
			mViewPos32.getLocationOnScreen(mLocationPoint32);
			mViewPos41.getLocationOnScreen(mLocationPoint41);
			mViewPos42.getLocationOnScreen(mLocationPoint42);
			mViewPos51.getLocationOnScreen(mLocationPoint51);
			mViewPos52.getLocationOnScreen(mLocationPoint52);
			localView.findViewById(R.id.pos1).getLocationOnScreen(mPos1);
			localView.findViewById(R.id.pos2).getLocationOnScreen(mPos2);
			localView.findViewById(R.id.pos3).getLocationOnScreen(mPos3);
			localView.findViewById(R.id.pos4).getLocationOnScreen(mPos4);
			localView.findViewById(R.id.pos5).getLocationOnScreen(mPos5);
		}
	});
    return localView;
  }

  public void onPause()
  {
    super.onPause();
    mCloseAnimationLayout.cancelAnimation();
    if ((mSet == null) || (!mSet.isRunning()))
      return;
    mSet.cancel();
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
    if ((mRootLayout == null) || (paramFloat + mCloseAnimationLayout.getXiaocaiqiHeight() <= 2 * Page1.ScreenHeight))
      return;
    mRootLayout.setVisibility(8);
  }
}
package com.example.testsplashview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;

public class Page2Layout extends FrameLayout
{
  private static final String TAG = "Page0Layout";
  private ObjectAnimator mAnimator;
  private float mScaleNum;
  private final int mScreenHeight;
  private final int mScreenWidth;
  private float mTranslationY;
  private TranslationYLinstener mTranslationYLinstener;
  private Bitmap mXiaocaiqi;
  private Rect mXiaocaiqiDst;
  private Rect mXiaocaiqiSrc;
  private int xiaocaiqiHeight;
  private int xiaocaiqiNewHeight;
  private int xiaocaiqiNewWidth;
  private int xiaocaiqiWidth;

  public Page2Layout(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public Page2Layout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public Page2Layout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setWillNotDraw(false);
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(getResources(), R.drawable.page2_line, localOptions);
    xiaocaiqiHeight = localOptions.outHeight;
    xiaocaiqiWidth = localOptions.outWidth;
    localOptions.inJustDecodeBounds = false;
    mXiaocaiqi = BitmapFactory.decodeResource(getResources(), R.drawable.page2_line, localOptions);
    xiaocaiqiNewHeight = localOptions.outHeight;
    xiaocaiqiNewWidth = localOptions.outWidth;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    mScreenWidth = localDisplayMetrics.widthPixels;
    mScreenHeight = localDisplayMetrics.heightPixels;
    mScaleNum = xiaocaiqiHeight / xiaocaiqiNewHeight;
    mTranslationY = (-xiaocaiqiHeight);
  }

  public void cancelAnimation()
  {
    if ((mAnimator == null) || (!mAnimator.isRunning()))
      return;
    mAnimator.cancel();
    ObjectAnimator.clearAllAnimations();
    mAnimator.removeAllListeners();
  }

  public float getTranslationY()
  {
    return mTranslationY;
  }

  public void hide(int paramInt, Animator.AnimatorListener paramAnimatorListener)
  {
    if ((mAnimator != null) && (mAnimator.isRunning()))
      mAnimator.cancel();
    float[] arrayOfFloat = new float[2];
    arrayOfFloat[0] = (-xiaocaiqiHeight);
    arrayOfFloat[1] = (2 * mScreenHeight);
    mAnimator = ObjectAnimator.ofFloat(this, "translationY", arrayOfFloat);
    mAnimator.setInterpolator(BakedBezierInterpolator.getInstance());
    mAnimator.setDuration(paramInt);
    mAnimator.addListener(paramAnimatorListener);
    mAnimator.addListener(new Animator.AnimatorListener()
    {
      public void onAnimationCancel(Animator paramAnimator)
      {
      }

      public void onAnimationEnd(Animator paramAnimator)
      {
      }

      public void onAnimationRepeat(Animator paramAnimator)
      {
      }

      public void onAnimationStart(Animator paramAnimator)
      {
      }
    });
    mAnimator.start();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    mXiaocaiqiDst = new Rect(0, (int)mTranslationY - mScreenHeight, xiaocaiqiNewWidth, (int)mTranslationY);
    mXiaocaiqiSrc = new Rect(0, (int)(mScreenHeight - mTranslationY), xiaocaiqiNewWidth, mScreenHeight);
    paramCanvas.drawBitmap(mXiaocaiqi, mXiaocaiqiSrc, mXiaocaiqiDst, null);
  }

  @Override
  public void setTranslationY(float paramFloat)
  {
    mTranslationY = paramFloat;
    invalidate();
    if (mTranslationYLinstener == null)
      return;
    mTranslationYLinstener.onTranslationYChange(paramFloat);
  }

  public void setTranslationYLinstener(TranslationYLinstener paramTranslationYLinstener)
  {
    mTranslationYLinstener = paramTranslationYLinstener;
  }

  public static abstract interface TranslationYLinstener
  {
    public abstract void onTranslationYChange(float paramFloat);
  }
}
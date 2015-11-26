package com.example.testsplashview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;

public class RevealLayout extends FrameLayout
{
  private static final int DEFAULT_DURATION = 600;
  private ObjectAnimator mAnimator;
  private int mClipCenterX;
  private int mClipCenterY = 0;
  private Path mClipPath = new Path();
  private float mClipRadius = 0.0F;
  private boolean mIsContentShown = true;

  public RevealLayout(Context paramContext)
  {
    this(paramContext, null);
  }

  public RevealLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public RevealLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
	  super(paramContext, paramAttributeSet, paramInt);
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
          setLayerType(View.LAYER_TYPE_SOFTWARE, null);
      }
  }

  private float getMaxRadius(int paramInt1, int paramInt2)
  {
    int i = Math.max(paramInt1, getWidth() - paramInt1);
    int j = Math.max(paramInt2, getHeight() - paramInt2);
    return (float)Math.sqrt(i * i + j * j);
  }
  @Override
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    if (indexOfChild(paramView) == -1 + getChildCount())
    {
      boolean bool;
      mClipPath.reset();
      mClipPath.addCircle(mClipCenterX, mClipCenterY, mClipRadius, Path.Direction.CW);
      paramCanvas.save();
      paramCanvas.clipPath(mClipPath);
      bool = super.drawChild(paramCanvas, paramView, paramLong);
      paramCanvas.restore();
      return bool;
    }else{
    	return  super.drawChild(paramCanvas, paramView, paramLong);
    }
  }

  public float getClipRadius()
  {
    return mClipRadius;
  }

  public void hide()
  {
    hide(DEFAULT_DURATION, null);
  }

  public void hide(int paramInt1, int paramInt2)
  {
    hide(paramInt1, paramInt2, DEFAULT_DURATION, null);
  }

  public void hide(int paramInt1, int paramInt2, int paramInt3, Animator.AnimatorListener paramAnimatorListener)
  {
    if ((paramInt1 < 0) || (paramInt1 > getWidth()) || (paramInt2 < 0) || (paramInt2 > getHeight()))
      throw new RuntimeException("Center point out of range or call method when View is not initialed yet.");
    if ((paramInt1 != mClipCenterX) || (paramInt2 != mClipCenterY))
    {
      mClipCenterX = paramInt1;
      mClipCenterY = paramInt2;
      mClipRadius = getMaxRadius(paramInt1, paramInt2);
    }
    if ((mAnimator != null) && (mAnimator.isRunning())){
      mAnimator.cancel();
    }
    float[] arrayOfFloat = new float[1];
    arrayOfFloat[0] = 0.0F;
    mAnimator = ObjectAnimator.ofFloat(this, "clipRadius", arrayOfFloat);
    mAnimator.setInterpolator(BakedBezierInterpolator.getInstance());
    mAnimator.setDuration(paramInt3);
    mAnimator.addListener(paramAnimatorListener);
    mAnimator.addListener(new AnimatorListener() {
		@Override
		public void onAnimationEnd(Animator arg0) {
			mIsContentShown = false;
		}

		@Override
		public void onAnimationCancel(Animator arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationRepeat(Animator arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
	});
    mAnimator.start();
  }

  public void hide(int paramInt, Animator.AnimatorListener paramAnimatorListener)
  {
    hide(getWidth() / 2, getHeight() / 2, paramInt, paramAnimatorListener);
  }

  public boolean isContentShown()
  {
    return mIsContentShown;
  }
 @Override
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mClipCenterX = (paramInt1 / 2);
    mClipCenterY = (paramInt2 / 2);
    if (!mIsContentShown);
    if (!mIsContentShown) {
        mClipRadius = 0;
    } else {
        mClipRadius = (float) (Math.sqrt(paramInt1 * paramInt1 + paramInt2 * paramInt2) / 2);
    }
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void resetAnimation()
  {
    if (mAnimator == null)
      return;
    if (mAnimator.isRunning())
    {
      mAnimator.cancel();
      ObjectAnimator.clearAllAnimations();
      mAnimator.removeAllListeners();
    }
    mIsContentShown = true;
    mClipRadius = getMaxRadius(mClipCenterX, mClipCenterY);
  }

  public void setClipRadius(float paramFloat)
  {
    mClipRadius = paramFloat;
    invalidate();
  }

  public void setContentShown(boolean paramBoolean)
  {
    mIsContentShown = paramBoolean;
    if (mIsContentShown) {
        mClipRadius = 0;
    } else {
        mClipRadius = getMaxRadius(mClipCenterX, mClipCenterY);
    }
    invalidate();
  }

  public void show()
  {
    show(600);
  }

  public void show(int paramInt)
  {
    show(getWidth() / 2, getHeight() / 2, paramInt, null);
  }

  public void show(int paramInt1, int paramInt2)
  {
    show(paramInt1, paramInt2, 600, null);
  }

  public void show(int paramInt1, int paramInt2, int paramInt3, Animator.AnimatorListener paramAnimatorListener)
  {
    if ((paramInt1 < 0) || (paramInt1 > getWidth()) || (paramInt2 < 0) || (paramInt2 > getHeight()))
      throw new RuntimeException("Center point out of range or call method when View is not initialed yet.");
    mClipCenterX = paramInt1;
    mClipCenterY = paramInt2;
    float f = getMaxRadius(paramInt1, paramInt2);
    if ((mAnimator != null) && (mAnimator.isRunning()))
      mAnimator.cancel();
    float[] arrayOfFloat = new float[2];
    arrayOfFloat[0] = 0.0F;
    arrayOfFloat[1] = f;
    mAnimator = ObjectAnimator.ofFloat(this, "clipRadius", arrayOfFloat);
    mAnimator.setInterpolator(BakedBezierInterpolator.getInstance());
    mAnimator.setDuration(paramInt3);
    mAnimator.addListener(paramAnimatorListener);
    mAnimator.addListener(new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator arg0) {
		}
		@Override
		public void onAnimationRepeat(Animator arg0) {
		}
		@Override
		public void onAnimationEnd(Animator arg0) {
			mIsContentShown = true;
		}
		@Override
		public void onAnimationCancel(Animator arg0) {
		}
	});
    mAnimator.start();
  }
}
package com.example.testsplashview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Page0 extends BaseInShowPage
{
  public static final String ACTION_OVER = "page0_animation_done";
  private static final String TAG = "Page0";
  private RelativeLayout mRootLayout;
  private void setupViews(View paramView)
  {
    mRootLayout = ((RelativeLayout)paramView.findViewById(R.id.gameinshow_page0_rootview));
  }
  @Override
  protected void doCloseAnimation()
  {
  }
  @Override
  protected void doCurrentViewAniamtion()
  {
    mRootLayout.postDelayed( new Runnable() {
		public void run() {
			sendOverAction("page0_animation_done");
		}
	}, 1500L);
  }
  @Override
  protected String getPageTag()
  {
    return "Page0";
  }
  @Override
  protected String getWaitingAction()
  {
    return "page4_animation_done";
  }
  @Override
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(R.layout.fragment_gameinshow_page0, null, false);
    setupViews(localView);
    return localView;
  }
  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
  }
  @Override
  public void onPause()
  {
    super.onPause();
  }
  @Override
  public void onResume()
  {
    super.onResume();
  }
}
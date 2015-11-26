package com.example.testsplashview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseInShowPage extends Fragment
{
  public static final String TAG = "BaseInShowPage";
  private BroadcastReceiver mPageOverReceiver = new BroadcastReceiver() {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(getPageTag(), intent.getAction());
		doCurrentViewAniamtion();
	}
};

  protected abstract void doCloseAnimation();

  protected abstract void doCurrentViewAniamtion();

  protected abstract String getPageTag();

  protected abstract String getWaitingAction();
  @Override
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Log.d(getPageTag(), "onCreate");
  }
  @Override
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  @Override
  public void onDestroy()
  {
    super.onDestroy();
    Log.d(getPageTag(), "onDestroy");
  }
  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    Log.d(getPageTag(), "onDestroyView");
  }
  @Override
  public void onPause()
  {
    super.onPause();
    Log.d(getPageTag(), "onPause");
    LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mPageOverReceiver);
  }
  @Override
  public void onResume()
  {
    super.onResume();
    Log.d(getPageTag(), "onResume");
  }
  @Override
  public void onStart()
  {
    super.onStart();
    LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mPageOverReceiver, new IntentFilter(getWaitingAction()));
    Log.d(getPageTag(), "onStart");
  }
  @Override
  public void onStop()
  {
    super.onStop();
    Log.d(getPageTag(), "onStop");
  }
  public void sendOverAction(String paramString)
  {
    LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(paramString));
  }
}
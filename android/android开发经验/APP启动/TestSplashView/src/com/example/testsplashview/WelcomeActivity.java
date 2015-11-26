package com.example.testsplashview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class WelcomeActivity extends FragmentActivity
{
  private static final long EXIT_DURATION_BETWEEN_BACKKEY_PRESSED = 1000L;
  private int mBackKeyPressedCount;
  private Fragment[] mFragments;
  private BroadcastReceiver mPageShowReceiver = new BroadcastReceiver() {
	
	@Override
	public void onReceive(Context context, Intent paramIntent) {
		 	   String str = paramIntent.getAction();
		 	  if("page0_animation_done".equals(str)){
		 		  Intent intent =new Intent();
		 		  intent.setClass(getApplicationContext(), MainActivity.class);
		 		  startActivity(intent);
		 		  finish();
		 	  }
		      if ("page1_animation_done".equals(str))
		        getSupportFragmentManager().beginTransaction().remove(page1).commitAllowingStateLoss();
		      if ("page2_animation_done".equals(str))
		        getSupportFragmentManager().beginTransaction().remove(page2).commitAllowingStateLoss();
		      if ("page3_animation_done".equals(str))
		        getSupportFragmentManager().beginTransaction().remove(page3).commitAllowingStateLoss();
		      if ("page4_animation_done".equals(str))
		        getSupportFragmentManager().beginTransaction().remove(page4).commitAllowingStateLoss();
	}
};
  private BaseInShowPage page0;
  private BaseInShowPage page1;
  private BaseInShowPage page2;
  private BaseInShowPage page3;
  private BaseInShowPage page4;
  @Override
  public void onBackPressed()
  {
    mBackKeyPressedCount = (1 + mBackKeyPressedCount);
    if (mBackKeyPressedCount == 2){
      finish();
    }else{
      new Handler().postDelayed(new Runnable() {
		
		@Override
		public void run() {
			mBackKeyPressedCount = 0;
		}
      }, EXIT_DURATION_BETWEEN_BACKKEY_PRESSED);
    }
  }
  @Override
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.welcome_fragment);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("page0_animation_done");
    localIntentFilter.addAction("page1_animation_done");
    localIntentFilter.addAction("page2_animation_done");
    localIntentFilter.addAction("page3_animation_done");
    localIntentFilter.addAction("page4_animation_done");
    LocalBroadcastManager.getInstance(this).registerReceiver(mPageShowReceiver, localIntentFilter);
    if (paramBundle != null)
      return;
    page4 = new Page4();
    page3 = new Page3();
    page2 = new Page2();
    page1 = new Page1();
    page0 = new Page0();
    Fragment[] arrayOfFragment = new Fragment[5];
    arrayOfFragment[0] = page0;
    arrayOfFragment[1] = page4;
    arrayOfFragment[2] = page3;
    arrayOfFragment[3] = page2;
    arrayOfFragment[4] = page1;
    mFragments = arrayOfFragment;
    for (int i = 0; i < mFragments.length; i++)
      getSupportFragmentManager().beginTransaction().add(R.id.fragmentview, mFragments[i]).addToBackStack(null).commit();
  }
  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    LocalBroadcastManager.getInstance(this).unregisterReceiver(mPageShowReceiver);
  }
}
package hxl.smzdm.activity;

import hxl.smzdm.R;
import hxl.smzdm.fragment.fm_left;
import hxl.smzdm.fragment.fm_right;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;



public class BaseActivity extends SlidingFragmentActivity {

	protected Fragment mFrag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initSlideMenu();
	}

	private void initSlideMenu() {

		SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT_RIGHT);

		setBehindContentView(R.layout.left_menu_frame);
		sm.setSlidingEnabled(true);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadowright);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.left_menu_frame, new fm_left()).commit();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_cocoa);
		sm.setBehindScrollScale(0);
		sm.setFadeDegree(0.25f);

		sm.setSecondaryMenu(R.layout.right_menu_frame);
		sm.setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.right_menu_frame, new fm_right()).commit();
	} 

}

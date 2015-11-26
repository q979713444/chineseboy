package hxl.smzdm.activity;

import hxl.smzdm.R;
import hxl.smzdm.fragment.MainIndexFM;
import hxl.smzdm.fragment.MainIndexFM.OnOpenSMLinstener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.RadioGroup;

public class MainActivity extends BaseActivity implements
		RadioGroup.OnCheckedChangeListener  , OnOpenSMLinstener {

	
	private RadioGroup main_radiogroup;
	private FragmentManager fragmentManageer;
	private FragmentTransaction fragmentTransaction;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//可以用actionbar来滑动
		setSlidingActionBarEnabled(true);
		setContentView(R.layout.activity_main);
		setTitle("宝贝日记");
		//隐藏actionbar
		getActionBar().hide();
		main_radiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		main_radiogroup.setOnCheckedChangeListener(this);
		
		beginTransaction(new MainIndexFM(), 0);
	}
	
	/**
	 * @param fragment 要切换的fragment
	 * @param addOrReplace 当为了0 时候是添加 1为替换
	 */
	private void beginTransaction(Fragment fragment, int addOrReplace) {
		if (null == fragmentManageer) {
			fragmentManageer = getSupportFragmentManager();
		}
		fragmentTransaction = fragmentManageer.beginTransaction();
		if (addOrReplace == 0) {
			fragmentTransaction.add(R.id.main_fragemnet_container, fragment);
		} else if (addOrReplace == 1) {
			fragmentTransaction.replace(R.id.main_fragemnet_container, fragment);
		}

		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.index:
			beginTransaction(new MainIndexFM(), 1);
			break;
		case R.id.group:
			beginTransaction(new MainIndexFM(), 1);
			break;
		case R.id.store:
			beginTransaction(new MainIndexFM(), 1);
			break;
		case R.id.cart:
			beginTransaction(new MainIndexFM(), 1);
			break;
		case R.id.more:
			beginTransaction(new MainIndexFM(), 1);
			break;

		default:
			break;
		}
	}
	
	@Override
	public void openSM() {
		toggle();
	}
	
	public void openSM1(){
		showSecondaryMenu();
	}

}

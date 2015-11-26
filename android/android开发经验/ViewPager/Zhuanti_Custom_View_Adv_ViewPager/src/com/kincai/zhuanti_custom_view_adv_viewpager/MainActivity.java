package com.kincai.zhuanti_custom_view_adv_viewpager;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 
 * @company KCS互联网有限公司
 * 
 * @copyright KCS互联网有限公司版权所有 (c) 2014-2015
 *
 * @author kincai
 * 
 * @description 广告轮播
 *
 * @project Zhuanti_Custom_View_Adv_ViewPager
 *
 * @package com.kincai.zhuanti_custom_view_adv_viewpager
 *
 * @time 2015-7-25 上午10:45:18
 *
 */
@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	private ViewPager mViewPager;
	private TextView mIntroTv;
	private LinearLayout mDotLayout;
	private ViewPagerAdapter mViewPagerAdapter;
	/**
	 * handler处理定时任务
	 */
	private Handler mMyHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
			mMyHandler.sendEmptyMessageDelayed(0, 3000);
		}
	};
	
	private ArrayList<AdvInfo> list = new ArrayList<AdvInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setLinstener();
		initData();
	}


	/**
	 * 初始化控价
	 */
	private void initView() {
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mIntroTv = (TextView) findViewById(R.id.tv_intro);
		mDotLayout = (LinearLayout) findViewById(R.id.dot_layout);
	}
	
	/**
	 * 设置事件监听
	 */
	private void setLinstener() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				Log.e("Activity", "position: "+position);
				updateIntroAndDot();
			}
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}
	
	/**
	 * 初始化数据
	 */
	private void initData() {
		list.add(new AdvInfo(R.drawable.a, "巩俐不低俗，我就不能低俗"));
		list.add(new AdvInfo(R.drawable.b, "朴树又回来了，再唱经典老歌引百万人同唱啊"));
		list.add(new AdvInfo(R.drawable.c, "揭秘北京电影如何升级"));
		list.add(new AdvInfo(R.drawable.d, "乐视网TV版大放送"));
		list.add(new AdvInfo(R.drawable.e, "热血屌丝的反杀"));
		
		initDots();
		mViewPagerAdapter = new ViewPagerAdapter(list, MainActivity.this);
		mViewPager.setAdapter(mViewPagerAdapter);
		
		//默认在1亿多
		mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2 )% list.size()));
		//3秒定时
		mMyHandler.sendEmptyMessageDelayed(0, 3000);
		updateIntroAndDot();
	}
	
	/**
	 * 初始化dot
	 */
	private void initDots(){
		for (int i = 0; i < list.size(); i++) {
			View view = new View(this);
			LayoutParams params = new LayoutParams(8, 8);
			if(i!=0){//第一个点不需要左边距
				params.leftMargin = 5;
			}
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.selector_dot);
			mDotLayout.addView(view);
		}
	}

	/**
	 * 更新文本
	 */
	private void updateIntroAndDot(){
		int currentPage = mViewPager.getCurrentItem()%list.size();
		mIntroTv.setText(list.get(currentPage).getIntro());
		
		for (int i = 0; i < mDotLayout.getChildCount(); i++) {
			mDotLayout.getChildAt(i).setEnabled(i==currentPage);//设置setEnabled为true的话 在选择器里面就会对应的使用白色颜色
		}
	}
	

}

package com.example.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * @作者：Administrator
 * @时间：2014-3-20 下午4:35:04
 * @描述：手机充值
 */
public class ActivityMobile extends Activity implements
		OnCheckedChangeListener, OnClickListener {
	int screenWidth = 0;
	ImageView iv;
	RadioGroup rg;
	int startX = 0;
	int toX = 0;
	String[] moneys = { "10 元", "20 元", "30 元", "50 元", "100 元" };
	AlertDialog dialog;
	TextView tv_money;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mobile);
		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		iv = (ImageView) findViewById(R.id.iv_flow);
		rg = (RadioGroup) findViewById(R.id.rg);
		rg.setOnCheckedChangeListener(this);
		((RadioButton) rg.getChildAt(0)).setChecked(true);
		findViewById(R.id.select).setOnClickListener(this);
		tv_money = (TextView) findViewById(R.id.tv_money);
		createDialog();
	}

	void createDialog() {
		dialog = new AlertDialog.Builder(this)
				.setTitle("请选择面值")
				.setSingleChoiceItems(moneys, 0,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								tv_money.setText(moneys[which]);
								dialog.dismiss();
							}
						}).create();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		setLeftMargin(0);
	}

	/** 设置左边距 */
	void setLeftMargin(int i) {
		FrameLayout.LayoutParams params = (LayoutParams) iv.getLayoutParams();
		params.leftMargin = (int) (screenWidth * (1 / 6.0) * (2 * i + 1) - iv
				.getMeasuredWidth() / 2.0);
		iv.setLayoutParams(params);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if (group.getChildAt(0).getId() == checkedId) {
			// setLeftMargin(0);
			moveBg(0, iv);
		} else if (group.getChildAt(1).getId() == checkedId) {
			// setLeftMargin(1);
			moveBg(1, iv);
		} else {
			// setLeftMargin(2);
			moveBg(2, iv);
		}
		startX = toX;
	}

	/** 平移背景动画 */
	private void moveBg(int i, View v) {
		toX = (int) (screenWidth * (1 / 6.0) * (2 * i) + 1);
		TranslateAnimation anim = new TranslateAnimation(startX, toX, 0, 0);
		anim.setDuration(100);
		anim.setFillAfter(true);
		v.startAnimation(anim);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		dialog.show();
	}
}

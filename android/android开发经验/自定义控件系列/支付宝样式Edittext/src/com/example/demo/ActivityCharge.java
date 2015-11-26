package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.newgame.sdk.view.KeyBoardEditText;
import com.newgame.sdk.view.KeyBoardEditText.OnFinishComposingListener;

/**
 * @作者：Administrator
 * @时间：2014-3-20 下午3:43:05
 * @描述：充值界面
 */
public class ActivityCharge extends Activity implements OnClickListener {
	KeyBoardEditText et_money;
	ImageView iv_edit;
	ImageView iv_back;
	protected float density;

	/** 监听键盘关闭 */
	private OnFinishComposingListener onFinishComposingListener = new OnFinishComposingListener() {

		@Override
		public void finishComposing() {
			// TODO Auto-generated method stub
			// 失去焦点
			et_money.setFocusable(false);
			iv_edit.setVisibility(View.VISIBLE);
			et_money.setBackgroundDrawable(null);// 设置输入状态背景
			// 设置布局宽度
			et_money.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge);
		// 获取屏幕密度（方法2）
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
		et_money = (KeyBoardEditText) findViewById(R.id.tv_charge_id);
		iv_edit = (ImageView) findViewById(R.id.iv_charge_edit_id);
		iv_back = (ImageView) findViewById(R.id.btn_back);
		iv_back.setOnClickListener(this);
		iv_edit.setOnClickListener(this);
		et_money.setOnFinishComposingListener(onFinishComposingListener);
	}

	// 点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == iv_back) {// 返回图标事件
			finish();
		} else if (v == iv_edit) {// 编辑金额
			// 移动光标到最后
			et_money.setSelection(et_money.getText().toString().length());
			// et_money.setFocusable(true);//这行去掉也可以
			et_money.setFocusableInTouchMode(true);// 设置可以获取焦点
			et_money.requestFocus();
			et_money.setBackgroundResource(R.drawable.input_box_);// 设置输入状态背景
			et_money.setLayoutParams(new LinearLayout.LayoutParams(// 设备布局宽度
					(int) (100 * density), (int) (30 * density)));
			iv_edit.setVisibility(View.INVISIBLE);
			// 强制显示软键盘
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(et_money, InputMethodManager.SHOW_FORCED);
		}
	}

	// 充值事件
	public void item_click(View v) {
		int tag = Integer.parseInt(v.getTag().toString());
		switch (tag) {
		case 1:// 支付宝

			break;

		case 2:// 财付通

			break;
		case 3:// 充值卡
			startActivity(new Intent(this, ActivityCardCharge.class));
			break;
		}
	}
}

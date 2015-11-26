package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.newgame.sdk.view.FlowRadioGroup;

/**
 * @作者：Administrator
 * @时间：2014-4-1 下午2:50:02
 * @描述：手机充值卡
 */
public class ActivityCardCharge extends Activity implements
		OnCheckedChangeListener, OnClickListener {
	ImageView iv_back;
	RadioGroup rg_select;
	FlowRadioGroup rg;
	RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
	TextView tv_mes2;
	String mes2 = "2.&#160;如果使用的充值卡<font color='#FC000B'>面值不是%s</font>,&#160;将导致充值失败,并且卡内余额丢失.";
	String mes1 = "2. 所选面值必须与充值卡实际面值保持一致, 选择错误将导致资金损失.";
	Button btn_next;
	/** 0为移动，1为联通，2为电信 */
	int type = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_charge);
		rg = (FlowRadioGroup) findViewById(R.id.rg);
		rg_select = (RadioGroup) findViewById(R.id.select);
		tv_mes2 = (TextView) findViewById(R.id.tv_verify_code);
		rb1 = (RadioButton) findViewById(R.id.iv_charge_tv_money1);
		rb2 = (RadioButton) findViewById(R.id.iv_charge_tv_money2);
		rb3 = (RadioButton) findViewById(R.id.iv_charge_tv_money3);
		rb4 = (RadioButton) findViewById(R.id.iv_charge_tv_money4);
		rb5 = (RadioButton) findViewById(R.id.iv_charge_tv_money5);
		btn_next = (Button) findViewById(R.id.btn_login);
		btn_next.setOnClickListener(this);

		iv_back = (ImageView) findViewById(R.id.btn_back);
		iv_back.setOnClickListener(this);
		rg_select.setOnCheckedChangeListener(this);
		((RadioButton) rg_select.getChildAt(0)).setChecked(true);

		rg.setOnCheckedChangeListener(onCheckedListener);
		// 初始化提示信息
		tv_mes2.setText(mes1);
	}

	/** 金额切换 */
	private FlowRadioGroup.OnCheckedChangeListener onCheckedListener = new FlowRadioGroup.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(FlowRadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			for (int i = 0; i < group.getRadioButtonCount(); i++) {
				RadioButton rb = group.getRadioButton(i);
				if (checkedId == rb.getId()) {
					tv_mes2.setText(Html.fromHtml(String.format(mes2, rb
							.getText().toString())));
					break;
				}
			}
		}
	};

	// 运营商切换
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		rg.clearCheck();// 清除选中
		tv_mes2.setText(mes1);
		// TODO Auto-generated method stub
		if (checkedId == group.getChildAt(0).getId()) {// 移动
			this.type = 0;
			rb1.setText("10元");
			rb2.setText("20元");
			rb3.setText("30元");
			rb4.setText("50元");
			rb5.setText("100元");
			rb5.setVisibility(View.VISIBLE);
		} else if (checkedId == group.getChildAt(2).getId()) {// 联通
			this.type = 1;
			rb1.setText("30元");
			rb2.setText("30元");
			rb3.setText("50元");
			rb4.setText("100元");
			rb5.setVisibility(View.INVISIBLE);
		} else if (checkedId == group.getChildAt(4).getId()) {// 电信
			this.type = 2;
			rb1.setText("10元");
			rb2.setText("20元");
			rb3.setText("30元");
			rb4.setText("50元");
			rb5.setText("100元");
			rb5.setVisibility(View.VISIBLE);
		}
	}

	// 点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == iv_back) {
			finish();
		} else if (v == btn_next) {// 下一步
			if (rg.getCheckedRadioButtonId() == -1) {// 判断是否选取金额
				Toast.makeText(this, "请选择一个额度", 0).show();
				return;
			}
			Intent intent = new Intent(this, ActivityCardChargeFill.class);
			intent.putExtra("type", type);// 传递运营商参数
			startActivity(intent);
		}
	}
}

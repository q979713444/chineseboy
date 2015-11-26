package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.newgame.sdk.utils.AppUtil;

/**
 * @作者：Administrator
 * @时间：2014-4-2 上午10:28:15
 * @描述：填写卡号立即充值
 */
public class ActivityCardChargeFill extends Activity implements OnClickListener {
	ImageView iv_back;
	Button btn_next;
	/** 0为移动，1为联通，2为电信 */
	int type = 0;

	EditText et_code, et_pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_fill);
		iv_back = (ImageView) findViewById(R.id.btn_back);
		iv_back.setOnClickListener(this);
		btn_next = (Button) findViewById(R.id.btn_login);
		btn_next.setOnClickListener(this);
		// 获取上个界面传递的type
		type = getIntent().getIntExtra("type", 0);
		et_code = (EditText) findViewById(R.id.et_code);
		et_pass = (EditText) findViewById(R.id.et_pass);
	}

	// 点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == iv_back) {
			finish();
		} else if (v == btn_next) {// 立即充值
			// 校验输入
			boolean verify = verify();
			if (verify) {// TODO 通过

			}
		}
	}

	// 校验是否通过
	boolean verify() {
		boolean b = true;
		String code = et_code.getText().toString().replace(" ", "");
		String pass = et_pass.getText().toString().replace(" ", "");
		switch (type) {
		case 0:// 移动 卡号至少10位，密码至少8位
			if (!AppUtil.isNumberMore(code, 10)) {
				b = false;
				AppUtil.showDialog(this, "卡号至少10位");
				break;
			}
			if (!AppUtil.isNumberMore(pass, 8)) {
				b = false;
				AppUtil.showDialog(this, "密码至少8位");
				break;
			}
			break;
		case 1:// 联通 卡号15位，密码19位
			if (!AppUtil.isNumber(code, 15)) {
				b = false;
				AppUtil.showDialog(this, "卡号必须是15位");
				break;
			}
			if (!AppUtil.isNumber(pass, 19)) {
				b = false;
				AppUtil.showDialog(this, "密码必须是19位");
				break;
			}
			break;
		case 2:// 电信 卡号19，密码18
			if (!AppUtil.isNumber(code, 19)) {
				b = false;
				AppUtil.showDialog(this, "卡号必须是19位");
				break;
			}
			if (!AppUtil.isNumber(pass, 18)) {
				b = false;
				AppUtil.showDialog(this, "密码必须为18位");
				break;
			}
			break;
		}
		return b;
	}
}

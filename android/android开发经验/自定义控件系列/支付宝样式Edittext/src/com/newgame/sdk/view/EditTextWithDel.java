package com.newgame.sdk.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.demo.R;

/**
 * @作者：Administrator
 * @时间：2014-3-24 上午10:53:24
 * @描述：自定义带删除图标的edittext
 */

public class EditTextWithDel extends EditText {
	private final static String TAG = "EditTextWithDel";
	private Drawable imgAble;
	private Context mContext;
	private Drawable leftDrawable;

	// 默认构造函数
	public EditTextWithDel(Context context) {
		super(context);
		mContext = context;
		init();
	}

	// 默认构造函数
	public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	// 默认构造函数
	public EditTextWithDel(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	// 初始化
	private void init() {
		imgAble = mContext.getResources().getDrawable(R.drawable.icon_delete);
		leftDrawable = getCompoundDrawables()[0];
		// 添加监听
		addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				setDrawable();
			}
		});
		setDrawable();
	}

	// 设置删除图片
	private void setDrawable() {
		if (length() < 1)
			setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null,
					null);
		else
			setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null,
					imgAble, null);
	}

	// 处理删除事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
			int eventX = (int) event.getX();
			if (this.getRight() - eventX < 50) {
				setText("");
			}
		}
		return super.onTouchEvent(event);
	}
}

package com.newgame.sdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * @作者：Administrator
 * @时间：2014-4-1 下午1:50:48
 * @描述：可以监听软键盘关闭的文本框
 */
public class KeyBoardEditText extends EditText {

	public KeyBoardEditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public KeyBoardEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public KeyBoardEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private OnFinishComposingListener mFinishComposingListener;

	/** 设置监听 */
	public void setOnFinishComposingListener(OnFinishComposingListener listener) {
		this.mFinishComposingListener = listener;
	}

	// 重写该方法传入监听
	@Override
	public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
		return new MyInputConnection(super.onCreateInputConnection(outAttrs),
				false);
	}

	// 监听状态类
	public class MyInputConnection extends InputConnectionWrapper {
		public MyInputConnection(InputConnection target, boolean mutable) {
			super(target, mutable);
		}

		@Override
		public boolean finishComposingText() {
			boolean finishComposing = super.finishComposingText();
			if (mFinishComposingListener != null) {
				mFinishComposingListener.finishComposing();
			}
			return finishComposing;
		}
	}

	// 自定义监听接口
	public interface OnFinishComposingListener {
		public void finishComposing();
	}
}

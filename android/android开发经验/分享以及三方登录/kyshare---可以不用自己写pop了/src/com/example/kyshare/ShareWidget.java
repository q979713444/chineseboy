package com.example.kyshare;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class ShareWidget {
	private Activity activity;
	private PopupWindow popupWindow;
	private List<ShareWrapper> shareWrappers;
	
	public ShareWidget(Activity activity){
		this.activity = activity;
	}
	/**
	 * 添加app
	 */
	public void add(ShareWrapper wrapper){
	    if(shareWrappers == null)
	    	shareWrappers = new ArrayList<ShareWrapper>();
	    shareWrappers.add(wrapper);
	}
	/**
	 * 打开
	 */	
	public void open(){
		if(isShowing())
			return ;
		ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup atLayout = (ViewGroup) decor.getChildAt(0);
		final ViewPager viewPager = new ViewPager(activity);
		viewPager.setWrappers(shareWrappers);
		popupWindow = new PopupWindow(viewPager, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(true);
		popupWindow.setAnimationStyle(R.style.kyshare_anim_style);
		popupWindow.showAtLocation(atLayout, Gravity.BOTTOM, 0, 0);
		
	}
	/**
	 * 关闭
	 */
	public void close(){
		try {
			if(popupWindow == null)
				return ;
			if(popupWindow.isShowing()){
				popupWindow.dismiss();
			}
			popupWindow = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 是否正在打开
	 */
	public boolean isShowing(){
		if(this.popupWindow != null && this.popupWindow.isShowing())
			return true;
		return false;
	}
	
}

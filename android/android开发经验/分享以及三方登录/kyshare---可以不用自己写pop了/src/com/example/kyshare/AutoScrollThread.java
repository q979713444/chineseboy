package com.example.kyshare;

import java.util.List;

import android.os.Handler;

public class AutoScrollThread extends Thread{
	public static int LEFT = 0;
	public static int RIGHT = 1;	
	private OnAutoScrollListener listener;
	private boolean flag = true;
	private int orientation;//方向，
	private int moveSize;//移动距离
	private int changeX ;
	AutoScrollThread(OnAutoScrollListener listener, int orientation, int moveSize){
		this.listener = listener;
		this.orientation = orientation;
		this.moveSize = moveSize;
	}
	public void run(){
		while(flag){				    
		    try {	
				if(!flag){
					break ;
				}
		    	Thread.sleep(5);
				if(!flag){
					break ;
				}
				if(Math.abs(changeX) >= moveSize){
					flag = false;
					break;
				}
				if(this.orientation == LEFT){
					this.changeX += -15;
				}else{
					this.changeX += 15;
				}
				if(changeX > moveSize)
					changeX = moveSize;
				if(listener != null)
					listener.onScroll(changeX);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    if(listener != null)
	    	listener.onEnd();
	}
	/**
	 * 停止滚动
	 */
	public void stopScroll(){
		this.flag = false;
	}
	/**
	 * 是否正在滚动
	 * @author pc
	 *
	 */
	public boolean isScrolling(){
		return this.flag;
	}
	public interface OnAutoScrollListener{
		public void onScroll(int changeX);
		public void onEnd();
	}
}

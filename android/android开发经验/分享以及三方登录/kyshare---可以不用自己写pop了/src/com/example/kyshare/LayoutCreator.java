package com.example.kyshare;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class LayoutCreator {
	private Context context;
    private int screenWidth;//屏幕宽度
    private int screenHeight;//屏幕高度
    private int densityDpi;//像素密度
    public LayoutCreator(Context context){
    	this.context = context;
    	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	screenWidth = wm.getDefaultDisplay().getWidth();
    	screenHeight = wm.getDefaultDisplay().getHeight();
    	DisplayMetrics metric = new DisplayMetrics();
    	wm.getDefaultDisplay().getMetrics(metric);
    	densityDpi = metric.densityDpi;
    }
    /**
     * 获得width
     */
    public int getWidth(){
    	return screenWidth;
    }
    /**
     * 获得height
     */
    public int getHeight(){
    	if(this.screenWidth >= 1400){//1440
    		return 1100;
    	}
    	if(this.screenWidth >= 1000){//1080
    		if(this.densityDpi >=480)
        		return 600;
        	if(this.densityDpi >= 320)
        		return 600;
        	return 600;
    	}
    	if(this.screenWidth >= 700){//720
        	if(this.densityDpi >= 320)
        		return 420;
        	if(this.densityDpi >= 240)
        		return 420;
        	if(this.densityDpi >= 160)
        		return 420;
        	return 420;
    	}
    	if(this.screenWidth >= 500){//540
        	if(this.densityDpi >= 320)
        		return 400;
        	if(this.densityDpi >= 240)
        		return 400;
        	if(this.densityDpi >= 160)
        		return 400;
        	return 400;
    	}
    	if(this.screenWidth >= 400){//480
        	if(this.densityDpi >= 240)
        		return 270;
        	return 270;
    	}
    	return 270;
    }
}

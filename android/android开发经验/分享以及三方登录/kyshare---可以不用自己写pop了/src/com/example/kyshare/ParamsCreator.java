package com.example.kyshare;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ParamsCreator {
	private Context context;
    private int screenWidth;//屏幕宽度
    private int screenHeight;//屏幕高度
    private int densityDpi;//像素密度
    public ParamsCreator(Context context){
    	this.context = context;
    	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	screenWidth = wm.getDefaultDisplay().getWidth();
    	screenHeight = wm.getDefaultDisplay().getHeight();
    	DisplayMetrics metric = new DisplayMetrics();
    	wm.getDefaultDisplay().getMetrics(metric);
    	densityDpi = metric.densityDpi;
    }
    /**
     * 获得icon图片大小
     */
    public int getAppIconSize(){
    	if(this.screenWidth >= 1400){//1440
    		return 100;
    	}
    	if(this.screenWidth >= 1000){//1080
    		if(this.densityDpi >=480)
        		return 70;
        	if(this.densityDpi >= 320)
        		return 70;
        	return 70;
    	}
    	if(this.screenWidth >= 700){//720
        	if(this.densityDpi >= 320)
        		return 55;
        	if(this.densityDpi >= 240)
        		return 55;
        	if(this.densityDpi >= 160)
        		return 55;
        	return 55;
    	}
    	if(this.screenWidth >= 500){//540
        	if(this.densityDpi >= 320)
        		return 50;
        	if(this.densityDpi >= 240)
        		return 50;
        	if(this.densityDpi >= 160)
        		return 50;
        	return 50;
    	}
    	if(this.screenWidth >= 400){//480
        	if(this.densityDpi >= 240)
        		return 30;
        	return 30;
    	}
    	return 30;
    }
    /**
     * 获得headerHeight
     */
    public int getHeaderHeight(){
    	if(this.screenWidth >= 1400){//1440
    		return 120;
    	}
    	if(this.screenWidth >= 1000){//1080
    		if(this.densityDpi >=480)
        		return 80;
        	if(this.densityDpi >= 320)
        		return 80;
        	return 80;
    	}
    	if(this.screenWidth >= 700){//720
        	if(this.densityDpi >= 320)
        		return 60;
        	if(this.densityDpi >= 240)
        		return 60;
        	if(this.densityDpi >= 160)
        		return 60;
        	return 60;
    	}
    	if(this.screenWidth >= 500){//540
        	if(this.densityDpi >= 320)
        		return 50;
        	if(this.densityDpi >= 240)
        		return 50;
        	if(this.densityDpi >= 160)
        		return 50;
        	return 50;
    	}
    	if(this.screenWidth >= 400){//480
        	if(this.densityDpi >= 240)
        		return 40;
        	return 40;
    	}
    	return 40;
    }
    /**
     * 获得bottomHeight
     */
    public int getBottomHeight(){
    	if(this.screenWidth >= 1400){//1440
    		return 35;
    	}
    	if(this.screenWidth >= 1000){//1080
    		if(this.densityDpi >=480)
        		return 30;
        	if(this.densityDpi >= 320)
        		return 30;
        	return 30;
    	}
    	if(this.screenWidth >= 700){//720
        	if(this.densityDpi >= 320)
        		return 40;
        	if(this.densityDpi >= 240)
        		return 40;
        	if(this.densityDpi >= 160)
        		return 40;
        	return 40;
    	}
    	if(this.screenWidth >= 500){//540
        	if(this.densityDpi >= 320)
        		return 40;
        	if(this.densityDpi >= 240)
        		return 40;
        	if(this.densityDpi >= 160)
        		return 40;
        	return 40;
    	}
    	if(this.screenWidth >= 400){//480
        	if(this.densityDpi >= 240)
        		return 20;
        	return 20;
    	}
    	return 20;
    }
    /**
     * 获得app 的logo与name的间距
     */
    public int getSpacingOfIconAndName(){
    	if(this.screenWidth >= 1400){//1440
    		return 15;
    	}
    	if(this.screenWidth >= 1000){//1080
    		if(this.densityDpi >=480)
        		return 10;
        	if(this.densityDpi >= 320)
        		return 10;
        	return 10;
    	}
    	if(this.screenWidth >= 700){//720
        	if(this.densityDpi >= 320)
        		return 6;
        	if(this.densityDpi >= 240)
        		return 6;
        	if(this.densityDpi >= 160)
        		return 6;
        	return 6;
    	}
    	if(this.screenWidth >= 500){//540
        	if(this.densityDpi >= 320)
        		return 5;
        	if(this.densityDpi >= 240)
        		return 5;
        	if(this.densityDpi >= 160)
        		return 5;
        	return 5;
    	}
    	if(this.screenWidth >= 400){//480
        	if(this.densityDpi >= 240)
        		return 4;
        	return 4;
    	}
    	return 4;
    }
    /**
     * 获得app 的name的字体大小
     */
    public int getAppNameTextSize(){
    	if(this.screenWidth >= 1400){//1440
    		return 45;
    	}
    	if(this.screenWidth >= 1000){//1080
    		if(this.densityDpi >=480)
        		return 32;
        	if(this.densityDpi >= 320)
        		return 32;
        	return 32;
    	}
    	if(this.screenWidth >= 700){//720
        	if(this.densityDpi >= 320)
        		return 22;
        	if(this.densityDpi >= 240)
        		return 22;
        	if(this.densityDpi >= 160)
        		return 22;
        	return 22;
    	}
    	if(this.screenWidth >= 500){//540
        	if(this.densityDpi >= 320)
        		return 20;
        	if(this.densityDpi >= 240)
        		return 20;
        	if(this.densityDpi >= 160)
        		return 20;
        	return 20;
    	}
    	if(this.screenWidth >= 400){//480
        	if(this.densityDpi >= 240)
        		return 16;
        	return 16;
    	}
    	return 16;
    }
    /**
     * 获得header字体大小
     */
    public int getHeaderTextSize(){
    	if(this.screenWidth >= 1400){//1440
    		return 50;
    	}
    	if(this.screenWidth >= 1000){//1080
    		if(this.densityDpi >=480)
        		return 35;
        	if(this.densityDpi >= 320)
        		return 35;
        	return 35;
    	}
    	if(this.screenWidth >= 700){//720
        	if(this.densityDpi >= 320)
        		return 25;
        	if(this.densityDpi >= 240)
        		return 25;
        	if(this.densityDpi >= 160)
        		return 25;
        	return 25;
    	}
    	if(this.screenWidth >= 500){//540
        	if(this.densityDpi >= 320)
        		return 23;
        	if(this.densityDpi >= 240)
        		return 23;
        	if(this.densityDpi >= 160)
        		return 23;
        	return 23;
    	}
    	if(this.screenWidth >= 400){//480
        	if(this.densityDpi >= 240)
        		return 18;
        	return 18;
    	}
    	return 18;
    }
    /**
     * 获得header底端横线的高度
     */
    public int getHeaderBottomLineHeight(){
    	if(this.screenWidth >= 1400){//1440
    		return 4;
    	}
    	if(this.screenWidth >= 1000){//1080
    		if(this.densityDpi >=480)
        		return 3;
        	if(this.densityDpi >= 320)
        		return 3;
        	return 3;
    	}
    	if(this.screenWidth >= 700){//720
        	if(this.densityDpi >= 320)
        		return 2;
        	if(this.densityDpi >= 240)
        		return 2;
        	if(this.densityDpi >= 160)
        		return 2;
        	return 2;
    	}
    	if(this.screenWidth >= 500){//540
        	if(this.densityDpi >= 320)
        		return 1;
        	if(this.densityDpi >= 240)
        		return 1;
        	if(this.densityDpi >= 160)
        		return 1;
        	return 1;
    	}
    	if(this.screenWidth >= 400){//480
        	if(this.densityDpi >= 240)
        		return 1;
        	return 1;
    	}
    	return 1;
    }
}

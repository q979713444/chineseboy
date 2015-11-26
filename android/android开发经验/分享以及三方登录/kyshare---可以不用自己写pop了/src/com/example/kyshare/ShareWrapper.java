package com.example.kyshare;

import android.graphics.Bitmap;

public class ShareWrapper {
    private int iconId; //app logo
    private Bitmap bitmap;
	private String name;//app名称
	private OnClickListener onClickListener;

	public ShareWrapper(int iconId, String name, OnClickListener onClickListener){
    	this.iconId = iconId;
    	name = name == null ? "" : name;
    	this.name = name;
    	this.onClickListener = onClickListener;
    }
	

	public OnClickListener getOnClickListener() {
		return onClickListener;
	}
	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public int getIconId() {
		return iconId;
	}
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
}

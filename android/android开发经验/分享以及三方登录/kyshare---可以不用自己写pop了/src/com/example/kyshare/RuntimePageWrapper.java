package com.example.kyshare;

import java.util.List;
/**
 * 运行时，每页的定位信息
 * @author pc
 *
 */
public class RuntimePageWrapper {
	public int left;
	public int top;
	public int height;
	public int width;
	public List<ShareWrapper> shareWrappers;
	public RuntimePageWrapper(int left, int top, int height, int width, List<ShareWrapper> shareWrappers){
		this.left = left;
		this.top = top;
		this.height = height;
		this.width = width;
		this.shareWrappers = shareWrappers;
	}	
}

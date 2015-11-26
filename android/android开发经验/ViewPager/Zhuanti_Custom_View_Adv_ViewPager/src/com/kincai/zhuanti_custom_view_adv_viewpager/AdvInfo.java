package com.kincai.zhuanti_custom_view_adv_viewpager;

/**
 * 
 * @company KCS互联网有限公司
 * 
 * @copyright KCS互联网有限公司版权所有 (c) 2014-2015
 *
 * @author kincai
 * 
 * @description 广告信息类
 *
 * @project Zhuanti_Custom_View_Adv_ViewPager
 *
 * @package com.kincai.zhuanti_custom_view_adv_viewpager
 *
 * @time 2015-7-25 上午10:45:39
 *
 */
public class AdvInfo {
	private int iconResId;
	private String intro;
	
	
	public AdvInfo(int iconResId, String intro) {
		super();
		this.iconResId = iconResId;
		this.intro = intro;
	}
	public int getIconResId() {
		return iconResId;
	}
	public void setIconResId(int iconResId) {
		this.iconResId = iconResId;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
}

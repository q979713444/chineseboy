package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kyshare.OnClickListener;
import com.example.kyshare.R;
import com.example.kyshare.ShareWidget;
import com.example.kyshare.ShareWrapper;


public class MainActivity extends Activity {
    private LinearLayout root;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		root = (LinearLayout)this.findViewById(R.id.root);
	}
	/**
	 * 打开分享对话框分享
	 */
	private  ShareWidget shareWidget;
    public void share(View view){
    	if(this.shareWidget == null){
			shareWidget = new ShareWidget(this);
			addApp();
		}			
		shareWidget.open();//代开分享窗口，选择分享的应用
    }
	/**
	 * 添加应用
	 */
	private void addApp(){
		shareWidget.add(new ShareWrapper(R.drawable.logo_sinaweibo, "微博", new OnClickListener(){
			@Override
			public void onClick() {				
				/**  此处添加你的实际分享代码，如微信分享接口     **/
				Toast toast = Toast.makeText(MainActivity.this, "成功分享", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.BOTTOM, 0, 80);
				toast.show();
				shareWidget.close();
			}			
		}));	
		shareWidget.add(new ShareWrapper(R.drawable.logo_wechat, "微信好友", new OnClickListener(){
			@Override
			public void onClick() {	
			}			
		}));	
		shareWidget.add(new ShareWrapper(R.drawable.logo_weixinpengyouquan, "微信朋友圈", new OnClickListener(){
			@Override
			public void onClick() {	
			}			
		}));	
		shareWidget.add(new ShareWrapper(R.drawable.logo_email, "邮件", new OnClickListener(){
			@Override
			public void onClick() {	
			}			
		}));
		shareWidget.add(new ShareWrapper(R.drawable.logo_shortmessage, "信息", new OnClickListener(){
			@Override
			public void onClick() {	
			}			
		}));
		for(int i = 0; i<36; i++){
			shareWidget.add(new ShareWrapper(R.drawable.logo_email, "Email", new OnClickListener(){
				@Override
				public void onClick() {
				}			
			}));
		}
		
	}

}

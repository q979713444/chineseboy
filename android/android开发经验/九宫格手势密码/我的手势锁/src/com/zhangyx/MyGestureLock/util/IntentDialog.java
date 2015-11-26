package com.zhangyx.MyGestureLock.util;

import com.zhangyx.MyGestureLock.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

@SuppressLint("ShowToast")
public class IntentDialog {

	/**
	 * @category退出对话框
	 */
	public static void showExitDialog(final Activity ctx) {

		new AlertDialog.Builder(ctx)
				.setIcon(R.drawable.exit)
				.setTitle("退出应用")
				.setMessage("您确定要退出当前应用？")
				.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								// 发送广播
								ctx.finish();
								System.exit(0);// 退出整个程序
								android.os.Process
										.killProcess(android.os.Process.myPid());
							}
						})
				.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).show();
	}
	
	
}

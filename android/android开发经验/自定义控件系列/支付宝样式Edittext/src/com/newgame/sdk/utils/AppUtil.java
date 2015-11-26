package com.newgame.sdk.utils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.R;

/**
 * @作者：Administrator
 * @时间：2014-3-21 下午2:19:06
 * @描述：
 */
public class AppUtil {
	/**
	 * 获取当前的应用版本号
	 * 
	 * @return 返回版本号
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			String version = packInfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取资源的id(context.getResources())
	 * 
	 * @param context
	 * @param name
	 *            资源名字
	 * @param resType
	 *            资源类型，比如drawable,string
	 * @param packageName
	 *            包名
	 * @return
	 */
	public static int getId(Context context, String name, String resType,
			String packageName) {
		int id = 0;
		id = context.getResources().getIdentifier(name, resType, packageName);
		return id;
	}

	/**
	 * 通过反射获取资源id
	 * 
	 * @param packageName
	 *            包名
	 * @param className
	 *            资源类名，比如layout
	 * @param name
	 *            资源名
	 * @return
	 */
	public static int getResourseIdByName(String packageName, String className,
			String name) {
		Class r = null;
		int id = 0;
		try {
			r = Class.forName(packageName + ".R");

			Class[] classes = r.getClasses();
			Class desireClass = null;

			for (int i = 0; i < classes.length; i++) {
				if (classes[i].getName().split("\\$")[1].equals(className)) {
					desireClass = classes[i];
					break;
				}
			}

			if (desireClass != null) {
				id = desireClass.getField(name).getInt(desireClass);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 获取当前资源的id(反射)
	 * 
	 * @param cls
	 *            当前资源环境
	 * @param name
	 *            资源名
	 * @return
	 */
	public static int getId(Class cls, String name) {
		try {
			Field field = cls.getField(name);
			int i = field.getInt(cls);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 反射得到组件的id
	 * 
	 * @param packageName
	 *            包名
	 * @param className
	 *            类名(drawable,layout,string,color等)
	 * @param idName
	 *            资源名称
	 * @return
	 */
	public static int getCompentID(String packageName, String className,
			String idName) {
		int id = 0;
		try {
			Class<?> cls = Class.forName(packageName + ".R$" + className);
			id = cls.getField(idName).getInt(cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	/** 显示提示框 */
	public static void showDialog(Context context, String msg, int error_code) {
		// new AlertDialog.Builder(context)
		// .setMessage(msg + "(" + error_code + ")")
		// .setPositiveButton("确定", null).create().show();
		if (error_code != 0) {
			showCustomDialog(context, msg + "(" + error_code + ")");
		} else {
			showCustomDialog(context, msg);
		}

	}

	/** 提示自定义dialog */
	public static void showCustomDialog(Context context, String msg) {
		View cus_layout = LayoutInflater.from(context).inflate(R.layout.alert,
				null);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		final AlertDialog alert = builder.create();
		alert.show();
		alert.getWindow().setContentView(cus_layout);
		TextView text = (TextView) cus_layout.findViewById(R.id.tv_verify_code);
		text.setText(msg + "\n");
		Button btn = (Button) cus_layout.findViewById(R.id.btn_login);
		builder.setView(cus_layout);

		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alert.cancel();
			}
		});

	}

	/** 显示提示框 */
	public static void showDialog(Context context, String msg) {
		// new AlertDialog.Builder(context).setMessage(msg)
		// .setPositiveButton("确定", null).create().show();
		showCustomDialog(context, msg);
	}

	/** 验证8位字母和数字 */
	public static boolean isLetter(String str) {
		Pattern p = Pattern.compile("^[A-Za-z0-9]{8}$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 验证数字
	 * 
	 * @param str
	 * @param number
	 *            位数
	 * @return
	 */
	public static boolean isNumber(String str, int number) {
		String pattern = "^[0-9]{%s}$";
		Pattern p = Pattern.compile(String.format(pattern, number));
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 验证至少几位以上数字
	 * 
	 * @param str
	 *            要验证的字符串
	 * @param number
	 *            位数
	 * @return
	 */
	public static boolean isNumberMore(String str, int number) {
		String pattern = "^[0-9]{%s,}$";
		Pattern p = Pattern.compile(String.format(pattern, number));
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/** 验证是否为手机号 */
	public static boolean isMobile(String str) {
		Pattern p = Pattern.compile("'^1[3458][0-9]{9}$");
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
}

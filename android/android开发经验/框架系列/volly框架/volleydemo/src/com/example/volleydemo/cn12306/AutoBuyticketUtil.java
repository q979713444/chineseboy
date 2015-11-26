/**
 * 
 */
package com.example.volleydemo.cn12306;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.volleydemo.MainActivity;

/**
 * @author jiashuai.xujs@alibaba-inc.com 2014-3-7 上午10:37:00
 * 
 */
public class AutoBuyticketUtil {

	public static volatile String cookies;
	public static String JSESSIONID = "JSESSIONID";
	public static String BIGipServerotn = "BIGipServerotn";
	public static String TAG = "Util";

	private RequestQueue mQueue;

	public AutoBuyticketUtil(RequestQueue mQueue) {
		this.mQueue = mQueue;
	}

	public void getHttpsCookie(final ImageView view) {
		String httpsIndexUrl = "https://kyfw.12306.cn/otn/login/init";
		StringRequest request = new StringRequest(Request.Method.GET,
				httpsIndexUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// response
						//Log.e(TAG, response);
						Log.e(TAG, "cookies:" + cookies);
						getLoginRandCode(view);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// error
						Log.e("Error.Response", error.getMessage());
					}
				}) {

			// 如何解析服务端设置的cookie
			// http://stackoverflow.com/questions/20702178/android-volley-access-http-response-header-fields
			// http://blog.csdn.net/hpb21/article/details/12163371
			@Override
			protected Response<String> parseNetworkResponse(
					NetworkResponse response) {
				Response<String> superResponse = super.parseNetworkResponse(response);
				Map<String, String> responseHeaders = response.headers;
				String rawCookies = responseHeaders.get("Set-Cookie");
				//服务端返回是 set-cookie:JSESSIONID=D90B58454550B4D37C4B66A76BF27B93; Path=/otn BIGipServerotn=2564030730.64545.0000; path=/
				String part1 = substring(rawCookies, "", ";");
				String part2 = substring(rawCookies, "\n", ";");
				//客户端需要的是 cookie:JSESSIONID=D90B58454550B4D37C4B66A76BF27B93; BIGipServerotn=2564030730.64545.0000;
				cookies = part1 + "; " + part2 + ";";
				return superResponse;
			}
		};
		mQueue.add(request);
	}
	public void getLoginRandCode(final ImageView view){
		//获取登陆验证码
		String loginPassCodeUrl = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand";
		// 自定义request
		// http://blog.csdn.net/ttdevs/article/details/17586205
		ByteArrayRequest request = new ByteArrayRequest(
				Request.Method.GET, 
				loginPassCodeUrl, 
				new Listener<byte[]>(){
					@Override
					public void onResponse(byte[] response) {
						Bitmap bm = BitmapFactory.decodeByteArray(response, 0, response.length);
						view.setImageBitmap(bm);
					}
				}, 
				new ErrorListener(){
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("Error.Response", error.getMessage());
					}
				}){
			// 如何上传cookie
			// http://stackoverflow.com/questions/17049473/how-to-set-custom-header-in-volley-request
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if(cookies != null && cookies.length() > 0){
					HashMap<String, String>	headers = new HashMap<String, String>();
					headers.put("Cookie", cookies);
					return headers;
				}
				return super.getHeaders();
			}
		};
		mQueue.add(request);
	}
	
	public void checkRandCode(final String username, final String password, final String randcode){
		String url = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		StringRequest postRequest = new StringRequest(
				Request.Method.POST, 
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// response
						//Log.e("Response", response);
						//{"validateMessagesShowId":"_validatorMessage","status":true,"httpstatus":200,"data":"Y","messages":[],"validateMessages":{}}
						String status = substring(response, "\"status\":", ",");
						String httpstatus = substring(response, "\"httpstatus\":", ",");
						String data = substring(response, "\"data\":\"", "\"");
						Log.e(TAG, "status:"+status+",httpstatus:"+httpstatus+",data:"+data);
						if("true".equals(status) && "200".equals(httpstatus) && "Y".equals(data)){
							Log.e(TAG, "check randcode success");
							login(username, password, randcode);
						}else{
							Log.e(TAG, "check randcode error,"+response);
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// error
						Log.e("Error.Response", error.getMessage());
					}
				}) {
			
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("randCode", randcode);
				params.put("rand", "sjrand");
				return params;
			}
			
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if(cookies != null && cookies.length() > 0){
					HashMap<String, String>	headers = new HashMap<String, String>();
					headers.put("Cookie", cookies);
					return headers;
				}
				return super.getHeaders();
			}
		};
		mQueue.add(postRequest);
		
	}

	public void login(final String username,final String password,final String randcode){
		String loginUrl = "https://kyfw.12306.cn/otn/login/loginAysnSuggest";
		StringRequest postRequest = new StringRequest(
				Request.Method.POST, 
				loginUrl,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// response
						//Log.e("Response", response);
						//{"validateMessagesShowId":"_validatorMessage","status":true,"httpstatus":200,"data":{"loginCheck":"Y"},"messages":[],"validateMessages":{}}
						String status = substring(response, "\"status\":", ",");
						String httpstatus = substring(response, "\"httpstatus\":", ",");
						String loginCheck = substring(response, "\"loginCheck\":\"", "\"");
						Log.e(TAG, "status:"+status+",httpstatus:"+httpstatus+",loginCheck:"+loginCheck);
						if("true".equals(status) && "200".equals(httpstatus) && "Y".equals(loginCheck)){
							Log.e(TAG, "login success");
							showIndex();
						}else{
							Log.e(TAG, "login error,"+response);
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// error
						Log.e("Error.Response", error.getMessage());
					}
				}) {
			
			@Override
			protected Map<String, String> getParams() {
				Map<String,String> params = new HashMap<String, String>();
				params.put("loginUserDTO.user_name",username);
				params.put("userDTO.password",password);
				params.put("randCode",randcode);
				return params;
			}
			
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if(cookies != null && cookies.length() > 0){
					HashMap<String, String>	headers = new HashMap<String, String>();
					headers.put("Cookie", cookies);
					return headers;
				}
				return super.getHeaders();
			}
		};
		mQueue.add(postRequest);
	}
	
	public void showIndex(){
		String url = "https://kyfw.12306.cn/otn/index/init";
		StringRequest getRequest = new StringRequest(
				Request.Method.POST, 
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// response
						//Log.e("Response", response);
						String msg = "";
						if(response.indexOf("退出") > 0){
							int start= response.indexOf("您好，");
							int end = response.indexOf("退出", start);
							String realname = response.substring(start+3,end);
							Matcher m = Pattern.compile("(?i)<span>(.*?)</span>").matcher(realname);
							if(m.find()){
								realname = m.group(1);
							}
							msg = "欢迎你:"+realname;
							Log.e(TAG, msg);
						}else{
							msg = "滚粗！";
							Log.e(TAG, msg);
						}
						MainActivity.sendMsg(msg);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// error
						Log.e("Error.Response", error.getMessage());
					}
				}) {
			
			
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if(cookies != null && cookies.length() > 0){
					HashMap<String, String>	headers = new HashMap<String, String>();
					headers.put("Cookie", cookies);
					return headers;
				}
				return super.getHeaders();
			}
			//设置超时时间
			//http://stackoverflow.com/questions/17094718/android-volley-timeout
			//http://blog.csdn.net/dacainiao007/article/details/12617747,说法不准确，确切的是超时时间增长的因子，连接超时读取超时设置为同一个数
			@Override
			public RetryPolicy getRetryPolicy() {  
				RetryPolicy retryPolicy = new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);  
				return retryPolicy;  
			}
		};

		mQueue.add(getRequest);
	}
	
	public static String substring(String src, String fromString,
			String toString) {
		int fromPos = 0;
		if (fromString != null && fromString.length() > 0) {
			fromPos = src.indexOf(fromString);
			fromPos += fromString.length();
		}
		int toPos = src.indexOf(toString, fromPos);
		return src.substring(fromPos, toPos);
	}
}

package com.example.volleydemo;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.cn12306.AutoBuyticketUtil;
//参考：
//http://blog.csdn.net/xyz_lmn/article/details/12165391
//http://blog.csdn.net/xyz_lmn/article/details/12746581
//http://blog.csdn.net/xyz_lmn/article/details/12177005
//http://www.itsalif.info/content/android-volley-tutorial-http-get-post-put
//概述：http://blog.csdn.net/t12x3456/article/details/9221611
//入门： http://blog.csdn.net/ttdevs/article/details/17566795
//自定义request： http://blog.csdn.net/ttdevs/article/details/17586205
//源码分析：http://blog.csdn.net/ttdevs/article/details/17764351
//发送https请求：http://blog.csdn.net/llwdslal/article/details/18052723
//volley源码：git clone https://android.googlesource.com/platform/frameworks/volley
public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	//这个是本地的ip，localhost指的是手机，而非开发用的电脑
	private static final String host = "10.73.21.175";
	private RequestQueue mQueue;
	private AutoBuyticketUtil util;
	private static MsgHandler handler;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mQueue = Volley.newRequestQueue(getApplicationContext());
		mQueue.start();
		util = new AutoBuyticketUtil(mQueue);

		Button button_get_json = (Button) findViewById(R.id.button_get_json);
		button_get_json.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getJson();
			}
		});

		Button button_post_json = (Button) findViewById(R.id.button_post_json);
		button_post_json.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				postJson();
			}
		});

		Button button_image = (Button) findViewById(R.id.button_image);
		button_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getImage();
			}
		});

		Button button_https = (Button) findViewById(R.id.button_https);
		button_https.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				util.getHttpsCookie((ImageView)findViewById(R.id.button_login_randcode));
			}
		});
		
		final EditText randcode = (EditText)findViewById(R.id.text_login_randcode);
		Button button_login_button = (Button) findViewById(R.id.button_login_button);
		button_login_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText usernameView = (EditText)findViewById(R.id.text_login_username);
				EditText passwordView = (EditText)findViewById(R.id.text_login_password);
				String username = usernameView.getText().toString();
				String password = passwordView.getText().toString();
				util.checkRandCode(username,password,randcode.getText().toString());
			}
		});
		
		handler = new MsgHandler(this);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mQueue.stop();
		mQueue = null;
		util = null;
		handler = null;
	}

	public void getImage() {
		String imageUrl = "http://"+host+":8080/web/image.jsp";
		NetworkImageView view = (NetworkImageView) findViewById(R.id.network_image_view);
		view.setDefaultImageResId(android.R.drawable.ic_menu_rotate); 
		view.setErrorImageResId(android.R.drawable.ic_delete); 
		view.setImageUrl(imageUrl, new ImageLoader(mQueue, new BitmapLruCache(1024 * 4)));
	}

	
	public void postJson() {
		String url = "http://"+host+":8080/web/json.jsp";
		StringRequest postRequest = new StringRequest(
				Request.Method.POST, 
				url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// response
						Log.d("Response", response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// error
						Log.d("Error.Response", error.getMessage());
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", "xjs");
				params.put("password", "123456");
				return params;
			}
		};
		mQueue.add(postRequest);
		
		/*测试这种方式不好用！
		HashMap<String, String> params = new HashMap<String, String>();  
		params.put("username", "xjs");  
		params.put("password", "123456"); 
		  
		JsonObjectRequest postRequest = new JsonObjectRequest(url, new JSONObject(params),  
		       new Response.Listener<JSONObject>() {  
		           @Override  
		           public void onResponse(JSONObject response) {  
		               try {  
		                   Log.e("Response:%n %s", response.toString(4));  
		               } catch (JSONException e) {  
		                   e.printStackTrace();  
		               }  
		           }  
		       }, new Response.ErrorListener() {  
		           @Override  
		           public void onErrorResponse(VolleyError error) {  
		               Log.e("Error: ", error.getMessage());  
		           }  
		       }){
		};  
		mQueue.add(postRequest); 
		*/
	}

	public void getJson() {
		String url = "http://"+host+":8080/web/json.jsp?username=xjs&password=123456";
		mQueue.add(new JsonObjectRequest(Method.GET, url, null,
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.e(TAG, "response : " + response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						String err = error.getMessage();
						Log.e(TAG, "err : " + err);
					}
				}));
	}
	
	public static void sendMsg(String msg){
		if(handler == null){
			return;
		}
		Message message = Message.obtain();
		message.obj = msg;
		handler.sendMessage(message);
	}
	
	 public static class MsgHandler extends Handler {
	        WeakReference<MainActivity> mActivity;

	        MsgHandler(MainActivity activity) {
	            mActivity = new WeakReference<MainActivity>(activity);
	        }
	        @Override
	        public void handleMessage(Message msg) {
	        	Activity activity = mActivity.get();
	        	if(activity == null){
	        		return;
	        	}
	        	String text = (String)msg.obj;
	        	Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
	        	TextView result = (TextView)activity.findViewById(R.id.text_login_result);
	        	result.setText(text);
	        }   
	    };
}

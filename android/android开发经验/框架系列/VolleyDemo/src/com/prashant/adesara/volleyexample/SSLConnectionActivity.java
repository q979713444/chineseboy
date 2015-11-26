package com.prashant.adesara.volleyexample;

import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prashant.adesara.volleysample.ssl.SslHttpStack;

public class SSLConnectionActivity extends Activity {

	private Button mTrigger;
	private RequestQueue mVolleyQueue;
	private ProgressDialog mProgress;
	private final String TAG_REQUEST = "MY_TAG";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.json_object_layout);
		
		actionBarSetup();
		
		// Initialise Volley Request Queue with SSL HttpStack to handle secured connection.
		mVolleyQueue = Volley.newRequestQueue(this,new SslHttpStack(true));

		mTrigger = (Button) findViewById(R.id.send_http);
		mTrigger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showProgress();
				makeSampleHttpsRequest();
			}
		});
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void actionBarSetup() {
	  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	    ActionBar ab = getActionBar();
	    ab.setTitle("SSL Connection");
	  }
	}

	public void onDestroy() {
		super.onDestroy();
	}
	
	/*
	 * Dont forget to cancel the request when the UI is not visible.
	 * @see android.app.Activity#onStop()
	 */
	public void onStop() {
		super.onStop();
		if(mProgress != null)
			mProgress.dismiss();
		// Keep the list of requests dispatched in a List<Request<T>> mRequestList;
		/*
		 for( Request<T> req : mRequestList) {
		 	req.cancel();
		 }
		 */
		//( or )
		//jsonObjRequest.cancel();
		//( or )
		mVolleyQueue.cancelAll(TAG_REQUEST);
	}
	
	private void showProgress() {
		mProgress = ProgressDialog.show(this, "", "Loading...");
	}
	
	private void stopProgress() {
		mProgress.cancel();
	}
	
	private void showToast(String msg) {
		Toast.makeText(SSLConnectionActivity.this, msg, Toast.LENGTH_LONG).show();
	}
	
	/*
	 * The endpoint is for demo.Please use your correct endpoint to test Secured Http request.
	 */
	private void makeSampleHttpsRequest() {
		
		String url = "https://[YOUR END POINT]";
		
		
		JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				stopProgress();
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				stopProgress();
				showToast(error.getMessage());
			}
		});
		jsonObjRequest.setTag(TAG_REQUEST);	
		mVolleyQueue.add(jsonObjRequest);
	}
}

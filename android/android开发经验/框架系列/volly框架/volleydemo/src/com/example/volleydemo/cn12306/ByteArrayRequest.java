/**
 * 
 */
package com.example.volleydemo.cn12306;

import org.apache.http.HttpStatus;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

/**
 * @author jiashuai.xujs@alibaba-inc.com 2014-3-7 下午1:17:13
 *
 */
public class ByteArrayRequest extends Request<byte[]>{

	private final Listener<byte[]> mListener;
	
	public ByteArrayRequest(int method, String url, Listener<byte[]> listener, ErrorListener errlistener) {
		super(method, url, errlistener);
		mListener = listener;
	}

	@Override
	protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
		if(response == null){
			return null;
		}
		if(response.statusCode != HttpStatus.SC_OK){
			return null;
		}
		byte[] bytes = response.data;
		return Response.success(bytes, null);
	}
	
	@Override
	protected void deliverResponse(byte[] response) {
		if(mListener != null){
			mListener.onResponse(response);
		}
	}
}

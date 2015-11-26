package com.example.soapdemo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Androidƽ̨����WebService���ֻ���������ز�ѯ��
 * 
 * @author liuyi
 */
public class MainActivity extends Activity {
	private EditText phoneSecEditText;
	private TextView resultView;
	private Button queryButton;

	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			String result = (String) msg.obj;
			resultView.setText(result);
		};
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		phoneSecEditText = (EditText) findViewById(R.id.phone_sec);
		resultView = (TextView) findViewById(R.id.result_text);
		queryButton = (Button) findViewById(R.id.query_btn);

		queryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// �ֻ����루�Σ�
				final String phoneSec = phoneSecEditText.getText().toString().trim();
				// ���ж��û�������ֻ����루�Σ��Ƿ�Ϸ�
				if ("".equals(phoneSec) || phoneSec.length() < 7) {
					// ����������ʾ
					phoneSecEditText.setError("��������ֻ����루�Σ�����");
					phoneSecEditText.requestFocus();
					// ����ʾ��ѯ�����TextView���
					resultView.setText("");
					return;
				}
				// ��ѯ�ֻ����루�Σ���Ϣ
				new Thread(){
					public void run() {
						String result = getRemoteInfo(phoneSec);
						Message msg = handler.obtainMessage();
						msg.obj = result;
						handler.sendMessage(msg);
					};
				}.start();
			}
		});
	}

	/**
	 * �ֻ��Ŷι����ز�ѯ
	 * 
	 * @param phoneSec �ֻ��Ŷ�
	 */
	public String getRemoteInfo(String phoneSec) {
		// �����ռ�
		String nameSpace = "http://WebXml.com.cn/";
		// ���õķ�������
		String methodName = "getMobileCodeInfo";
		// EndPoint:EndPointͨ���ǽ�WSDL��ַĩβ��"?wsdl"ȥ����ʣ��Ĳ���
		String endPoint = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx"; 
		// SOAP Action:SOAP Actionͨ��Ϊ�����ռ� + ���õķ������ơ�
		String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";

		// ָ��WebService�������ռ�͵��õķ�����
		SoapObject rpc = new SoapObject(nameSpace, methodName);

		// ���������WebService�ӿ���Ҫ�������������mobileCode��userId
		rpc.addProperty("mobileCode", phoneSec);
		rpc.addProperty("userId", "");
		
		// ���ɵ���WebService������SOAP������Ϣ,��ָ��SOAP�İ汾
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

		envelope.bodyOut = rpc;
		// �����Ƿ���õ���dotNet������WebService
		envelope.dotNet = true;
		// �ȼ���envelope.bodyOut = rpc;
		envelope.setOutputSoapObject(rpc);
		//��ӡ���󷽷����������
		Log.d("tag", rpc.toString());
		HttpTransportSE transport = new HttpTransportSE(endPoint);
		try {
			// ����WebService
			transport.call(soapAction, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ȡ���ص�����
		SoapObject object = (SoapObject) envelope.bodyIn;
		// ��ȡ���صĽ��
		String result = object.getProperty(0).toString();
		return result;
		// ��WebService���صĽ����ʾ��TextView��
//		resultView.setText(result);
	}
}
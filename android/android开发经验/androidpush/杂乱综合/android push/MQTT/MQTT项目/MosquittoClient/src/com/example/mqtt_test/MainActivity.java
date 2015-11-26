package com.example.mqtt_test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView resultTv;

	private String host = "tcp://192.168.1.102:1883";
	private String userName = "admin";
	private String passWord = "password";

	private Handler handler;

	private MqttClient client;

	private String myTopic = "test/topic";

//	private MqttConnectOptions options;

	private ScheduledExecutorService scheduler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resultTv = (TextView) findViewById(R.id.result);

		init();

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 1) {
					Toast.makeText(MainActivity.this, (String) msg.obj,
							Toast.LENGTH_SHORT).show();
					resultTv.setText(resultTv.getText().toString() + "\n"
							+ msg.obj);
					System.out.println("-----------------------------");
					NotificationManager notificationManager = (NotificationManager)
							MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);

			        Notification notification = new Notification(R.drawable.ic_launcher,
			                "Black Ice Warning!", System.currentTimeMillis());

			        // Hide the notification after its selected
			        notification.flags |= Notification.FLAG_AUTO_CANCEL;
			        Intent intent = new Intent(MainActivity.this, MainActivity.class);
			        PendingIntent activity = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
			        notification.setLatestEventInfo(MainActivity.this, "Black Ice Warning", "Outdoor temperature is " +
			        		(String)msg.obj, activity);
			        notification.number += 1;
			        notificationManager.notify(0, notification);
				} else if (msg.what == 2) {
					Toast.makeText(MainActivity.this, "���ӳɹ�",
							Toast.LENGTH_SHORT).show();
					try {
						client.subscribe(myTopic, 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (msg.what == 3) {
					Toast.makeText(MainActivity.this, "����ʧ�ܣ�ϵͳ��������",
							Toast.LENGTH_SHORT).show();
				}
			}
		};

		startReconnect();

	}

	private void startReconnect() {
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				if (!client.isConnected()) {
					connect();
				}
			}
		}, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
	}

	private void init() {
		try {
			// hostΪ��������testΪclientid������MQTT�Ŀͻ���ID��һ���Կͻ���Ψһ��ʶ����ʾ��MemoryPersistence����clientid�ı�����ʽ��Ĭ��Ϊ���ڴ汣��
			client = new MqttClient(host, "test", new MemoryPersistence());
			// MQTT����������
//			options = new MqttConnectOptions();
//			// �����Ƿ����session,�����������Ϊfalse��ʾ�������ᱣ���ͻ��˵����Ӽ�¼����������Ϊtrue��ʾÿ�����ӵ������������µ��������
//			options.setCleanSession(true);
//			// �������ӵ��û���
//			options.setUserName(userName);
//			// �������ӵ�����
//			options.setPassword(passWord.toCharArray());
//			// ���ó�ʱʱ�� ��λΪ��
//			options.setConnectionTimeout(10);
//			// ���ûỰ����ʱ�� ��λΪ�� ��������ÿ��1.5*20���ʱ����ͻ��˷��͸���Ϣ�жϿͻ����Ƿ����ߣ������������û�������Ļ���
//			options.setKeepAliveInterval(20);
			// ���ûص�
			client.setCallback(new MqttCallback() {

				@Override
				public void connectionLost(Throwable cause) {
					// ���Ӷ�ʧ��һ�����������������
					System.out.println("connectionLost----------");
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					// publish���ִ�е�����
					System.out.println("deliveryComplete---------"
							+ token.isComplete());
				}

				@Override
				public void messageArrived(String topicName, MqttMessage message)
						throws Exception {
					// subscribe��õ�����Ϣ��ִ�е�������
					System.out.println("messageArrived----------");
					Message msg = new Message();
					msg.what = 1;
					msg.obj = topicName + "---" + message.toString();
					handler.sendMessage(msg);
				}
			});
			// connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void connect() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
//					client.connect(options);
					client.connect();
					Message msg = new Message();
					msg.what = 2;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = 3;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (client != null && keyCode == KeyEvent.KEYCODE_BACK) {
			try {
				client.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			scheduler.shutdown();
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
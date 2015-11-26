package com.example.demo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.demo.PullToRefreshView.OnFooterRefreshListener;
import com.example.demo.PullToRefreshView.OnHeaderRefreshListener;

/***
 * 
 * ScrollView Ƕ��ListView Ƕ��GridView���������ظ��࣬����ˢ�¡�
 * 
 * �߼������������˴���
 * 
 * ����ֻ��ListView����2������Item����һ����item���󣬵ڶ�����һ������
 * 
 * 
 * @author lyy
 * 
 */
public class MainActivity extends Activity implements OnHeaderRefreshListener,
		OnFooterRefreshListener {

	MyAdapter myAdapter;
	// �Զ����GridView����������ˢ��
	private PullToRefreshView mPullToRefreshView;
	private MyListView gridView;
	private List<Integer> data;
	private List<Integer> gridViewData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPullToRefreshView = (com.example.demo.PullToRefreshView) findViewById(R.id.main_pull_refresh_view);
		gridView = (MyListView) findViewById(R.id.gridView1);
		myAdapter = new MyAdapter(this);
		data = new ArrayList<Integer>();
		gridViewData = new ArrayList<Integer>();
		for (int i = 0; i < 2; i++) {
			data.add(i);
		}

		for (int i = 0; i < 12; i++) {
			if (i % 2 == 0) {
				gridViewData.add(R.drawable.pic1);
			} else {
				gridViewData.add(R.drawable.pic2);
			}

		}
		myAdapter.setData(data);
		myAdapter.setGridViewData(gridViewData);
		gridView.setAdapter(myAdapter);

		mPullToRefreshView.setOnHeaderRefreshListener(this);
		mPullToRefreshView.setOnFooterRefreshListener(this);
		mPullToRefreshView.setLastUpdated(new Date().toLocaleString());
	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		mPullToRefreshView.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPullToRefreshView.onHeaderRefreshComplete("������:"
						+ Calendar.getInstance().getTime().toLocaleString());
				mPullToRefreshView.onHeaderRefreshComplete();

				Toast.makeText(MainActivity.this, "����ˢ�����!", 0).show();
			}

		}, 3000);

	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {
				mPullToRefreshView.onFooterRefreshComplete();
				gridViewData.add(R.drawable.pic1);
				myAdapter.setGridViewData(gridViewData);
				Toast.makeText(MainActivity.this, "���ظ�������!", 0).show();
			}

		}, 3000);

	}

}

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
 * ScrollView 嵌套ListView 嵌套GridView的上拉加载更多，下拉刷新。
 * 
 * 逻辑在适配器做了处理
 * 
 * 我们只让ListView加载2个数据Item，第一个是item对象，第二个是一个对象
 * 
 * 
 * @author lyy
 * 
 */
public class MainActivity extends Activity implements OnHeaderRefreshListener,
		OnFooterRefreshListener {

	MyAdapter myAdapter;
	// 自定义的GridView的上下拉动刷新
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
				mPullToRefreshView.onHeaderRefreshComplete("更新于:"
						+ Calendar.getInstance().getTime().toLocaleString());
				mPullToRefreshView.onHeaderRefreshComplete();

				Toast.makeText(MainActivity.this, "数据刷新完成!", 0).show();
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
				Toast.makeText(MainActivity.this, "加载更多数据!", 0).show();
			}

		}, 3000);

	}

}

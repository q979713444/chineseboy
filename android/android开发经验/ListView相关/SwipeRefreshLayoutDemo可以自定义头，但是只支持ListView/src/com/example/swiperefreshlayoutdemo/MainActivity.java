package com.example.swiperefreshlayoutdemo;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.ListView;

import com.example.swiperefreshlayoutdemo.RefreshLayout.OnLoadListener;

public class MainActivity extends Activity implements OnRefreshListener,
		OnLoadListener {

	private RefreshLayout swipeLayout;
	private ListView listView;
	private MyAdapter adapter;
	private ArrayList<HashMap<String, String>> list;
	private View header;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setData();
		setListener();
		/*设置自动刷新 swipeLayout.setRefreshing(true);
		setRefreshing(true)是不会触发onRefresh的,必须要手动调用一次
		所以在界面onCreate里面想要立刻加载就需要这样*/
		swipeLayout.post(new Thread(new Runnable() {
			
			@Override
			public void run() {
				swipeLayout.setRefreshing(true);
			}
		}));
		onRefresh();
	}

	/**
	 * 初始化布局
	 */
	@SuppressLint({ "InlinedApi", "InflateParams" })
	private void initView() {
		header = getLayoutInflater().inflate(R.layout.header, null);
		swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
		swipeLayout.setColorSchemeResources(R.color.color_bule2,
				R.color.color_bule,
				R.color.color_bule2,
				R.color.color_bule3);
	}

	/**
	 * 添加数据
	 */
	private void setData() {
		list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 10; i++) {
			list.add(new HashMap<String, String>());
		}

		listView = (ListView) findViewById(R.id.list);
		listView.addHeaderView(header);
		adapter = new MyAdapter(this, list);
		listView.setAdapter(adapter);
	}

	/**
	 * 设置监听
	 */
	private void setListener() {
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setOnLoadListener(this);
	}

	/**
	 * 上拉刷新
	 */
	@Override
	public void onRefresh() {
		swipeLayout.postDelayed(new Runnable() {

			@Override
			public void run() {
				// 更新数据
				// 更新完后调用该方法结束刷新
				list.clear();
				for (int i = 0; i < 10; i++) {
					list.add(new HashMap<String, String>());
				}
				adapter.notifyDataSetChanged();
				swipeLayout.setRefreshing(false);
			}
		}, 2000);

	}

	/**
	 * 加载更多
	 */
	@Override
	public void onLoad() {
		if(list.size()>40){
			return;
		}
		swipeLayout.postDelayed(new Runnable() {

			@Override
			public void run() {
				// 更新数据
				// 更新完后调用该方法结束刷新
				swipeLayout.setLoading(false);
				if(list.size()>40){
					return;
				}
				for (int i = 0; i < 10; i++) {
					list.add(new HashMap<String, String>());
				}
				adapter.notifyDataSetChanged();
			}
		}, 2000);
	}

}

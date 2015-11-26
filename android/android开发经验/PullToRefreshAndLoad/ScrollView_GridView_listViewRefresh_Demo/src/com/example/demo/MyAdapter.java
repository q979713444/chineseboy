package com.example.demo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * lsitView的适配器
 * 
 * @author lyy
 * 
 */
public class MyAdapter extends BaseAdapter {
	Context context;
	private List<Integer> data;

	/***
	 * listview item position==2
	 * 
	 * GridView里面的数据
	 * 
	 */
	private List<Integer> gridViewData;
	private GridViewAdapter gridViewAdapter;

	/** ListView 的数据 **/
	public void setData(List<Integer> data) {
		this.data = data;
		this.notifyDataSetChanged();
	}

	/*** gridView的数据 **/

	public void setGridViewData(List<Integer> gridViewData) {
		this.gridViewData = gridViewData;

		this.notifyDataSetChanged();

	}

	public MyAdapter(Context context) {
		this.context = context;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (position == 0) {

			return listView();
		} else {

			return gridView();
		}

	}

	public View listView() {
		View view = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.item, null);
		}

		ImageView imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		return view;
	}

	public View gridView() {

		View view = LayoutInflater.from(context).inflate(R.layout.item1, null);
		GridView myGridView = (GridView) view.findViewById(R.id.gridView1);

		if (gridViewAdapter == null)
			gridViewAdapter = new GridViewAdapter(context);

		gridViewAdapter.setData(gridViewData);
		myGridView.setAdapter(gridViewAdapter);

		myGridView.setOnItemClickListener(onItemClickListener);
		return view;
	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Toast.makeText(context, "你点击的是：" + position, 0).show();

		}

	};
}

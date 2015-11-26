package com.example.horizontallistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/***
 * 
 * @author ymw
 * @summary 博客地址欢迎访问： http://www.cnblogs.com/_ymw
 */
public class MainActivity extends Activity {

	List<CityItem> cityList;
	RelativeLayout itmel;
	GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LayoutInflater layoutInflater = (LayoutInflater) this
				.getSystemService("layout_inflater");
		gridView = (GridView) findViewById(R.id.grid);
		setData();
		setGridView();
	}

	private void setData() {
		cityList = new ArrayList<CityItem>();
		CityItem item = new CityItem();
		item.setCityName("深圳");
		item.setCityCode("0755");
		cityList.add(item);
		item = new CityItem();
		item.setCityName("上海");
		item.setCityCode("021");
		cityList.add(item);
		item = new CityItem();
		item.setCityName("广州");
		item.setCityCode("020");
		cityList.add(item);
		item = new CityItem();
		item.setCityName("北京");
		item.setCityCode("010");
		cityList.add(item);
		item = new CityItem();
		item.setCityName("武汉");
		item.setCityCode("027");
		cityList.add(item);
		item = new CityItem();
		item.setCityName("孝感");
		item.setCityCode("0712");
		cityList.add(item);
		cityList.addAll(cityList);
	}

	private void setGridView() {

		int size = cityList.size();

		int length = 100;

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int gridviewWidth = (int) (size * (length + 4) * density);
		int itemWidth = (int) (length * density);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
		gridView.setLayoutParams(params); // 重点
		gridView.setColumnWidth(itemWidth); // 重点
		gridView.setHorizontalSpacing(5); // 间距
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(size); // 重点

		GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),
				cityList);
		gridView.setAdapter(adapter);
	}

	public class GridViewAdapter extends BaseAdapter {
		Context context;
		List<CityItem> list;

		public GridViewAdapter(Context _context, List<CityItem> _list) {
			this.list = _list;
			this.context = _context;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			convertView = layoutInflater.inflate(R.layout.list_item, null);
			TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
			TextView tvCode = (TextView) convertView.findViewById(R.id.tvCode);
			CityItem city = list.get(position);

			tvCity.setText(city.getCityName());
			tvCode.setText(city.getCityCode());
			return convertView;
		}
	}

	public class CityItem {
		private String cityName;
		private String cityCode;

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getCityCode() {
			return cityCode;
		}

		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
	}
}

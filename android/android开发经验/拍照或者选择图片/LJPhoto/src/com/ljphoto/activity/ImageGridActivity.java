package com.ljphoto.activity;

import java.util.ArrayList;
import java.util.List;

import com.ljphoto.util.AlbumHelper;
import com.ljphoto.util.Bimp;
import com.ljphoto.util.BitmapCache;
import com.ljphoto.util.ImageBucket;
import com.ljphoto.util.ImageItem;
import com.ljphoto.util.BitmapCache.ImageCallback;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * 图片的目录详细页
 * @author Administrator
 *
 */

public class ImageGridActivity extends Activity implements OnClickListener{
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	
	private List<ImageBucket> contentList;
	private RelativeLayout title;
	
	private List<ImageItem> dataList;
	private GridView gridView;
	private ImageGridAdapter adapter;
	private AlbumHelper helper;
	private Button bt;
	private int count;
	
	private TextView tv_cancel;
	private TextView tv_content;
	private PopupWindow pop = null;
	private ListView popListView = null;
	
	private BitmapCache cache = new BitmapCache();
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(ImageGridActivity.this, "最多选择9张图片", 400).show();
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_grid);
		
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		
		contentList = helper.getImagesBucketList(false);
		dataList = new ArrayList<ImageItem>();
		for(int i = 0; i<contentList.size(); i++){
			dataList.addAll( contentList.get(i).imageList );
		}
		ImageBucket bucket = new ImageBucket();
		bucket.bucketName = "所有图片";
		bucket.imageList = dataList;
		bucket.count = bucket.imageList.size();
		contentList.add(0, bucket);
		
		initView();

	}

	private void initView() {
		title = (RelativeLayout) findViewById(R.id.title);
		
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new ImageGridAdapter();
		gridView.setAdapter(adapter);


		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				adapter.notifyDataSetChanged();
			}

		});
		
		bt = (Button) findViewById(R.id.bt);
		if(Bimp.selectBitmap.size() != 0){
			count = Bimp.selectBitmap.size();
			bt.setText("完成" + "(" + count + ")");
		}
		bt.setOnClickListener(this);
		
		tv_cancel = (TextView) findViewById(R.id.tv_cancel);
		tv_cancel.setOnClickListener(this);
		
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_content.setOnClickListener(this);
		
		View popLayout = getLayoutInflater().inflate(R.layout.poplayout, null);
		pop = new PopupWindow(popLayout, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setAnimationStyle(R.style.PopupAnimation1);
		popListView = (ListView) popLayout.findViewById(R.id.lv_content);
		popListView.setAdapter(new MyAdapter());
		popListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				dataList = contentList.get(arg2).imageList;
				adapter.notifyDataSetChanged();
				pop.dismiss();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv_cancel:
			finish();
			overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
			break;
		case R.id.bt:
			Bimp.selectBitmap.addAll(Bimp.tempSelectBitmap);
			Bimp.tempSelectBitmap.clear();
			finish();
			overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
			break;
		case R.id.tv_content:
			pop.showAsDropDown(title);
			break;
		}
	}
	
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return contentList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(view == null){
				view = getLayoutInflater().inflate(R.layout.poplayoutitem, null);
			}

			TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
			

			tv_content.setText(contentList.get(position).bucketName + "(" + contentList.get(position).count + ")");
			
			return view;
		}
		
	}
	
	class ImageGridAdapter extends BaseAdapter {
		
		
		ImageCallback callback = new ImageCallback() {
			@Override
			public void imageLoad(ImageView imageView, Bitmap bitmap,
					Object... params) {
				if (imageView != null && bitmap != null) {
					String url = (String) params[0];
					if (url != null && url.equals((String) imageView.getTag())) {
						((ImageView) imageView).setImageBitmap(bitmap);
					} else {
						//"callback, bmp not match"
					}
				} else {
					//"callback, bmp null"
				}
			}
		};
		
		@Override
		public int getCount() {
			int count = 0;
			if (dataList != null) {
				count = dataList.size();
			}
			return count;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		class Holder {
			private ImageView iv;
			private ImageView selected;
			private TextView text;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final Holder holder;

			if (convertView == null) {
				holder = new Holder();
				convertView = getLayoutInflater().inflate(R.layout.item_image_grid, null);
				holder.iv = (ImageView) convertView.findViewById(R.id.image);
				holder.selected = (ImageView) convertView
						.findViewById(R.id.isselected);
				holder.text = (TextView) convertView
						.findViewById(R.id.item_image_grid_text);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			
			dataList.get(position).isSelected = false;
			if (Bimp.tempSelectBitmap.size() == 0) {
				dataList.get(position).isSelected = false;
			} else {
				for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
					if (dataList.get(position).imageId.equals(Bimp.tempSelectBitmap
							.get(i).imageId)) {
						dataList.get(position).isSelected = true;
						break;
					} else {
						dataList.get(position).isSelected = false;
					}
				}
			}
			/**
			 * 以下代码为功能为记录已经添加的照片
			 */
//			if(Bimp.selectBitmap.size() == 0){
//				dataList.get(position).isSelected = false;
//			}else{
//				for(int i = 0; i<Bimp.selectBitmap.size(); i++){
//					if(dataList.get(position).imageId.equals(Bimp.selectBitmap.get(i).imageId)){
//						dataList.get(position).isSelected = true;
//						break;
//					}else{
//						dataList.get(position).isSelected = false;
//					}
//				}
//			}
			/**
			 * end
			 */
			
			final ImageItem item = dataList.get(position);
			
			holder.iv.setTag(item.imagePath);
			cache.displayBmp(holder.iv, item.thumbnailPath, item.imagePath,callback);
			if (item.isSelected) {
				holder.selected.setVisibility(View.VISIBLE);
				holder.selected.setImageResource(R.drawable.icon_data_select);  
				holder.text.setBackgroundResource(R.drawable.bgd_relatly_line);
			} else {
				holder.selected.setVisibility(View.INVISIBLE);
				holder.text.setBackgroundColor(0x00000000);
			}
			holder.iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String path = dataList.get(position).imagePath;
					System.out.println("----picpath----" + path);
					if (Bimp.selectBitmap.size() + Bimp.tempSelectBitmap.size() < 9 ) {
						item.isSelected = !item.isSelected;
						if (item.isSelected) {
							holder.selected.setVisibility(View.VISIBLE);
							dataList.get(position).setSelected(true);
							Bimp.tempSelectBitmap.add(dataList.get(position));
							holder.selected.setImageResource(R.drawable.icon_data_select);
							holder.text.setBackgroundResource(R.drawable.bgd_relatly_line);
							bt.setText("完成" + "(" + (Bimp.selectBitmap.size() + Bimp.tempSelectBitmap.size()) + ")");
						} else if (!item.isSelected) {
							holder.selected.setVisibility(View.INVISIBLE);
							dataList.get(position).setSelected(false);
							Bimp.tempSelectBitmap.remove(dataList.get(position));
							holder.text.setBackgroundColor(0x00000000);
							bt.setText("完成" + "(" + (Bimp.selectBitmap.size() + Bimp.tempSelectBitmap.size()) + ")");
						}
					} else if (Bimp.selectBitmap.size() + Bimp.tempSelectBitmap.size() >= 9) {
						if (item.isSelected == true) {
							item.isSelected = !item.isSelected;
							holder.selected.setVisibility(View.INVISIBLE);
							dataList.get(position).setSelected(false);
							Bimp.selectBitmap.remove(dataList.get(position));

						} else {
							Message message = Message.obtain(mHandler, 0);
							message.sendToTarget();
						}
					}
				}

			});

			return convertView;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(pop.isShowing()){
			pop.dismiss();
		}
		return super.onTouchEvent(event);
	}
	
}

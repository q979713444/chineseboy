package com.ljphoto.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljphoto.activity.R;
import com.ljphoto.util.BitmapCache.ImageCallback;

public class ImageGridAdapter extends BaseAdapter {

	private TextCallback textcallback = null;
	final String TAG = getClass().getSimpleName();
	Activity act;
	List<ImageItem> dataList;
	public Map<String, String> map = new HashMap<String, String>();
	BitmapCache cache;
	private Handler mHandler;
	
	ImageCallback callback = new ImageCallback() {
		@Override
		public void imageLoad(ImageView imageView, Bitmap bitmap,
				Object... params) {
			if (imageView != null && bitmap != null) {
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag())) {
					((ImageView) imageView).setImageBitmap(bitmap);
				} else {
					Log.e(TAG, "callback, bmp not match");
				}
			} else {
				Log.e(TAG, "callback, bmp null");
			}
		}
	};

	public static interface TextCallback {
		public void onListen(int count);
	}

	public void setTextCallback(TextCallback listener) {
		textcallback = listener;
	}

	public ImageGridAdapter(Activity act, List<ImageItem> list, Handler mHandler) {
		this.act = act;
		dataList = list;
		cache = new BitmapCache();
		this.mHandler = mHandler;
	}

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
			convertView = View.inflate(act, R.layout.item_image_grid, null);
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
//		if(Bimp.selectBitmap.size() == 0){
//			dataList.get(position).isSelected = false;
//		}else{
//			for(int i = 0; i<Bimp.selectBitmap.size(); i++){
//				if(dataList.get(position).imageId.equals(Bimp.selectBitmap.get(i).imageId)){
//					dataList.get(position).isSelected = true;
//					break;
//				}else{
//					dataList.get(position).isSelected = false;
//				}
//			}
//		}
		/**
		 * end
		 */
		
		final ImageItem item = dataList.get(position);
		
		holder.iv.setTag(item.imagePath);
		cache.displayBmp(holder.iv, item.thumbnailPath, item.imagePath,
				callback);
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
						if (textcallback != null)
							textcallback.onListen(Bimp.selectBitmap.size() + Bimp.tempSelectBitmap.size());
					} else if (!item.isSelected) {
						holder.selected.setVisibility(View.INVISIBLE);
						dataList.get(position).setSelected(false);
						Bimp.tempSelectBitmap.remove(dataList.get(position));
						holder.text.setBackgroundColor(0x00000000);
						if (textcallback != null)
							textcallback.onListen(Bimp.selectBitmap.size() + Bimp.tempSelectBitmap.size());

					}
				} else if (Bimp.selectBitmap.size() + Bimp.tempSelectBitmap.size() >= 9) {
					if (item.isSelected == true) {
						item.isSelected = !item.isSelected;
						holder.selected.setVisibility(View.INVISIBLE);
						dataList.get(position).setSelected(false);
						Bimp.selectBitmap.remove(dataList.get(position));
						map.remove(path);

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

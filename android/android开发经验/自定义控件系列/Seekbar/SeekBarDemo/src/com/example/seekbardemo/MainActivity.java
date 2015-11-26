package com.example.seekbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView num_tv;
	private SeekBar seekBar;
	private double width, fDensity;
	private String numbers;

	private DisplayMetrics displaysMetrics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initSeekBarProgress();
	}

	private void initView() {
		displaysMetrics = getResources().getDisplayMetrics();
		width = displaysMetrics.widthPixels;
		fDensity = (width - Utils.dip2px(this, 51)) / 100;
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		num_tv = (TextView) findViewById(R.id.num_tv);
	}

	private void initSeekBarProgress() {
		seekBar.setProgress(45);
		seekBar.setOnSeekBarChangeListener(mSeekChange);
		LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		numbers = 45 + "";
		paramsStrength.leftMargin = (int) (45 * fDensity);
		num_tv.setLayoutParams(paramsStrength);
		num_tv.setText(numbers + "个");
	}

	private OnSeekBarChangeListener mSeekChange = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			numbers = progress + "";
			LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			paramsStrength.leftMargin = (int) (progress * fDensity);
			num_tv.setLayoutParams(paramsStrength);
			num_tv.setText(numbers + "个");

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}
	};

}

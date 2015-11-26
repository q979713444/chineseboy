package hxl.smzdm.fragment;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivityHelper;

import hxl.smzdm.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class MainIndexFM extends Fragment implements OnItemClickListener,
		OnClickListener {
	
	private Context context;
	private ImageView main_more;
	private ImageView main_me;
	private OnOpenSMLinstener onOpenSMLinstener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fm_index,container, false);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			onOpenSMLinstener = (OnOpenSMLinstener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnOpenSMLinstener");
		}
	}

	private SlidingActivityHelper mHelper;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		context = getActivity();
		main_more = (ImageView) getActivity().findViewById(R.id.main_more);
		main_more.setOnClickListener(this);
		main_me = (ImageView) getActivity().findViewById(R.id.main_me);
		main_me.setOnClickListener(this);
	}

	public interface OnOpenSMLinstener {
		public void openSM();
		public void openSM1();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.main_more:
			onOpenSMLinstener.openSM1();
			break;
		case R.id.main_me:
			onOpenSMLinstener.openSM();
			break;
		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}

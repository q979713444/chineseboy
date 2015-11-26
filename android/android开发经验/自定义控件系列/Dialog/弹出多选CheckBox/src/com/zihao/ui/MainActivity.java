package com.zihao.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * @author zihao
 *
 */
public class MainActivity extends Activity {

	private String arrs[] = { "Item0", "Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8" };

	private boolean boos[] = { true, false, false, true, true, false, false, false, true };

	private CustomMultiChoiceDialog.Builder multiChoiceDialogBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showMultiChoiceDialog(View view) {
		multiChoiceDialogBuilder = new CustomMultiChoiceDialog.Builder(this);
		CustomMultiChoiceDialog multiChoiceDialog = multiChoiceDialogBuilder.setTitle("选择")
				.setMultiChoiceItems(arrs, boos, null, true)
				.setPositiveButton("确定", new PositiveClickListener()).setNegativeButton("取消", null).create();
		multiChoiceDialog.show();
	}

	class PositiveClickListener implements OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			String s = "您选择了:";
			boos = multiChoiceDialogBuilder.getCheckedItems();
			for (int i = 0; i < boos.length; i++) {
				if (boos[i]) {
					s += i + ":" + arrs[i] + "  ";
				} 
			}
			alert(MainActivity.this, s);
		}
	}

	public void alert(Context context, String str) {
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}
	
}

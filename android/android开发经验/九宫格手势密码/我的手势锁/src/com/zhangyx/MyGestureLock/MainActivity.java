package com.zhangyx.MyGestureLock;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

/***
 *
 *com.zhangyx.MyGestureLock.MainActivity
 * @author Admin-zhangyx
 *
 * create at 2015-1-16 下午1:56:18
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       LinearLayout ll=new LinearLayout(this);
       ll.setOrientation(LinearLayout.VERTICAL);
       ll.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg2));
       TextView tv=new TextView(this);
       tv.setText(" 解锁成功......");
       tv.setTextSize(20);
       tv.setTextColor(Color.RED);
       tv.setGravity(Gravity.CENTER);
       
       LinearLayout.LayoutParams llParams=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
       ll.setLayoutParams(llParams);
       ll.addView(tv);
       setContentView(ll);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

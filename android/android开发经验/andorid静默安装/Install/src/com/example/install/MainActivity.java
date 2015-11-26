package com.example.install;

import java.io.DataOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_packagename;  
    private String cmd_install = "pm install -r ";  
    private String cmd_uninstall = "pm uninstall ";  
    String apkLocation = Environment.getExternalStorageDirectory().toString()  
            + "/";  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        et_packagename = (EditText) findViewById(R.id.et_packagename);  
    }  
  
    public void onClick_install(View view) {  
        String cmd = cmd_install + apkLocation  
                + et_packagename.getText().toString().trim();  
        System.out.println("静默安装命令：" + cmd);  
        excuteSuCMD(cmd);  
    }  
  
    public void onClick_uninstall(View view) { 
    	Log.d("tag", "onClick_uninstall");
        String cmd = cmd_uninstall + et_packagename.getText().toString().trim();  
        // String cmd = cmd_uninstall + "com.kingsoft.website";  
        Log.d("tag", "uninstall cmd：" + cmd);  
        Toast.makeText(getApplicationContext(), "uninstall cmd：" + cmd, Toast.LENGTH_LONG).show();
        excuteSuCMD(cmd);  
    }  
        //执行shell命令  
    protected int excuteSuCMD(String cmd) {  
        try {  
            Process process = Runtime.getRuntime().exec("su");  
            DataOutputStream dos = new DataOutputStream(  
                    (OutputStream) process.getOutputStream());  
            // 部分手机Root之后Library path 丢失，导入library path可解决该问题  
            dos.writeBytes((String) "export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n");  
            cmd = String.valueOf(cmd);  
            dos.writeBytes((String) (cmd + "\n"));  
            dos.flush();  
            dos.writeBytes("exit\n");  
            dos.flush();  
            process.waitFor();  
            int result = process.exitValue();  
            return (Integer) result;  
        } catch (Exception localException) {  
            localException.printStackTrace();  
            return -1;  
        }  
    }  

}

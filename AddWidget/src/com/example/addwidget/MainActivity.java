package com.example.addwidget;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


public class MainActivity extends Activity {
	FrameLayout frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = (FrameLayout)findViewById(R.id.framelayout);
        
        new Handler().postDelayed(new Runnable() {
			
			@SuppressLint("NewApi") @Override
			public void run() {
				// TODO Auto-generated method stub
				//AppWidgetManager wm = (AppWidgetManager)getApplicationContext().getSystemService("appwidget");
				AppWidgetManager wm = AppWidgetManager.getInstance(getApplicationContext());
				Log.i("widget-------", "wm = " + wm);
				List<AppWidgetProviderInfo> infos = wm.getInstalledProviders();
				AppWidgetProviderInfo info = null;
				for(int i = 0 ; i < infos.size();i++){
					Log.i("widget-------", "provider = " + infos.get(i).provider);
					if(infos.get(i).provider.getPackageName().equals("com.android.zello")){
						info = infos.get(i);
					}
				}
				Log.i("widget-------", "info = " + info);
				AppWidgetHost host = new AppWidgetHost(MainActivity.this, 1029);
				host.startListening();
				Log.i("widget-------", "host = " + host);
				int id = host.allocateAppWidgetId();
				Log.i("widget-------", "id = " + id);
				Log.i("widget-------", "provider = " + info.provider);
				boolean success = wm.bindAppWidgetIdIfAllowed(id, info.provider);
				
				Log.i("widget-------", "success = " + success);
		        if(success){
		        	AppWidgetHostView fl = host.createView(getApplicationContext(), id, info);
		        	frame.addView(fl);
		        }
			}
		},0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

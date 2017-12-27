package org.corgiking.demo.activity.widget;

import org.corgiking.demo.activity.R;
import org.corgiking.demo.activity.R.id;
import org.corgiking.demo.activity.R.layout;
import org.corgiking.demo.activity.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class TabWidgetActivity extends Activity {
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_widget, menu);
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

	private void init() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		// 为TabHost对象添加标签页
		LayoutInflater inflater = LayoutInflater.from(this);
		inflater.inflate(R.layout.tab_view_1, tabHost.getTabContentView());
		inflater.inflate(R.layout.tab_view_2, tabHost.getTabContentView());
		
		tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("我是Tab1").setContent(R.id.RelativeLayout1));
		
		tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("我是Tab2").setContent(R.id.RelativeLayout2));
	}
}

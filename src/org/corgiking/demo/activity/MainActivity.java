package org.corgiking.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
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
	
	private void init() {
		initItemList();
	}

	private void initItemList() {
		LinearLayout itemList = (LinearLayout) findViewById(R.id.item_list);
		
		//5.1进度条类组件
		itemList.addView(newBtn("进度条类组件", BarActivity.class));
		itemList.addView(newBtn("ImageSwitcher组件", ImageSwitcherActivity.class));
		itemList.addView(newBtn("GridView组件", GridViewActivity.class));
	}
	private <T> Button newBtn(String item, final Class<T> clazz){
		Button btn = new Button(this);
		btn.setText(item);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, clazz);
				startActivity(intent);
			}
		});
		return btn;
	}
}

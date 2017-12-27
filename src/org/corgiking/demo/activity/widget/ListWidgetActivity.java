package org.corgiking.demo.activity.widget;

import org.corgiking.demo.activity.R;
import org.corgiking.demo.activity.R.id;
import org.corgiking.demo.activity.R.layout;
import org.corgiking.demo.activity.R.menu;
import org.corgiking.demo.util.ToastUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ListWidgetActivity extends Activity {
	private Spinner spinner1;
	private TextView spinnerText1;
	private Spinner spinner2;
	private TextView spinnerText2;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
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
		// 下拉列表1
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinnerText1 = (TextView) findViewById(R.id.spinnerText1);
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String result = parent.getItemAtPosition(position).toString();
				spinnerText1.setText(result);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		// 下拉列表2
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinnerText2 = (TextView) findViewById(R.id.spinnerText2);
		String[] ctype = new String[] { "英雄联盟", "王者荣耀", "自由之战", "300英雄" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter);
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String result = parent.getItemAtPosition(position).toString();
				spinnerText2.setText(result);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		// 列表视图
		listView = (ListView) findViewById(R.id.listView);
		String[] items = new String[] { "张三", "李四", "王五", "赵六" };
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String result = parent.getItemAtPosition(position).toString();
				ToastUtil.showToast(ListWidgetActivity.this, result);
			}
		});
	}
}

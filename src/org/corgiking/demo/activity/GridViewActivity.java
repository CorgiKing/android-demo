package org.corgiking.demo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewActivity extends Activity {
	private GridView gridView;
	
	private int[] arrayPictures;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid_view, menu);
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
		arrayPictures = new int[] { R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04,
				R.drawable.img05, R.drawable.img06, R.drawable.img07, R.drawable.img08, R.drawable.img09 };
		
		gridView = (GridView) findViewById(R.id.gridView);
		gridView.setAdapter(new ImageAdapter(this));
		
	}
	
	private class ImageAdapter extends BaseAdapter{
		private Context context;
		
		public ImageAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			return arrayPictures.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
//				imageView.setLayoutParams(new GridView.LayoutParams(100,90));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			}else{
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(arrayPictures[position]);
			return imageView;
		}
		
	}
}

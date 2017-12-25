package org.corgiking.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class ImageSwitcherActivity extends Activity {
	private ImageSwitcher imageSwitcher;

	private int[] arrayPictures;
	private int pictureIndex;
	private float touchDownX;
	private float touchUpX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_switcher);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_switcher, menu);
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
		// 初始化
		pictureIndex = 0;
		arrayPictures = new int[] { R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04,
				R.drawable.img05, R.drawable.img06, R.drawable.img07, R.drawable.img08, R.drawable.img09 };
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

		// 设置Factory,用于显示图片，与父窗口区分开
		imageSwitcher.setFactory(new ImageSwitcher.ViewFactory() {

			@Override
			public View makeView() {
				ImageView imageView = new ImageView(ImageSwitcherActivity.this);
				imageView.setImageResource(pictureIndex);
				return imageView;
			}
		});
		imageSwitcher.setImageResource(arrayPictures[pictureIndex]);
		// 设置监听
		imageSwitcher.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					touchDownX = event.getX();
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					touchUpX = event.getX();
					if (touchUpX - touchDownX > 100) {
						// 从左往右
						//获取当前图片id
						pictureIndex = pictureIndex == 0 ? arrayPictures.length - 1 : pictureIndex - 1;
						//设置动画
						imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(ImageSwitcherActivity.this, R.anim.slide_in_left));
						imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(ImageSwitcherActivity.this, R.anim.slide_out_right));
						//设置图片
						imageSwitcher.setImageResource(arrayPictures[pictureIndex]);
					} else if (touchDownX - touchUpX > 100) {
						// 从右往左
						//获取当前图片id
						pictureIndex = pictureIndex == arrayPictures.length - 1 ? 0 : pictureIndex + 1;
						//设置动画
						imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(ImageSwitcherActivity.this, R.anim.slide_in_right));
						imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(ImageSwitcherActivity.this, R.anim.slide_out_left));
						//设置图片
						imageSwitcher.setImageResource(arrayPictures[pictureIndex]);
					}
					return true;
				}
				return false;
			}
		});

	}
}

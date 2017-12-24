package org.corgiking.demo.activity;

import org.corgiking.demo.util.ToastUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class BarActivity extends Activity {
	private ProgressBar horizonBar;
	private TextView horizonBarStatusText;
	private SeekBar seekBar;
	private TextView seekBarStatusText;
	private RatingBar ratingBar;
	private TextView ratingBarStatusText;

	private int horizonBarStatus;// 完成进度
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bar, menu);
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
		// 进度条
		horizonBar = (ProgressBar) findViewById(R.id.horizontalBar);
		horizonBarStatusText = (TextView) findViewById(R.id.horizontalBarStatus);
		mHandler = new Handler() {
			@Override
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 1) {
					horizonBarStatus = horizonBarStatus > 100 ? 100 : horizonBarStatus;
					horizonBar.setProgress(horizonBarStatus);
					horizonBarStatusText.setText(horizonBar.getProgress() + "%");
					if (horizonBar.getProgress() == horizonBar.getMax()) {
						ToastUtil.showToast(BarActivity.this, "进度完成！");
						horizonBarStatus = 0;
						// 设置进度条不显示，并且不占用空间
						// horizonBar.setVisibility(View.GONE);
					}
				}
			};
		};
		startThread();

		// 拖动条
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBarStatusText = (TextView) findViewById(R.id.seekBarStatus);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				seekBarStatusText.setText(progress + "%");
			}
		});
		
		// 星级评分条
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		ratingBarStatusText = (TextView) findViewById(R.id.ratingBarStatus);
		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				int progress = ratingBar.getProgress();
				float step = ratingBar.getStepSize();
				ToastUtil.showToast(BarActivity.this, "星级评分");
				ratingBarStatusText.setText("进度："+progress+",星级："+rating+",最少改变星级:"+step);
			}
		});
	}

	private void startThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					horizonBarStatus += Math.random() * 10;

					Message msg = new Message();
					msg.what = 1;
					mHandler.sendMessage(msg);
					if (horizonBarStatus > 100) {
						// break;
					}

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

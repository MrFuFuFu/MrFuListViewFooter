package com.mrfu.listviewfooter;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.mrfu.listviewfooter.lib.LoadMoreAdapter;
import com.mrfu.listviewfooter.lib.LoadMoreListView;

/***
 * @author MrFu
 */
public class MainActivity extends Activity {
	private Context mContext = MainActivity.this;
	private LoadMoreListView listview;
	private List<PriceModel> list;
	TestAdapter<PriceModel> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview = (LoadMoreListView)findViewById(R.id.listview);
		list = new ArrayList<PriceModel>();
		initData();
		adapter = new TestAdapter<PriceModel>(this, list);
		adapter.setOnLoadMoreRequestListener(new LoadMoreAdapter.OnLoadMoreRequestListener() {
			@Override
			public void onLoadMoreRequested() {
				listview.setLoadMore();
				new MyAsyncTasd().execute();
			}
		});
		listview.setAdapter(adapter);
	}
	private void initData() {
		for (int i = 0; i < 100; i++) {
			PriceModel model = new PriceModel();
			model.price = "i="+i;
			list.add(model);
		}
	}

	private void initData2() {
		list = new ArrayList<PriceModel>();
		for (int i = 100; i < 200; i++) {
			PriceModel model = new PriceModel();
			model.price = "i="+i;
			list.add(model);
		}
		adapter.append(list);
	}
	
	private class MyAsyncTasd extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			initData2();
			listview.reset();
		}
		
		
		
	}
	
	
	
	
}

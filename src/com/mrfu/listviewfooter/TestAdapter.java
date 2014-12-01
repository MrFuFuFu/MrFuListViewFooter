package com.mrfu.listviewfooter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrfu.listviewfooter.lib.LoadMoreAdapter;

/**
 * @author Mr.傅 2014-11-23 下午9:21:56
 * @param <T>
 */
public class TestAdapter<T> extends LoadMoreAdapter<PriceModel> {

	public TestAdapter(Context mContext, List<PriceModel> list) {
		super(mContext, list);
	}

	public TestAdapter(Context mContext, List<PriceModel> list, OnLoadMoreRequestListener loadMoreRequestListener) {
		super(mContext, list, loadMoreRequestListener);
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getViewRelize(int position, View convertView, ViewGroup parent) {
		PriceModel model = list.get(position);
		ViewHolder holder =null;
		if (convertView !=null) {
			holder = (ViewHolder)convertView.getTag();
		}else {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.item_list, null);
			holder.textView = (TextView)convertView.findViewById(R.id.textview);
			convertView.setTag(holder);
		}
		holder.textView.setText(model.price);
		return convertView;
	}
	private class ViewHolder{
		private TextView textView;
	}
	

}

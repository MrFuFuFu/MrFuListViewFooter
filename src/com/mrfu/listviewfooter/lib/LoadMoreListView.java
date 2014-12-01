package com.mrfu.listviewfooter.lib;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.mrfu.listviewfooter.R;

/**
 * @author Mr.傅 2014-11-23 下午8:52:10
 */
public class LoadMoreListView extends ListView {

	View footer;
	public LoadMoreListView(Context context) {
		super(context);
		setAddFooter();
	}

	public LoadMoreListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAddFooter();
	}

	public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setAddFooter();
	}
	
	private void setAddFooter(){
		footer = getLoadMoreView();
		addFooterView(footer);
	}
	

	protected View getLoadMoreView() {
		//TODO 这里用 getApplicationContext() 不知道是否合理~~
		ProgressBar loadMoreProgress = (ProgressBar) View.inflate(((Activity)getContext()).getApplicationContext(), R.layout.auto_load_more_footer, null);
		loadMoreProgress.setBackgroundColor(Color.LTGRAY);
		return loadMoreProgress;
	}
	
	public void setLoadMore(){
		footer.setVisibility(View.VISIBLE);
	}

	
	public void reset(){
		if(footer.getVisibility() == View.VISIBLE){
			footer.setVisibility(View.GONE);
			postInvalidate();
		}
	}
}

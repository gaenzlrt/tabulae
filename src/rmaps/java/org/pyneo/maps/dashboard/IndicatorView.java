package org.pyneo.maps.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.pyneo.maps.R;

public class IndicatorView extends RelativeLayout implements OnCreateContextMenuListener, OnClickListener {
	private String mIndicatorTag;
	private IndicatorManager mIndicatorManager;

	public IndicatorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setOnCreateContextMenuListener(this);
		setOnClickListener(this);
	}

	public IndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);

		setOnCreateContextMenuListener(this);
		setOnClickListener(this);
	}

	public IndicatorView(Context context) {
		super(context);

		setOnCreateContextMenuListener(this);
		setOnClickListener(this);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}

	public String getIndicatorTag() {
		return mIndicatorTag;
	}

	public void setIndicatorTag(String tag) {
		mIndicatorTag = tag;
	}

	public void setIndicatorManager(IndicatorManager indicatorManager) {
		mIndicatorManager = indicatorManager;
	}

	public void updateIndicator(IndicatorManager indicatorManager) {
		if (indicatorManager.getIndicators().containsKey(mIndicatorTag)) {
			if (mIndicatorTag.equalsIgnoreCase(Constants.GPSELEV)
				|| mIndicatorTag.equalsIgnoreCase(Constants.GPSACCURACY)
				|| mIndicatorTag.equalsIgnoreCase(Constants.GPSSPEED)
				|| mIndicatorTag.equalsIgnoreCase(Constants.TRDIST)
				|| mIndicatorTag.equalsIgnoreCase(Constants.TRMAXSPEED)
				|| mIndicatorTag.equalsIgnoreCase(Constants.TRAVGSPEED)
				|| mIndicatorTag.equalsIgnoreCase(Constants.TRAVGMOVESPEED)
				|| mIndicatorTag.equalsIgnoreCase(Constants.TARGETDISTANCE)
				) {
				final String[] val = (String[])indicatorManager.getIndicators().get(mIndicatorTag);
				((TextView)findViewById(R.id.data_value)).setText(val[0]);
				((TextView)findViewById(R.id.data_unit)).setText(val[1]);
			} else {
				((TextView)findViewById(R.id.data_value)).setText(indicatorManager.getIndicators().get(mIndicatorTag).toString());
				((TextView)findViewById(R.id.data_unit)).setText(Constants.EMPTY);
			}
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		mIndicatorManager.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	protected ContextMenuInfo getContextMenuInfo() {
		final IndicatorViewMenuInfo info = new IndicatorViewMenuInfo();
		info.IndicatorView = this;
		return info;
	}

	@Override
	public void onClick(View v) {
	}

	public class IndicatorViewMenuInfo implements ContextMenuInfo {
		public IndicatorView IndicatorView;
	}
}
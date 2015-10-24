package org.pyneo.tabulae.gui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import org.pyneo.tabulae.Base;
import org.pyneo.tabulae.R;
import org.pyneo.tabulae.Tabulae;

public class Controller extends Base implements Constants {
	private Animation popOutAnimation;
	private Animation popInAnimation;
	private boolean optionsOut;
	private boolean tempAuto;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (DEBUG) Log.d(TAG, "Controller.onCreateView");
		View view = inflater.inflate(R.layout.controller, container, false);
		View.OnClickListener clickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int e = view.getId();
				// if (DEBUG) Log.d(TAG, "Controller.onClick e=" + e);
				switch (e) {
					case R.id.event_attribute: {
						if (!optionsOut) {
							getActivity().findViewById(R.id.attributes).startAnimation(popOutAnimation);
							optionsOut = true;
							return;
						}
					}
					break;
				}
				((Tabulae) getActivity()).inform(e, null);
			}
		};
		for (int resourceId : new int[]{
				R.id.event_attribute_blue,
				R.id.event_attribute_green,
				R.id.event_attribute_red,
				R.id.event_attribute_white,
				R.id.event_attribute_yellow,
				R.id.event_attribute,
				R.id.event_autofollow,
				R.id.event_overlay,
				R.id.event_zoom_in,
				R.id.event_zoom_out,
		}) {
			view.findViewById(resourceId).setOnClickListener(clickListener);
		}
		view.findViewById(R.id.event_autofollow).setVisibility(
			tempAuto ? View.INVISIBLE : View.VISIBLE);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (DEBUG) Log.d(TAG, "Controller.onActivityCreated");
		popOutAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.attributes_open);
		popInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.attributes_close);
	}

	public void inform(int event, Bundle extra) {
		//if (DEBUG) Log.d(TAG, "Controller.inform event=" + event + ", extra=" + extra);
		switch (event) {
			case R.id.event_attribute_red: {
				((ImageButton) getActivity().findViewById(R.id.event_attribute)).setImageResource(R.drawable.attribute_red);
			}
			break;
			case R.id.event_attribute_yellow: {
				((ImageButton) getActivity().findViewById(R.id.event_attribute)).setImageResource(R.drawable.attribute_yellow);
			}
			break;
			case R.id.event_attribute_green: {
				((ImageButton) getActivity().findViewById(R.id.event_attribute)).setImageResource(R.drawable.attribute_green);
			}
			break;
			case R.id.event_attribute_blue: {
				((ImageButton) getActivity().findViewById(R.id.event_attribute)).setImageResource(R.drawable.attribute_blue);
			}
			break;
			case R.id.event_attribute_white: {
				((ImageButton) getActivity().findViewById(R.id.event_attribute)).setImageResource(R.drawable.attribute_white);
			}
			break;
			case R.id.event_attribute:
			case R.id.event_overlay:
			case R.id.event_zoom_in:
			case R.id.event_zoom_out:
			case R.id.event_autofollow:
				break;
			case R.id.autofollow: {
				if (getActivity() == null) {
					tempAuto = extra.getBoolean("autofollow");
				}
				else {
					getActivity().findViewById(R.id.event_autofollow).setVisibility(
						extra.getBoolean("autofollow") ? View.INVISIBLE : View.VISIBLE);
				}
			}
			break;
			default: // prevent closing attributes on unknown events
				return;
		}
		if (optionsOut) {
			getActivity().findViewById(R.id.attributes).startAnimation(popInAnimation);
			optionsOut = false;
		}
	}
}

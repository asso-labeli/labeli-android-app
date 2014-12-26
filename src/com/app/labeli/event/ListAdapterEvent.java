package com.app.labeli.event;

import java.util.ArrayList;



import com.app.labeli.ItemNavigationDrawer;
import com.app.labeli.R;
import com.app.labeli.R.id;
import com.app.labeli.R.layout;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapterEvent extends BaseAdapter {

	private Context context;
	private ArrayList<ItemEvent> eventItems;

	public ListAdapterEvent(Context context, ArrayList<ItemEvent> eventItems){
		this.context = context;
		this.eventItems = eventItems;
	}

	@Override
	public int getCount() {
		return eventItems.size();
	}

	@Override
	public Object getItem(int position) {       
		return eventItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater)
					context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.list_view_event_item, null);
		}

		//ImageView imgIcon = (ImageView) convertView.findViewById(R.id.drawer_layout_icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.list_view_event_item_title);
		TextView txtAuthor = (TextView) convertView.findViewById(R.id.list_view_event_item_author);

		//imgIcon.setImageResource(eventItems.get(position).getIcon());
		if (eventItems != null){
			txtTitle.setText(eventItems.get(position).getName());
			txtAuthor.setText(" par " + eventItems.get(position).getAuthor().getFirstName() + " " + 
					eventItems.get(position).getAuthor().getLastName());

			// displaying count
			// check whether it set visible or not
			if(eventItems.get(position).getCounterVisibility()){
				//txtCount.setText(eventItems.get(position).getCount());
			}else{
			}
		}

		return convertView;
	}

}


package com.app.labeli.event;

import java.util.ArrayList;
import com.app.labeli.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * > @ListAdapterEvent
 *
 * Adapter for FragmentEvent's ListView
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class ListAdapterEvent extends BaseAdapter {

	private Context context;
	private ArrayList<Event> eventItems;

	public ListAdapterEvent(Context context, ArrayList<Event> eventItems){
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

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater)
					context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.list_view_item_event, null);
		}

		TextView txtTitle = (TextView) convertView.findViewById(R.id.list_view_item_event_title);
		TextView txtAuthor = (TextView) convertView.findViewById(R.id.list_view_item_event_author);

		if (eventItems != null){
			txtTitle.setText(eventItems.get(position).getName());
			txtAuthor.setText(" par " + eventItems.get(position).getAuthor().getFirstName() + " " + 
					eventItems.get(position).getAuthor().getLastName());
		}

		return convertView;
	}

}


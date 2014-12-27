package com.app.labeli.team;

import java.util.ArrayList;
import com.app.labeli.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapterTeam extends BaseAdapter {

	private Context context;
	private ArrayList<ItemTeam> projects;

	public ListAdapterTeam(Context context, ArrayList<ItemTeam> projects){
		this.context = context;
		this.projects = projects;
	}

	@Override
	public int getCount() {
		return projects.size();
	}

	@Override
	public Object getItem(int position) {       
		return projects.get(position);
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
			convertView = mInflater.inflate(R.layout.list_view_event_item, null);
		}

		TextView txtTitle = (TextView) convertView.findViewById(R.id.list_view_event_item_title);
		TextView txtAuthor = (TextView) convertView.findViewById(R.id.list_view_event_item_author);

		if (projects != null){
			txtTitle.setText(projects.get(position).getName());
			txtAuthor.setText(" par " + projects.get(position).getAuthor().getFirstName() + " " + 
					projects.get(position).getAuthor().getLastName());
		}

		return convertView;
	}

}


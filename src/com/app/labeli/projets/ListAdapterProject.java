package com.app.labeli.projets;

import java.util.ArrayList;

import com.app.labeli.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapterProject extends BaseAdapter {

	private Context context;
	private ArrayList<ItemProject> projects;

	public ListAdapterProject(Context context, ArrayList<ItemProject> projects){
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

		//imgIcon.setImageResource(projects.get(position).getIcon());
		if (projects != null){
			txtTitle.setText(projects.get(position).getName());
			txtAuthor.setText(" par " + projects.get(position).getAuthor().getFirstName() + " " + 
					projects.get(position).getAuthor().getLastName());

		}

		return convertView;
	}

}


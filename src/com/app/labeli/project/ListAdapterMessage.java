package com.app.labeli.project;

import java.util.ArrayList;

import com.app.labeli.R;
import com.tools.DateTools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * > @ListAdapterMessage
 *
 * Adapter for MessagesActivity's ListView
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class ListAdapterMessage extends BaseAdapter {

	private Context context;
	private ArrayList<Message> messages;

	public ListAdapterMessage(Context context, ArrayList<Message> messages){
		this.context = context;
		this.messages = messages;
	}

	@Override
	public int getCount() {
		return messages.size();
	}

	@Override
	public Object getItem(int position) {       
		return messages.get(position);
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
			convertView = mInflater.inflate(R.layout.list_view_item_message, null);
		}

		//ImageView imgIcon = (ImageView) convertView.findViewById(R.id.drawer_layout_icon);
		TextView txtAuthor = (TextView) convertView.findViewById(R.id.list_view_item_message_author);
		TextView txtDate = (TextView) convertView.findViewById(R.id.list_view_item_message_date);
		TextView txtText = (TextView) convertView.findViewById(R.id.list_view_item_message_text);

		//imgIcon.setImageResource(projects.get(position).getIcon());
		if (messages != null){
			Message m = messages.get(position);
			txtAuthor.setText(m.getAuthor().getFirstName() + " " + m.getAuthor().getLastName().substring(0, 1) + ".");
			txtDate.setText(DateTools.formatDate(m.getLastEdited()));
			txtText.setText(m.getContent());
		}

		return convertView;
	}

}


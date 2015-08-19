package com.app.labeli.member;

import java.util.ArrayList;

import com.app.labeli.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * > @ListAdapterMember
 *
 * Adapter for FragmentMember's ListView
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class ListAdapterMember extends BaseAdapter{
	private Context mContext;
	private ArrayList<Member> members;

	public ListAdapterMember(Context c, ArrayList<Member> al) {
		mContext = c;
		members = al;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return members.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		Log.i("Test", " " + position);
		return members.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	public void updateDatas(ArrayList<Member> al){
		this.members = (ArrayList<Member>)al.clone();
		this.notifyDataSetChanged();
	}
	
	@Override
	public void notifyDataSetChanged(){
		Log.i("Test", "Refresh Adapter");
		super.notifyDataSetChanged();
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater)
					mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.list_view_member_item, null);
		}

		if (members.get(position).isVisible()){
			// TODO Auto-generated method stub			
			TextView txtName = (TextView) convertView.findViewById(R.id.list_view_member_item_name);

			//imgIcon.setImageResource(eventItems.get(position).getIcon());
			txtName.setText(members.get(position).getFirstName() + " " + members.get(position).getLastName());
		}
		return convertView;
	}


}

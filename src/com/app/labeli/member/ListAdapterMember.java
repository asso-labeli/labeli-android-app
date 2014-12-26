package com.app.labeli.member;

import java.util.ArrayList;

import com.app.labeli.R;
import com.tools.FileTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapterMember extends BaseAdapter{
	private Context mContext;
	private ArrayList<ItemMember> members;

	public ListAdapterMember(Context c, ArrayList<ItemMember> al) {
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
		return members.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void updateDatas(ArrayList<ItemMember> al){
		this.members = new ArrayList<ItemMember>();
		this.notifyDataSetChanged();
		
		this.members = al;
		this.notifyDataSetChanged();
	}
	
	@Override
	public void notifyDataSetChanged(){
		Log.i("Test", "Refresh Adapter");
		super.notifyDataSetChanged();
	}

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

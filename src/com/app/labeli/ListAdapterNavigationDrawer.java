package com.app.labeli;
 
import java.util.ArrayList;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ListAdapterNavigationDrawer extends BaseAdapter {
     
    private Context context;
    private ArrayList<ItemNavigationDrawer> navDrawerItems;
     
    public ListAdapterNavigationDrawer(Context context, ArrayList<ItemNavigationDrawer> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }
 
    @Override
    public int getCount() {
        return navDrawerItems.size();
    }
 
    @Override
    public Object getItem(int position) {       
        return navDrawerItems.get(position);
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
            convertView = mInflater.inflate(R.layout.navigation_drawer_item, null);
        }
          
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.navigation_drawer_item_icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.navigation_drawer_item_title);
        //TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
          
        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
        txtTitle.setText(navDrawerItems.get(position).getTitle());
         
        // displaying count
        // check whether it set visible or not
        if(navDrawerItems.get(position).getCounterVisibility()){
            //txtCount.setText(navDrawerItems.get(position).getCount());
        }else{
            // hide the counter view
            //txtCount.setVisibility(View.GONE);
        }
         
        return convertView;
    }
 
}

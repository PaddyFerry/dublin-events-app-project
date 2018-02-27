package ie.dcu.mail.dublinevents;

import java.util.ArrayList;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

@SuppressWarnings("unchecked")
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> tempChild,tmpGroup;
    public ArrayList<Object> groupedItem = new ArrayList<>();
    public ArrayList<Object> Childtem = new ArrayList<>();
    public LayoutInflater minflater;
    ToggleButton toggleButton;

    public ExpandableListAdapter(ArrayList<Object> grList, ArrayList<Object> childItem) {
        groupedItem = grList;
        Childtem = childItem;
    }

    public void setInflater(LayoutInflater mInflater) {
        this.minflater = mInflater;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             final boolean isLastChild, View convertView, ViewGroup parent) {

        tempChild = (ArrayList<String>) Childtem.get(groupPosition);
        TextView text;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.list_item, null);
        }

        text = convertView.findViewById(R.id.lstitem);
        text.setText(tempChild.get(childPosition));

        final Button button = convertView.findViewById(R.id.favourite);

        button.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        addToFavourites(v);
                    }
                }
        );



        return convertView;
    }

    public void addToFavourites(View v){
        
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) Childtem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return groupedItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        tmpGroup = (ArrayList<String>) groupedItem.get(groupPosition);
        TextView text;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.list_group, null);
        }

        text = convertView.findViewById(R.id.eventName);
        text.setText(tmpGroup.get(0));
        text = convertView.findViewById(R.id.eventDate);
        text.setText(tmpGroup.get(1));
        text = convertView.findViewById(R.id.eventLocation);
        text.setText(tmpGroup.get(2));
        text = convertView.findViewById(R.id.rating);
        text.setText(tmpGroup.get(3));

        return convertView;
    }

    @Override
    public boolean hasStableIds() {return false;}

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}
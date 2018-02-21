package ie.dcu.mail.dublinevents;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unchecked")
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> groupItem, tempChild,tmpGroup;
    public ArrayList<Object> groupedItem = new ArrayList<Object>();
    public ArrayList<Object> Childtem = new ArrayList<Object>();
    public LayoutInflater minflater;
    public Activity activity;

    public ExpandableListAdapter(ArrayList<Object> grList, ArrayList<Object> childItem) {
        groupedItem = grList;
        this.Childtem = childItem;
    }

    public void setInflater(LayoutInflater mInflater, Activity act) {
        this.minflater = mInflater;
        activity = act;
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
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        tempChild = (ArrayList<String>) Childtem.get(groupPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.list_item, null);
        }
        text = (TextView) convertView.findViewById(R.id.lstitem);
        text.setText(tempChild.get(childPosition));
        return convertView;
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
        TextView text = null;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.list_group, null);
        }

        text = (TextView) convertView.findViewById(R.id.eventName);
        text.setText(tmpGroup.get(0));
        text = (TextView) convertView.findViewById(R.id.eventDate);
        text.setText(tmpGroup.get(1));
        text = (TextView) convertView.findViewById(R.id.eventLocation);
        text.setText(tmpGroup.get(2));
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
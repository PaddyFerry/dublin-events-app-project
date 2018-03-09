package ie.dcu.mail.dublinevents;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


@SuppressWarnings("unchecked")
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> tempChild,tmpGroup;
    public ArrayList<Object> groupedItem = new ArrayList<>();
    public ArrayList<Object> Childtem = new ArrayList<>();
    public LayoutInflater minflater;


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
                             final boolean isLastChild, View convertView, final ViewGroup parent) {

        final ArrayList<String> parentGroup = (ArrayList<String>) groupedItem.get(groupPosition);
        tempChild = (ArrayList<String>) Childtem.get(groupPosition);
        TextView text;

        if (parentGroup.get(4).equals("event")) {
            if (convertView == null) {
                convertView = minflater.inflate(R.layout.ven_list_item, null);
            }

            text = convertView.findViewById(R.id.eveDesc);
            text.setText(tempChild.get(childPosition));

            final Button eventInfoButt = convertView.findViewById(R.id.eventInfo);
            eventInfoButt.setText("View Venue");
            eventInfoButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String message = parentGroup.get(1).substring(10,parentGroup.get(1).length());
                    Intent intent = new Intent(Main2Activity.getContext(),MoreInfo.class);
                    intent.putExtra("name",message);
                    Main2Activity.getContext().startActivity(intent);

                }
            });
        }
        else{

            if (convertView == null) {
                convertView = minflater.inflate(R.layout.ven_list_item, null);
            }

            text = convertView.findViewById(R.id.eveDesc);
            text.setText(tempChild.get(childPosition));


            final Button eventInfoButt = convertView.findViewById(R.id.eventInfo);
            eventInfoButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String message = parentGroup.get(0);
                    Intent intent = new Intent(Main2Activity.getContext(),EventInfo.class);
                    intent.putExtra("name",message);
                    Main2Activity.getContext().startActivity(intent);

                }
            });


        }


        final Button button = convertView.findViewById(R.id.favourite);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addProduct(parentGroup.get(0),parentGroup.get(1),parentGroup.get(2),parentGroup.get(4),parentGroup.get(3));
            }
        });



        return convertView;
    }


    public void addProduct(String name,String loc,String date,String type,String link ){
        SqliteDBHandler dbHandler = new SqliteDBHandler(Main2Activity.getContext(),null,null,14);
        dbHandler.addVenue(name,loc,date,type,link);
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

        ImageView eveImage = convertView.findViewById(R.id.eventImage);

        text = convertView.findViewById(R.id.eventName);
        text.setText(tmpGroup.get(0));
        text = convertView.findViewById(R.id.eventDate);
        text.setText(tmpGroup.get(1));
        text = convertView.findViewById(R.id.eventLocation);
        text.setText(tmpGroup.get(2));
        Picasso.with(Main2Activity.getContext()).load(tmpGroup.get(3)).into(eveImage);


        if (tmpGroup.size() > 5) {
            text = convertView.findViewById(R.id.rating);
            text.setText(tmpGroup.get(5));
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {return false;}

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
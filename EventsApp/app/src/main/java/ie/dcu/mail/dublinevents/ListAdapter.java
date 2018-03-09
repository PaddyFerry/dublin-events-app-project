package ie.dcu.mail.dublinevents;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<ArrayList<String>> {

    List<Object> lista;
    SqliteDBHandler dbHandler;

    public ListAdapter(@NonNull Context context,@NonNull List objects) {
        super(context, R.layout.fave_row, objects);
        this.lista = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());

        View customView = myInflater.inflate(R.layout.fave_row,parent,false);
        final ArrayList<String> listOfItems = getItem(position);

        TextView faveName = customView.findViewById(R.id.faveName);
        TextView faveLoc = customView.findViewById(R.id.faveLoc);
        TextView faveCat = customView.findViewById(R.id.favCat);
        ImageView faveImage = customView.findViewById(R.id.faveImage);
        final Button deleteBtn = customView.findViewById(R.id.removeButn);

        faveName.setText(listOfItems.get(0));
        faveLoc.setText(listOfItems.get(1));
        faveCat.setText(listOfItems.get(2));
        Picasso.with(Main2Activity.getContext()).load(listOfItems.get(3)).into(faveImage);


        deleteBtn.setTag(position);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = (int) v.getTag();
                lista.remove(pos);
                ListAdapter.this.notifyDataSetChanged();
                deleteButtonClicked(v,listOfItems.get(0));
            }
        });

        final Button moreInfo = customView.findViewById(R.id.moreInfo);
        if (listOfItems.get(4).equals("venue")) {
            moreInfo.setTag(position);
            moreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(getContext(), MoreInfo.class);
                    intent.putExtra("name", listOfItems.get(0));
                    intent.putExtra("loc", listOfItems.get(1));
                    getContext().startActivity(intent);
                }
            });

        }else{
            moreInfo.setVisibility(View.GONE);
        }
        return customView;
    }


    private void deleteButtonClicked(View v,String deleteItem){
        dbHandler = new SqliteDBHandler(Main2Activity.getContext(),null,null,14);
        dbHandler.deleteVenue(deleteItem);

    }
}

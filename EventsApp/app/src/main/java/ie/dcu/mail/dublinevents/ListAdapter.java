package ie.dcu.mail.dublinevents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<ArrayList<String>> {

    public ListAdapter(@NonNull Context context,@NonNull List objects) {
        super(context, R.layout.fave_row, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.fave_row,parent,false);

        ArrayList<String> listOfItems = getItem(position);

        TextView faveName = customView.findViewById(R.id.faveName);
        TextView faveloc = customView.findViewById(R.id.faveLoc);
        TextView faveCat = customView.findViewById(R.id.favCat);
        ImageView faveImage = customView.findViewById(R.id.faveImage);

        faveName.setText(listOfItems.get(0));
        faveloc.setText(listOfItems.get(1));
        faveCat.setText(listOfItems.get(2));
        faveImage.setImageResource(R.drawable.paddyboi);

        return customView;
    }
}

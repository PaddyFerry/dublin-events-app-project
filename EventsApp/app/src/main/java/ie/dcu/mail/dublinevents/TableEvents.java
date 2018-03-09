package ie.dcu.mail.dublinevents;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Spinner;

public class TableEvents extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.events_frag, container, false);
        final ExpandableListView expandbleLis = rootView.findViewById(R.id.eventFragView);
        expandbleLis.setDividerHeight(2);
        expandbleLis.setGroupIndicator(null);
        expandbleLis.setClickable(true);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("SORT BY");

        Spinner spinner = rootView.findViewById(R.id.Evespinner);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (parentView.getItemAtPosition(position).toString().equals("Upcoming")) {
                    Downloader d = new Downloader(getActivity(), "http://159.65.84.145/events.php?order=datetime", expandbleLis, "event");
                    d.execute();
                }

                if (parentView.getItemAtPosition(position).toString().equals("Search Events")) {
                    String message="events";
                    Intent intent = new Intent(getActivity(),SearchActivity.class);
                    intent.putExtra("message",message);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }

        });

        return rootView;
    }

}
